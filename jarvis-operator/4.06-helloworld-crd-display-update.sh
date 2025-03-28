#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig.yml

# Display HelloWorls CRD update
kubectl get crds helloworldoperators.fullstack.wilda.fr -o json | jq '.spec.versions[0].schema.openAPIV3Schema.properties.spec'