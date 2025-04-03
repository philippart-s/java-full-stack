#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Configure KUBECONFIG environment variable for kubectl command
export KUBECONFIG=./kubeconfig-ovh.yml

# ☸️ Get external IP of the LoadBalancer service
echo "Getting load balancer external IP..."

while [ -z "$LB_EXTERNAL_IP" ]; do
    echo "External IP not available, retrying..."
    sleep 1 
    LB_EXTERNAL_IP=$(kubectl get svc ovh-lb-service -n jarvis-operator -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
done

echo "External load balancer IP: $LB_EXTERNAL_IP"

# Send a "GitHub" release event
curl --header "Content-Type: application/json" --request POST --data '{"ref": "v1", "ref_type": "tag"}' http://$LB_EXTERNAL_IP/webhook/event