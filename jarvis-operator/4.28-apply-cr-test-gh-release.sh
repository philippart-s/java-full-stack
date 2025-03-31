#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# Apply check-release CR
kubectl apply  -f ./src/test/resources/cr-test-gh-release-watch.yml -n jarvis