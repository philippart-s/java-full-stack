#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Login and push the Docker image
docker login 95y036e0.gra7.container-registry.ovh.net -u $DOCKER_USER -p $DOCKER_PASSWORD
docker push 95y036e0.gra7.container-registry.ovh.net/devoxx/jarvis-operator:1.0.0-SNAPSHOT