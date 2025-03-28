#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Login and push the Docker image
docker login 95y036e0.gra7.container-registry.ovh.net -u $DOCKER_USER -p $DOCKER_PASSWORD
docker push 95y036e0.gra7.container-registry.ovh.net/devoxx/jarvis-operator:1.0.0-SNAPSHOT