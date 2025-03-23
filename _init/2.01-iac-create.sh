#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 15: $(basename "$0")

mkdir ./jarvis-iac
cd ./jarvis-iac

# Login locally at Pulumi
pulumi login --local

# Create a new project
pulumi new -o java-jbang --force