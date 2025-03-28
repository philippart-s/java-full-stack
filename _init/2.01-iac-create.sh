#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 13: $(basename "$0")

mkdir ./jarvis-iac
cd ./jarvis-iac

# Login locally at Pulumi
pulumi login --local

# Create a new project
pulumi new -o java-jbang --force