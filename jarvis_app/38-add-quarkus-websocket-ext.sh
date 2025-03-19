#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Add Websocket extension to call Jarvis using Websocket protocole
quarkus ext add io.quarkus:quarkus-websockets-next