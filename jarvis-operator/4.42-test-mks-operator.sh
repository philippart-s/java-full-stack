#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig-ovh.yml

# Send a "GitHub" release event
curl --header "Content-Type: application/json" --request POST --data '{"ref": "92", "ref_type": "tag"}' http://51.178.60.185/webhook/event