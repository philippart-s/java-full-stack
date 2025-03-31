#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Add Ovhcloud provider
pulumi plugin install resource ovh v2.1.0 --server github://api.github.com/ovh/pulumi-ovh