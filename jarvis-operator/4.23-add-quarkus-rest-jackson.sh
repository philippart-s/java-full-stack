#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Add quarkus-rest-jackson Quarkus extension to do some REST call and expose some REST API
quarkus ext add quarkus-rest-jackson