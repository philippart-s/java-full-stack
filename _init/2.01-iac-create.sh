#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 13: $(basename "$0")

mkdir ./jarvis-iac-init
cd ./jarvis-iac-init

# Login locally at Pulumi
pulumi login --local

# Create a new project
pulumi new java-jbang -o --force