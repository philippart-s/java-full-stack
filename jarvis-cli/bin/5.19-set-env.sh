#!/bin/bash

export KUBECONFIG=../../jarvis-operator/kubeconfig-ovh.yml
export NODE_PUBLIC_IP=$(kubectl get nodes -o jsonpath='{.items[].status.addresses[].address}')

export JARVIS_API_URL=http://$NODE_PUBLIC_IP:30080
