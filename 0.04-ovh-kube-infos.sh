#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ./0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# ☸️ K9s on local Kubernetes ☸️
export KUBECONFIG=./jarvis-operator/kubeconfig-ovh.yml
echo "LB IP: $(kubectl get svc ovh-lb-service -n jarvis-operator -o jsonpath='{.status.loadBalancer.ingress[0].ip}')"
echo "Node IP : $(kubectl get nodes -o jsonpath='{.items[].status.addresses[].address}')"