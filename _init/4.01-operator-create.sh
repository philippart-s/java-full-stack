#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# First create the operator project
quarkus create app fr.wilda.fullstack:jarvis-operator-init -x='qosdk'