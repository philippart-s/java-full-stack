#!/bin/bash

# Load enviroment variables
source ../.env

clear

bat -P -r 10: $(basename "$0")

# Add Ovhcloud provider
pulumi plugin install resource ovh v1.6.0 --server github://api.github.com/ovh/pulumi-ovh