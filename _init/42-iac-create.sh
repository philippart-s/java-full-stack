#!/bin/bash

# Load enviroment variables
source ../.env

clear

bat -P -r 10: $(basename "$0")

mkdir ./jarvis_iac
cd ./jarvis_iac

# Login locally at Pulumi
pulumi login --local

# Create a new project
pulumi new -o java-jbang --force