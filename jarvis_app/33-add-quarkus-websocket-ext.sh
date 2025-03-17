#!/bin/bash

# Load enviroment variables
source ../.env

clear

bat -P -r 10: $(basename "$0")

# Add Websocket extension to call Jarvis using Websocket protocole
quarkus ext add io.quarkus:quarkus-websockets-next