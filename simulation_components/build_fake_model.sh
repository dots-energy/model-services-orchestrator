#!/bin/bash

cd ./fake_model/

VERSION=0.0.1

docker build -t fake_model:${VERSION} ./
kind load docker-image fake_model:${VERSION}
