#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig-ovh.yml

# Create namespaces for jarvis operator and jarvis app
kubectl create ns jarvis-operator
kubectl create ns jarvis