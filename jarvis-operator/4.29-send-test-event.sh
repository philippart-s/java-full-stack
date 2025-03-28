#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Send a "GitHub" release event
curl --header "Content-Type: application/json" --request POST --data '{"ref": "v99", "ref_type": "tag"}' http://localhost:8080/webhook/event