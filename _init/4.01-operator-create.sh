#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# First create the operator project
quarkus create app fr.wilda.fullstack:jarvis-operator -x='qosdk'