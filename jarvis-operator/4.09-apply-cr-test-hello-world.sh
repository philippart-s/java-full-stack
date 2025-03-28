#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10:14 $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# Create test-hello-world-operator namespace
kubectl create ns test-hello-world-operator

read -n 1 -p "Press any key to continue"

clear

bat -P -r 22:23 $(basename "$0")

# Apply HelloWorld test CR
kubectl apply -f ./src/test/resources/cr-test-hello-world.yaml -n test-hello-world-operator

read -n 1 -p "Press any key to continue"

clear

bat -P -r 31: $(basename "$0")

# Delete CR HelloWorld
kubectl delete helloworldoperator.fullstack.wilda.fr hello-world -n test-hello-world-operator