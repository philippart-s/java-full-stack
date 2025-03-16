#!/bin/bash

# Load enviroment variables
source ../.env

clear

bat -P -r 10: $(basename "$0")

# Start Quarkus in dev mode
quarkus dev