#!/bin/bash

# login first: docker login -u dotsenergyframework

VERSION=0.0.3
REPOSITORY="dotsenergyframework/model-services-orchestrator"

docker build -t ${REPOSITORY}:${VERSION} ./..

docker push ${REPOSITORY}:${VERSION}