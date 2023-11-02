import os
import logging
import sys

LOGGER = logging.getLogger('MSO')
LOG_LEVEL = os.getenv('LOG_LEVEL', 'INFO')
print('Will use log level:', LOG_LEVEL)
LOGGER.setLevel(LOG_LEVEL)

LOG_HANDLER = logging.StreamHandler(sys.stdout)
FORMATTER = logging.Formatter(fmt='%(asctime)s [%(threadName)s][%(filename)s:%(lineno)d][%(levelname)s]: %(message)s')
LOG_HANDLER.setFormatter(FORMATTER)
LOGGER.addHandler(LOG_HANDLER)
