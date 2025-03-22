#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Package the operator with maven
mvn clean package

read -n 1 -p "Press any key to continue"

clear

# Display Docker images
docker images
