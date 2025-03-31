#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# 🏗️  Create the infrastructure 🏗️
#pulumi up

# 🛠️  Set env variables 🛠️
sudo sed -i "s/^OVH_DB_HOST=.*/OVH_DB_HOST=$(pulumi stack output db_host --non-interactive)/" ../.talk.env
sudo sed -i "s/^OVH_DB_PASSWORD=.*/OVH_DB_PASSWORD=$(pulumi stack output avnadmin-password --show-secrets --non-interactive)/" ../.talk.env

## 🛠️  Generate kubeconfig-ovh file 🛠️
pulumi stack output kubeconfig --show-secrets --non-interactive > ../jarvis-operator/kubeconfig-ovh.yml

## ☸️ Set Kubernetes Node external IP ☸️
export KUBECONFIG=../jarvis-operator/kubeconfig-ovh.yml
NODE_IP=$(kubectl get nodes -o jsonpath='{.items[].status.addresses[].address}')
sudo sed -i "s/^NODE_PUBLIC_IP=.*/NODE_PUBLIC_IP=$NODE_IP/" ../.talk.env

## 💿 DB whitelist 💿
LOCAL_IP=$(curl ifconfig.me)
curl -X PUT "https://eu.api.ovh.com/v1/cloud/project/$OVH_CLOUD_PROJECT_SERVICE/database/postgresql/$OVH_DB_ID" \
 -H "accept: application/json"\
 -H "authorization: Bearer $OVHCLOUD_TOKEN"\
 -H "content-type: application/json" \
 -d "{\"ipRestrictions\":[{\"description\":\"kube_node_ip\",\"ip\":\"$NODE_IP/32\"}, {\"description\":\"local_ip\",\"ip\":\"$LOCAL_IP/32\"}]}" > /dev/null

