
# simulation_components/mosquitto

This directory contains the mosquitto config for a locally deployed mosquitto.
Mosquitto requires a password that is hashed. The unencrypted password is stored in `unencrypted_password_file.conf`.
To encrypt it so it may be used by the docker configurations in this repo:

```
./encrypt_password_file.sh
```
