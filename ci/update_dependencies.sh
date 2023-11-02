#!/bin/bash

. .venv/bin/activate
pip-compile dev-requirements.in > dev-requirements.txt
pip-compile requirements.in > requirements.txt
