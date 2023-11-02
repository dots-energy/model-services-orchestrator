import asyncio

from asyncio_mqtt import Client
from fake_simulator.messages import DeployModels, ModelConfiguration, EnvironmentVariable

SIMULATOR_NAME='dots-so'

MQTT_USERNAME = 'dots'
MQTT_PASSWORD = 'dotspass'
NUM_OF_MODELS_TO_SEND = 4

KEEP_LOGS_HOURS = 0.01


async def main():
    async with Client(hostname='localhost', port=1883, username=MQTT_USERNAME, password=MQTT_PASSWORD) as client:
        model_configs = []
        simulation_id = 'sim02-abc'

        for i in range(0, NUM_OF_MODELS_TO_SEND):
            model_id = f'{i}-model'
            exit_code = i if i <= 2 else None
            env_vars = [EnvironmentVariable(name='MQTT_HOST', value='host.docker.internal'),
                        EnvironmentVariable(name='MQTT_PORT', value='1883'),
                        EnvironmentVariable(name='SIMULATOR_ID', value='so01'),
                        EnvironmentVariable(name='SIMULATION_ID', value=simulation_id),
                        EnvironmentVariable(name='MODEL_ID', value=model_id),
                        EnvironmentVariable(name='MQTT_USERNAME', value=MQTT_USERNAME),
                        EnvironmentVariable(name='MQTT_PASSWORD', value=MQTT_PASSWORD)]
            if exit_code:
                env_vars.append(EnvironmentVariable(name='EXIT_CODE', value=str(exit_code)))
            model_configs.append(ModelConfiguration(modelID=model_id,
                                                    imageUrl='fake_model:0.0.1',
                                                    environmentVariables=env_vars))
        message = DeployModels(simulatorId='204-asd',
                               modelConfigurations=model_configs)
        print('Deploying models')
        print(message)
        await client.publish(f'/lifecycle/{SIMULATOR_NAME}/mso/{simulation_id}/DeployModels',
                             payload=message.SerializeToString())
        print('Done!')

asyncio.run(main())
