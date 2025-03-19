#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Start Quarkus in dev mode
quarkus dev