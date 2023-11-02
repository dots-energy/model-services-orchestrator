#!/bin/bash

SRC_DIR=./message_definitions
DST_DIR_PYTHON=./model_services_orchestrator/messages
DST_DIR_JAVA=./java

mkdir -p $DST_DIR_PYTHON
mkdir -p $DST_DIR_JAVA
protoc -I=$SRC_DIR --python_out=$DST_DIR_PYTHON $SRC_DIR/*.proto --java_out=$DST_DIR_JAVA
