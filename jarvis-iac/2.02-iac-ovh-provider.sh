#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Add Ovhcloud provider
pulumi plugin install resource ovh v1.6.0 --server github://api.github.com/ovh/pulumi-ovh