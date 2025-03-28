#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Create CLI project
quarkus create cli fr.wilda.fullstack:jarvis-cli-init:0.0.1-SNAPSHOT --extension='quarkus-rest-client-jackson' --no-wrapper