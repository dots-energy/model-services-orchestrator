#!/bin/bash

. ../.venv/bin/activate
cd ./fake_simulator/
echo $PWD
python3 -m fake_simulator.send_deploy_models_fake_model
