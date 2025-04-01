#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10:11 $(basename "$0")

# Package the operator with maven
mvn clean package

read -n 1 -p "Press any key to continue"

clear

bat -P -r 19: $(basename "$0")

# Display Docker images
docker images | grep jarvis-operator
