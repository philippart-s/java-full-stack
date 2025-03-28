#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig-ovh.yml

# Create namespaces for jarvis operator and jarvis app
kubectl create ns jarvis-operator
kubectl create ns jarvis