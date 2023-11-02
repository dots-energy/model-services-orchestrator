import asyncio
import typing

from asyncio_mqtt import Client, MqttError

from model_services_orchestrator.async_application import AsyncApplication
from model_services_orchestrator.k8s_api import K8sApi
from model_services_orchestrator.log import LOGGER
from model_services_orchestrator.messages import (DeployModels, ModelConfiguration, ModelsReady, TerminationStatus,
                                                  ModelHasTerminated, AllModelsHaveTerminated)
from model_services_orchestrator.model_inventory import ModelInventory, Model, ModelState
from model_services_orchestrator.types import SimulationId, ModelId


class MQTTApplication(AsyncApplication):  # pylint: disable=too-many-instance-attributes
    simulation_topic_name: str
    host: str
    port: int
    username: typing.Optional[str]
    password: typing.Optional[str]

    model_inventory: ModelInventory
    k8s_api: K8sApi

    client: typing.Optional[Client]
    stopped: bool

    loop: typing.Optional[asyncio.AbstractEventLoop]

    def __init__(self,  # pylint: disable=too-many-arguments
                 simulation_topic_name: str,
                 mqtt_host: str,
                 mqtt_port: int,
                 mqtt_username: typing.Optional[str],
                 mqtt_password: typing.Optional[str],
                 model_inventory: ModelInventory,
                 k8s_api: K8sApi):
        super().__init__()
        self.simulation_topic_name = simulation_topic_name
        self.host = mqtt_host
        self.port = mqtt_port

        self.username = mqtt_username
        self.password = mqtt_password

        self.model_inventory = model_inventory
        self.k8s_api = k8s_api

        self.client = None
        self.stopped = False

        self.loop = None

    def get_name(self) -> str:
        return 'MQTTApplication'

    def stop(self, loop: asyncio.AbstractEventLoop):
        self.stopped = True
        asyncio.run_coroutine_threadsafe(self._disconnect_client(), loop)

    async def _disconnect_client(self):
        if self.client:
            await self.client.disconnect()

    async def main_task(self, loop: asyncio.AbstractEventLoop) -> None:
        self.loop = loop
        reconnect_interval = 3  # [seconds]
        while not self.stopped:
            try:
                await self.setup_mqtt_subscriptions(loop)
            except MqttError as error:
                if not self.stopped:
                    LOGGER.error(f'Error "{error}". Reconnecting in {reconnect_interval} seconds.')
            finally:
                if not self.stopped:
                    await asyncio.sleep(reconnect_interval)

        self.loop = None

    async def setup_mqtt_subscriptions(self, loop: asyncio.AbstractEventLoop) -> None:
        async with Client(hostname=self.host,
                          port=self.port,
                          username=self.username,
                          password=self.password) as self.client:
            tasks = [
                loop.create_task(self.deploy_models()),
                loop.create_task(self.receive_for_ready_processing()),
                loop.create_task(self.log_unfiltered_messages())
            ]
            await self.client.subscribe("#")

            await asyncio.gather(*tasks)

    async def log_unfiltered_messages(self):
        async with self.client.unfiltered_messages() as messages:
            async for message in messages:
                LOGGER.debug(f'Received unknown message. [{message.topic}] {message.payload}')

    async def deploy_models(self):
        async with self.client.filtered_messages(
                f'/lifecycle/{self.simulation_topic_name}/mso/+/DeployModels') as messages:
            async for message in messages:
                simulation_id = message.topic.split('/')[4]
                deploy_models = DeployModels()
                deploy_models.ParseFromString(message.payload)
                LOGGER.debug(f'Received DeployModels message: {deploy_models}')
                new_models = [MQTTApplication.from_mqtt_model_config_to_model(model_config, ModelState.CREATED)
                              for model_config in deploy_models.modelConfigurations]
                self.model_inventory.add_models_to_simulation(simulation_id, new_models)
                for new_model in new_models:
                    succeeded = await self.k8s_api.deploy_model(deploy_models.simulatorId, simulation_id, new_model,
                                                                deploy_models.keepLogsHours)
                    if not succeeded:
                        self.model_inventory.remove_model(simulation_id, new_model.model_id)
                    else:
                        LOGGER.info(f'Successfully deployed model {new_model.model_id} for simulation {simulation_id}')

    async def receive_for_ready_processing(self):
        async with self.client.filtered_messages('/lifecycle/model/mso/+/+/ReadyForProcessing') as messages:
            async for message in messages:
                topic_parts = message.topic.split('/')
                simulation_id = topic_parts[4]
                model_id = topic_parts[5]
                # ready_for_processing = ReadyForProcessing()
                # ready_for_processing.ParseFromString(message.payload)

                await self.model_inventory.mark_model_as(simulation_id, model_id, ModelState.RUNNING)

    async def send_models_ready(self, simulation_id: SimulationId) -> None:
        assert self.client
        LOGGER.info(f'All models in simulation {simulation_id} are running. Send out ModelsReady.')
        await self.client.publish(f'/lifecycle/mso/{self.simulation_topic_name}/{simulation_id}/ModelsReady',
                                  ModelsReady().SerializeToString())

    async def send_model_terminated(self,
                                    simulation_id: SimulationId,
                                    model_id: ModelId,
                                    status: TerminationStatus,
                                    exit_code: int):
        assert self.client
        LOGGER.info(f'Model {simulation_id}/{model_id} has terminated. Send out ModelHasTerminated.')
        model_has_terminated = ModelHasTerminated(status=status, exitCode=exit_code)
        await self.client.publish(
            f'/lifecycle/mso/{self.simulation_topic_name}/{simulation_id}/{model_id}/ModelHasTerminated',
            model_has_terminated.SerializeToString())

    async def send_all_models_terminated(self, simulation_id: SimulationId):
        assert self.client
        LOGGER.info(f'All models for simulation {simulation_id} have terminated. Send out AllModelsHaveTerminated.')
        await self.client.publish(
            f'/lifecycle/mso/{self.simulation_topic_name}/{simulation_id}/AllModelsHaveTerminated',
            AllModelsHaveTerminated().SerializeToString())

    @staticmethod
    def from_mqtt_model_config_to_model(model_config: ModelConfiguration, state: ModelState) -> Model:
        return Model(model_config.modelID,
                     model_config.imageUrl,
                     {env.name: env.value for env in model_config.environmentVariables},
                     state)
