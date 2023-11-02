import asyncio

from asyncio_mqtt import Client
from fake_simulator.messages import DeployModels, ModelConfiguration, EnvironmentVariable

SIMULATOR_NAME = 'dots-so'

MQTT_USERNAME = 'dots'
MQTT_PASSWORD = 'dotspass'
NUM_OF_MODELS_TO_SEND = 1

KEEP_LOGS_HOURS = 0.01


async def main():
    async with Client(hostname='10.37.1.5', port=31534, username=MQTT_USERNAME, password=MQTT_PASSWORD) as client:
        model_configs = []
        simulation_id = 'sim02-abc'

        for i in range(0, NUM_OF_MODELS_TO_SEND):
            model_id = f'{i}-model'
            env_vars = [EnvironmentVariable(name='MQTT_HOST', value='10.37.1.5'),
                        EnvironmentVariable(name='MQTT_PORT', value='31534'),
                        EnvironmentVariable(name='SIMULATOR_ID', value='SO01'),
                        EnvironmentVariable(name='SIMULATION_ID', value=simulation_id),
                        EnvironmentVariable(name='MODEL_ID', value=model_id),
                        EnvironmentVariable(name='MQTT_USERNAME', value=MQTT_USERNAME),
                        EnvironmentVariable(name='MQTT_PASSWORD', value=MQTT_PASSWORD),
                        EnvironmentVariable(name='BID_PRICE_EPS', value='0.1'),
                        EnvironmentVariable(name='MAX_ITERATIONS', value='100000'),
                        EnvironmentVariable(name='PRINT_ITERATIONS', value='False'),
                        EnvironmentVariable(name='CONTROLLER_HORIZON', value='12'),
                        EnvironmentVariable(name='LOG4P_JSON_LOCATION', value='tno/shared/log4p.json'),
                        ]
            model_configs.append(ModelConfiguration(modelID=model_id,
                                                    imageUrl='ci.tno.nl/heatlosscalculation/essim-building-model:latest',
                                                    environmentVariables=env_vars))
        message = DeployModels(simulatorId='204-asd',
                               modelConfigurations=model_configs,
                               keepLogsHours=KEEP_LOGS_HOURS)
        print('Deploying models')
        print(message)
        await client.publish(f'/lifecycle/{SIMULATOR_NAME}/mso/{simulation_id}/DeployModels',
                             payload=message.SerializeToString())
        print('Done!')


asyncio.run(main())
