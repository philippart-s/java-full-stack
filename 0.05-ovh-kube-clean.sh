#!/bin/bash

clear

read -n 1 -p "⚠️⚠️⚠️⚠️⚠️ Supprime l'ensemble des données et applications ⚠️⚠️⚠️⚠️⚠️"

# 🛠️ Load environment variables 🛠️
source ./0.01-source-env.sh

bat -P -r 12: $(basename "$0")

export KUBECONFIG=./jarvis-operator/kubeconfig-ovh.yml

kubectl delete jarvisoperator.fullstack.wilda.fr check-release -n jarvis
kubectl delete ns jarvis
kubectl delete -f ./jarvis-operator/target/kubernetes/kubernetes.yml
kubectl delete crd/helloworldoperators.fullstack.wilda.fr
kubectl delete crd/jarvisoperators.fullstack.wilda.fr
kubectl delete ns jarvis-operator
