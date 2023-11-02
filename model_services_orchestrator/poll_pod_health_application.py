import asyncio
import typing

from model_services_orchestrator.async_application import AsyncApplication
from model_services_orchestrator.log import LOGGER
from model_services_orchestrator.model_inventory import ModelInventory
from model_services_orchestrator.k8s_api import K8sApi
from model_services_orchestrator.types import ModelState_TERMINATED


class PollPodHealthApplication(AsyncApplication):
    WAIT_TIME_BETWEEN_POLLING = 2
    LAST_LOG_LINES_COUNT = 30

    model_inventory: ModelInventory
    k8s_api: K8sApi

    stopped: bool
    _sleeping_handle: typing.Optional[asyncio.Task]

    def __init__(self, model_inventory: ModelInventory, k8s_api: K8sApi):
        super().__init__()
        self.model_inventory = model_inventory
        self.k8s_api = k8s_api

        self.stopped = False
        self._sleeping_handle = None
        self._sleeping_handle_lock = asyncio.Lock()

    def get_name(self) -> str:
        return 'PollPodHealthApplication'

    async def main_task(self, loop: asyncio.AbstractEventLoop) -> None:
        while not self.stopped:  # pylint: disable=too-many-nested-blocks
            pod_states_per_sim_id = await self.k8s_api.list_pods_status_per_simulation_id()

            for sim_id, pod_states in pod_states_per_sim_id.items():
                for pod_status in pod_states:
                    model = self.model_inventory.get_model(sim_id, pod_status.model_id)
                    if model:
                        # Known model
                        if pod_status.model_state in ModelState_TERMINATED and \
                                pod_status.model_state != model.current_state:
                            self.model_inventory.set_exit_parameters(sim_id,
                                                                     pod_status.model_id,
                                                                     pod_status.exit_code,
                                                                     pod_status.exit_reason)
                            LOGGER.info(f'Model {sim_id}/{pod_status.model_id} has terminated. '
                                        f'Marking as {pod_status.model_state}.')
                            await self.k8s_api.add_delete_date_to_pods_status_for_simulation_id(sim_id)

                            if pod_status.exit_code != 0:
                                # Pod has crashed in some form or fashion
                                last_log_lines = await self.k8s_api.retrieve_last_log_lines(pod_status.simulator_id,
                                                                                            sim_id, pod_status.model_id,
                                                                                            30)
                                LOGGER.warning(f'Pod {pod_status.simulator_id}.{sim_id}.{pod_status.model_id} crashed with '
                                               f'status {pod_status.exit_code} and the last '
                                               f'{PollPodHealthApplication.LAST_LOG_LINES_COUNT} log lines are: \n'
                                               f'{last_log_lines}')
                            await self.model_inventory.mark_model_as(sim_id,
                                                                     pod_status.model_id,
                                                                     pod_status.model_state)
                    else:
                        # Unknown model
                        # LOGGER.info(f'Model {sim_id}/{pod_status.model_id} is unknown. Removing.')
                        await self.k8s_api.delete_model(pod_status.simulator_id, sim_id, pod_status.model_id,
                                                        pod_status.delete_by)

            await self._safe_sleep()

    async def _safe_sleep(self):
        async with self._sleeping_handle_lock:
            if not self.stopped:
                self._sleeping_handle = asyncio.create_task(
                    asyncio.sleep(PollPodHealthApplication.WAIT_TIME_BETWEEN_POLLING))
                await self._sleeping_handle

    async def _cancel_sleep(self) -> None:
        async with self._sleeping_handle_lock:
            if self._sleeping_handle:
                self._sleeping_handle.cancel()

    def stop(self, loop: asyncio.AbstractEventLoop) -> None:
        self.stopped = True
        asyncio.run_coroutine_threadsafe(self._cancel_sleep(), loop)
