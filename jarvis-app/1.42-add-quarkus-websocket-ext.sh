#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Add Websocket extension to call Jarvis using Websocket protocole
quarkus ext add io.quarkus:quarkus-websockets-next