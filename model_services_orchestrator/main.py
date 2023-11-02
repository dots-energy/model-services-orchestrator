import os
import signal
import typing

from dotenv import load_dotenv
import kubernetes

from model_services_orchestrator.async_application import AsyncApplications
from model_services_orchestrator.k8s_api import K8sApi
from model_services_orchestrator.log import LOGGER
from model_services_orchestrator.mqtt_application import MQTTApplication
from model_services_orchestrator.model_inventory import ModelInventory
from model_services_orchestrator.model_state_observer import ModelStateObserver
from model_services_orchestrator.poll_pod_health_application import PollPodHealthApplication

TO_STOP_ON_SIGNAL: typing.List[typing.Callable[[], None]] = []

load_dotenv()  # take environment variables from .env.


def all_stop_on_signal(_signal_num, _stack_frame):
    for handler in TO_STOP_ON_SIGNAL:
        handler()


signal.signal(signal.SIGINT, all_stop_on_signal)

# Turn off 'InsecureRequestWarning: Unverified HTTPS request' of urllib3 if available
try:
    import urllib3
except ImportError:
    pass
else:
    urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)


class EnvConfig:
    CONFIG_KEYS = [('KUBERNETES_HOST', 'localhost', str, False),
                   ('KUBERNETES_PORT', '6443', int, False),
                   ('KUBERNETES_API_TOKEN', None, str, True),
                   ('KUBERNETES_PULL_IMAGE_SECRET_NAME', None, str, False),
                   ('MQTT_HOST', 'localhost', str, False),
                   ('MQTT_PORT', '1883', int, False),
                   ('MQTT_USERNAME', '', str, False),
                   ('MQTT_PASSWORD', '', str, True),
                   ('SIMULATOR_NAME', '', str, False)]

    @staticmethod
    def load(keys: typing.List[typing.Tuple[str,
                                            typing.Optional[str],
                                            typing.Any, bool]]) -> typing.Dict[str, typing.Any]:
        result = {}
        LOGGER.info('Config:')
        max_length_name = max(len(key[0]) for key in keys)
        for name, default, transform, hide in keys:
            if default is None and (name not in os.environ):
                raise Exception(f'Missing environment variable {name}')

            env_value = os.getenv(name, default)
            LOGGER.info(f'    {f"{name}:".ljust(max_length_name + 4)}{"<hidden>" if hide else env_value}')
            result[name] = transform(env_value)
        LOGGER.info('')

        return result


def main():
    async_applications = AsyncApplications()
    TO_STOP_ON_SIGNAL.append(async_applications.stop)

    config = EnvConfig.load(EnvConfig.CONFIG_KEYS)

    configuration = kubernetes.client.Configuration()
    configuration.api_key_prefix['authorization'] = 'Bearer'
    configuration.api_key['authorization'] = config['KUBERNETES_API_TOKEN']
    configuration.host = f"https://{config['KUBERNETES_HOST']}:{config['KUBERNETES_PORT']}"
    configuration.verify_ssl = False
    configuration.retries = 3

    # Enter a context with an instance of the API kubernetes.client
    with kubernetes.client.ApiClient(configuration) as api_client:
        k8s_api = K8sApi(api_client,
                         async_applications.loop,
                         config['KUBERNETES_PULL_IMAGE_SECRET_NAME'].strip())
        model_inventory = ModelInventory()
        mqtt_application = MQTTApplication(
            simulation_topic_name=config['SIMULATOR_NAME'].lower(),
            mqtt_host=config['MQTT_HOST'],
            mqtt_port=config['MQTT_PORT'],
            mqtt_username=config['MQTT_USERNAME'],
            mqtt_password=config['MQTT_PASSWORD'],
            model_inventory=model_inventory,
            k8s_api=k8s_api)
        poll_pod_health_application = PollPodHealthApplication(model_inventory=model_inventory,
                                                               k8s_api=k8s_api)
        ModelStateObserver(model_inventory, mqtt_application)
        async_applications.add_application(mqtt_application)
        async_applications.add_application(poll_pod_health_application)
        LOGGER.info('Ready for processing...')
        async_applications.run_all()


if __name__ == "__main__":
    main()
