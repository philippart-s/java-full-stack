#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig-ovh.yml

# Create the two CRDs
kubectl apply -f ./target/kubernetes/helloworldoperators.fullstack.wilda.fr-v1.yml
kubectl apply -f ./target/kubernetes/jarvisoperators.fullstack.wilda.fr-v1.yml
