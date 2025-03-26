#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 15:16 $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# Create jarvis namespace
kubectl create ns jarvis

read -n 1 -p "Press any key to continue"

clear 

bat -P -r 24: $(basename "$0")

# Create secrets
kubectl create secret generic devoxx-secrets --from-env-file=../.env -n jarvis