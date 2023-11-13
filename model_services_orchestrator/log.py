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
