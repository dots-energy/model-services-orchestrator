#  This work is based on original code developed and copyrighted by TNO 2023.
#  Subsequent contributions are licensed to you by the developers of such code and are
#  made available to the Project under one or several contributor license agreements.
#
#  This work is licensed to you under the Apache License, Version 2.0.
#  You may obtain a copy of the license at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#  Contributors:
#      TNO         - Initial implementation
#  Manager:
#      TNO

from model_services_orchestrator.log import LOGGER
from model_services_orchestrator.messages import TerminationStatus
from model_services_orchestrator.model_inventory import ModelInventory, Model
from model_services_orchestrator.mqtt_application import MQTTApplication
from model_services_orchestrator.types import SimulationId, ModelState, ModelState_TERMINATED


class ModelStateObserver:
    def __init__(self, model_inventory: ModelInventory, mqtt_client: MQTTApplication):
        self.model_inventory = model_inventory
        self.mqtt_client = mqtt_client
        model_inventory.register_state_change_observer(self.notification_model_state_change)

    async def notification_model_state_change(self,
                                              model_inventory: ModelInventory,
                                              simulation_id: SimulationId,
                                              model: Model,
                                              _old_state: ModelState):
        all_models = model_inventory.get_models(simulation_id)

        if model.current_state == ModelState.RUNNING:
            if all(m.current_state == ModelState.RUNNING for m in all_models):
                await self.mqtt_client.send_models_ready(simulation_id)

        elif model.current_state == ModelState.TERMINATED_SUCCESSFULL:
            assert model.exit_code is not None
            await self.mqtt_client.send_model_terminated(simulation_id,
                                                         model.model_id,
                                                         TerminationStatus.SUCCESSFULL,
                                                         model.exit_code)
        elif model.current_state == ModelState.TERMINATED_FAILED:
            assert model.exit_code is not None
            LOGGER.info(f'Model {simulation_id}/{model.model_id} exited with code ({model.exit_code}) due '
                        f'to: {model.exit_reason}')
            await self.mqtt_client.send_model_terminated(simulation_id,
                                                         model.model_id,
                                                         TerminationStatus.FAILED,
                                                         model.exit_code)

        if all((model.current_state in ModelState_TERMINATED,
                all(m.current_state in ModelState_TERMINATED for m in all_models))):
            await self.mqtt_client.send_all_models_terminated(simulation_id)
            self.model_inventory.remove_simulation(simulation_id)
