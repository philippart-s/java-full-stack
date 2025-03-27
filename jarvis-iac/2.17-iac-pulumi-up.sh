#!/bin/bash

# 🛠️  Load environment variables 🛠️
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# 🏗️  Create the infrastructure 🏗️
#pulumi up

# 🛠️  Set env variables 🛠️
sudo sed -i "s/^OVH_DB_HOST=.*/OVH_DB_HOST=$(pulumi stack output db_host --non-interactive)/" ../.env
sudo sed -i "s/^OVH_DB_PASSWORD=.*/OVH_DB_PASSWORD=$(pulumi stack output avnadmin-password --show-secrets --non-interactive)/" ../.env

## 🛠️  Generate kubeconfig-ovh file 🛠️
pulumi stack output kubeconfig --show-secrets --non-interactive > ../jarvis-operator/kubeconfig-ovh.yml