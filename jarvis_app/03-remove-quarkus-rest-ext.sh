#!/bin/bash

# Load enviroment variables
source ../.env

clear

bat -P -r 10: $(basename "$0")

# Remove unused Quakus rest extension
quarkus ext remove quarkus-rest