#!/bin/bash

VERSION=0.0.1

docker build -t model_services_orchestrator:${VERSION} ./..

# for local testing load in kind
kind load docker-image model_services_orchestrator:${VERSION} --name dots-kind

# for production push to dockerhub: public or https://hub.docker.com/orgs/tnodocker?
