#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10:14 $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# Create jarvis namespace
kubectl create ns jarvis

read -n 1 -p "Press any key to continue"

clear 

bat -P -r 22: $(basename "$0")

# Create secrets
kubectl create secret generic devoxx-secrets --from-env-file=../.env --from-env-file=../.talk.env -n jarvis