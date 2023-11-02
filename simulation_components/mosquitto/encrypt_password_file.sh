#!/bin/bash

cp ./unencrypted_password_file.conf mqtt_passwd

docker run --rm -v "$PWD/mqtt_passwd:/mosquitto/config/mqtt_passwd" eclipse-mosquitto mosquitto_passwd -U /mosquitto/config/mqtt_passwd
