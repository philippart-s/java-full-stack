#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Create CLI project
quarkus create cli fr.wilda.fullstack:jarvis-cli-init:0.0.1-SNAPSHOT --extension='quarkus-rest-client-jackson' --no-wrapper