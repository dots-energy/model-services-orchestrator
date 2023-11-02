#!/bin/bash

# login first: docker login -u <ci.tno.nl email address> ci.tno.nl

VERSION=0.0.1
REPOSITORY="ci.tno.nl/dots/model-services-orchestrator"

docker build -t ${REPOSITORY}:${VERSION} ./..

docker push ${REPOSITORY}:${VERSION}