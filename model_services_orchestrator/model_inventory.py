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

import typing

from model_services_orchestrator.log import LOGGER
from model_services_orchestrator.types import SimulationId, ModelId, ModelState


class Model:
    model_id: ModelId
    container_url: str
    env_vars: typing.Dict[str, str]
    current_state: ModelState
    exit_code: typing.Optional[int]
    exit_reason: typing.Optional[str]

    def __init__(self,
                 model_id: ModelId,
                 container_url: str,
                 env_vars: typing.Dict[str, str],
                 current_state: ModelState):
        self.model_id = model_id
        self.container_url = container_url
        self.env_vars = env_vars
        self.current_state = current_state
        self.exit_code = None
        self.exit_reason = None


StateChangeObserver = typing.Callable[['ModelInventory', SimulationId, Model, ModelState],
                                      typing.Coroutine[typing.Any, typing.Any, None]]


class ModelInventory:
    active_models_per_simulation_id: typing.Dict[SimulationId,
                                                 typing.Dict[ModelId, Model]]

    state_observers: typing.List[StateChangeObserver]

    def __init__(self):
        self.active_models_per_simulation_id = {}
        self.state_observers = []

    def add_models_to_simulation(self, simulation_id: SimulationId, new_models: typing.List[Model]):
        LOGGER.info(f'Adding models {[model.model_id for model in new_models]} for simulation {simulation_id} '
                    f'to inventory')
        self.active_models_per_simulation_id.setdefault(simulation_id, {}) \
                                                         .update({model.model_id: model for model in new_models})

    def remove_model(self, simulation_id, model_id):
        LOGGER.info(f'Removing model {model_id} from inventory for simulation {simulation_id}')
        popped = self.active_models_per_simulation_id.get(simulation_id).pop(model_id)

        if not popped:
            LOGGER.warning(f'Model {model_id} for simulation {simulation_id} was unknown. This should not happen.')

    def remove_simulation(self, simulation_id):
        LOGGER.info(f'Removing simulation {simulation_id} from cache.')
        popped = self.active_models_per_simulation_id.pop(simulation_id, None)

        if not popped:
            LOGGER.error(f'Simulation {simulation_id} was unknown. This should not happen.')

    def get_models(self, simulation_id: SimulationId) -> typing.List[Model]:
        return list(self.active_models_per_simulation_id.get(simulation_id, {}).values())

    def get_model(self, simulation_id: SimulationId, model_id: ModelId) -> typing.Optional[Model]:
        return self.active_models_per_simulation_id.get(simulation_id, {}).get(model_id)

    def register_state_change_observer(self, observer: StateChangeObserver) -> None:
        self.state_observers.append(observer)

    async def _notify_state_change_observers(self,
                                             simulation_id: SimulationId,
                                             model: Model,
                                             old_state: ModelState) -> None:
        for observer in self.state_observers:
            await observer(self, simulation_id, model, old_state)

    def set_exit_parameters(self,
                            simulation_id: SimulationId,
                            model_id: ModelId,
                            exit_code: typing.Optional[int],
                            exit_reason: typing.Optional[str]):
        model = self.get_model(simulation_id, model_id)
        assert model
        LOGGER.info(f'Model {simulation_id}/{model_id} now has exit code {exit_code} due to: {exit_reason}.')
        model.exit_code = exit_code
        model.exit_reason = exit_reason

    async def mark_model_as(self,
                            simulation_id: SimulationId,
                            model_id: ModelId,
                            new_state: ModelState) -> None:
        model = self.get_model(simulation_id, model_id)
        if model:
            old_state = model.current_state

            if old_state != new_state:
                model.current_state = new_state
                LOGGER.debug(f'Notifying state observers that model {simulation_id}/{model_id} has changed '
                             f'state from {old_state} to {new_state}')
                await self._notify_state_change_observers(simulation_id, model, old_state)
        else:
            LOGGER.warning(f'Model {model_id} in simulation {simulation_id} does not exist so it could not be marked '
                           f'as {new_state}')
