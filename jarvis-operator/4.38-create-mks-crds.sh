#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig-ovh.yml

# Create the two CRDs
kubectl apply -f ./target/kubernetes/helloworldoperators.fullstack.wilda.fr-v1.yml
kubectl apply -f ./target/kubernetes/jarvisoperators.fullstack.wilda.fr-v1.yml
