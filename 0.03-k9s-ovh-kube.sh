#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ./0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# ☸️ K9s on local Kubernetes ☸️
export KUBECONFIG=./jarvis-operator/kubeconfig-ovh.yml
k9s