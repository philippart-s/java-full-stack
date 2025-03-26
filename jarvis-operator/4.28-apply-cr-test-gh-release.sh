#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 15: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# Apply check-release CR
kubectl apply  -f ./src/test/resources/cr-test-gh-release-watch.yml -n jarvis