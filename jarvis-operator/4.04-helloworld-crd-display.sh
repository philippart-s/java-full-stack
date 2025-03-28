#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# List CRDs
kubectl get crds