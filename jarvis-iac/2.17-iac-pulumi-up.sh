#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# 🏗️  Create the infrastructure 🏗️
pulumi stack select devoxx
pulumi up

## 🛠️  Generate kubeconfig-ovh file 🛠️
echo "Kube ID: $(pulumi stack output kube_id)"
pulumi stack output kubeconfig --show-secrets --non-interactive > ../jarvis-operator/kubeconfig-ovh.yml

## ☸️ Set Kubernetes Node external IP ☸️
export KUBECONFIG=../jarvis-operator/kubeconfig-ovh.yml
echo "External Node IP: $(kubectl get nodes -o jsonpath='{.items[].status.addresses[].address}')"
NODE_IP=$(kubectl get nodes -o jsonpath='{.items[].status.addresses[].address}')
sed -i '' "s/^NODE_PUBLIC_IP=.*/NODE_PUBLIC_IP=$NODE_IP/" ../.talk.env

## 💿 DB configuration 💿
echo "Local IP: $(curl ipinfo.io/ip)"
echo "Database ID: $(pulumi stack output db_id --non-interactive)"
echo "Database Host: $(pulumi stack output db_host --non-interactive)"
echo "Database User: $(pulumi stack output db_user --non-interactive)"
echo "Database Password: $(pulumi stack output db_password --show-secrets --non-interactive)"

sed -i '' "s/^OVH_DB_HOST=.*/OVH_DB_HOST=$(pulumi stack output db_host --non-interactive)/" ../.talk.env
sed -i '' "s/^OVH_DB_PASSWORD=.*/OVH_DB_PASSWORD=$(pulumi stack output db_password --show-secrets --non-interactive)/" ../.talk.env

LOCAL_IP=$(curl ipinfo.io/ip)
DB_ID=$(pulumi stack output db_id --non-interactive)
sed -i '' "s/^OVH_DB_ID=.*/OVH_DB_ID=$DB_ID/" ../.talk.env
curl -X PUT "https://eu.api.ovh.com/v1/cloud/project/$OVH_CLOUD_PROJECT_SERVICE/database/postgresql/$DB_ID" \
 -H "accept: application/json"\
 -H "authorization: Bearer $OVHCLOUD_TOKEN"\
 -H "content-type: application/json" \
 -d "{\"ipRestrictions\":[{\"description\":\"kube_node_ip\",\"ip\":\"$NODE_IP/32\"}, {\"description\":\"local_ip\",\"ip\":\"$LOCAL_IP/32\"}]}" > /dev/null

