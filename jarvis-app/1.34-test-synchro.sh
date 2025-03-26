#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12:14 $(basename "$0")

# Test synchronisation
curl http://localhost:8080/devoxx-talks/synchro