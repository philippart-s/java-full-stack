#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# Create test-hello-world-operator namespace
kubectl create ns test-hello-world-operator

read -s -n 1

# Apply HelloWorld test CR
kubectl apply -f ./src/test/resources/cr-test-hello-world.yaml -n test-hello-world-operator

read -s -n 1

# Delete CR HelloWorld
kubectl delete helloworldoperator.fullstack.wilda.fr hello-world -n test-hello-world-operator