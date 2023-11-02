#!/bin/bash

. ../.venv/bin/activate
cd ./fake_so/
echo $PWD
python3 -m fake_so.send_deploy_models_simulation_building_model
