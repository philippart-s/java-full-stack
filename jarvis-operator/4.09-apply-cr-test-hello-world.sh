#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 15:16 $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# Create test-hello-world-operator namespace
kubectl create ns test-hello-world-operator

read -n 1 -p "Press any key to continue"

clear

bat -P -r 24:25 $(basename "$0")

# Apply HelloWorld test CR
kubectl apply -f ./src/test/resources/cr-test-hello-world.yaml -n test-hello-world-operator

read -n 1 -p "Press any key to continue"

clear

bat -P -r 33: $(basename "$0")

# Delete CR HelloWorld
kubectl delete helloworldoperator.fullstack.wilda.fr hello-world -n test-hello-world-operator