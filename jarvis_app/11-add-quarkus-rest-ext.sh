#!/bin/bash

# Load enviroment variables
source ../.env

clear

bat -P -r 10: $(basename "$0")

# Add quarkus-rest-jackson Quarkus extension to do some REST call and expose some REST API
quarkus ext add quarkus-rest-jackson, quarkus-rest-client-jackson

# Remove unused Quakus rest extension
quarkus ext remove quarkus-rest