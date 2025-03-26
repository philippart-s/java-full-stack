#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Send a "GitHub" release event
curl --header "Content-Type: application/json" --request POST --data '{"ref": "v89", "ref_type": "tag"}' http://localhost:8080/webhook/event