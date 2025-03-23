#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Add Qute extension to add frontend to Jarvis
quarkus ext add io.quarkiverse.qute.web:quarkus-qute-web