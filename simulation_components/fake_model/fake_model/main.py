import asyncio
import os
import sys

from asyncio_mqtt import Client
from fake_model.messages import ReadyForProcessing


MQTT_HOST = os.getenv('MQTT_HOST')
MQTT_PORT = int(os.getenv('MQTT_PORT', '1883'))
MQTT_USERNAME = os.getenv('MQTT_USERNAME', None)
MQTT_PASSWORD = os.getenv('MQTT_PASSWORD', None)
SIMULATOR_ID = os.getenv('SIMULATOR_ID')
SIMULATION_ID = os.getenv('SIMULATION_ID')
MODEL_ID = os.getenv('MODEL_ID')
EXIT_CODE = int(os.getenv('EXIT_CODE', '0'))

print('MQTT_HOST:     ', MQTT_HOST)
print('MQTT_PORT:     ', MQTT_PORT)
print('MQTT_USERNAME:     ', MQTT_USERNAME)
print('MQTT_PASSWORD:     ', '<hidden>')
print('SIMULATOR_ID:      ', SIMULATOR_ID)
print('SIMULATION_ID: ', SIMULATION_ID)
print('MODEL_ID:      ', MODEL_ID)
print('EXIT_CODE:     ', EXIT_CODE)


async def main():
    async with Client(hostname=MQTT_HOST, port=MQTT_PORT, username=MQTT_USERNAME, password=MQTT_PASSWORD) as client:
        print('Model booting...')

        if EXIT_CODE == 1:
            print(f'Fake crashing before ReadyForProcessing with exit code {EXIT_CODE}')
            sys.exit(EXIT_CODE)
        await client.publish(f'/lifecycle/model/mso/{SIMULATION_ID}/{MODEL_ID}/ReadyForProcessing',
                             payload=ReadyForProcessing().SerializeToString())
        print('ReadyForProcessing send.')
        if EXIT_CODE == 2:
            print(f'Fake crashing after ReadyForProcessing with exit code {EXIT_CODE}')
            sys.exit(EXIT_CODE)
        print('Going to sleep...')
        await asyncio.sleep(10)
        print('Done!')

asyncio.run(main())
