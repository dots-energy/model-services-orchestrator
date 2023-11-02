import enum

SimulatorId = str
SimulationId = str
ModelId = str


class ModelState(enum.Enum):
    CREATED = 'created'
    RUNNING = 'running'
    TERMINATED_FAILED = 'terminated_failed'
    TERMINATED_SUCCESSFULL = 'terminated_successfull'


ModelState_TERMINATED = (ModelState.TERMINATED_FAILED, ModelState.TERMINATED_SUCCESSFULL)
