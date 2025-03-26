#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Add JIB extension
quarkus ext add io.quarkus:quarkus-container-image-jib