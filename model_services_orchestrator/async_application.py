import abc
import asyncio
import typing
import traceback

from model_services_orchestrator.log import LOGGER


class AsyncApplication(abc.ABC):
    def __init__(self):
        pass

    @abc.abstractmethod
    async def main_task(self, loop:asyncio.AbstractEventLoop) -> None:
        pass

    @abc.abstractmethod
    def get_name(self) -> str:
        pass

    @abc.abstractmethod
    def stop(self, loop: asyncio.AbstractEventLoop) -> None:
        pass

    def create_main_task(self, loop: asyncio.AbstractEventLoop) -> asyncio.Task:
        return loop.create_task(self.main_task(loop))


class AsyncApplications:
    loop: asyncio.AbstractEventLoop
    applications: typing.List[AsyncApplication]

    def __init__(self) -> None:
        self.loop = asyncio.new_event_loop()
        self.applications = []

    def add_application(self, application: AsyncApplication) -> None:
        self.applications.append(application)

    async def _run_application(self, application):
        try:
            await application.create_main_task(self.loop)
        except Exception as exc:  # pylint: disable=broad-except
            LOGGER.error(f'Application {application.get_name()} crashed with exception!')
            LOGGER.error(''.join(traceback.format_exception(None, exc, exc.__traceback__)))

    async def _run_all_until_done(self) -> None:
        tasks = [self._run_application(application) for application in self.applications]
        await asyncio.gather(*tasks)

    def run_all(self):
        try:
            self.loop.run_until_complete(self._run_all_until_done())
        finally:
            self.loop.close()

    def stop(self):
        for application in self.applications:
            application.stop(self.loop)
