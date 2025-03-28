#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# Create a tunnel to Jarvis app service on port 8181
kubectl expose deploy jarvis-deployment --name=jarvis-port-fwd-svc --target-port=8080 -n jarvis
kubectl port-forward service/jarvis-port-fwd-svc 8181:80 -n jarvis