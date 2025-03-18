#!/bin/bash

# Load enviroment variables
source ../.env

clear

bat -P -r 10: $(basename "$0")

# Add Qute extension to add frontend to Jarvis
quarkus ext add io.quarkiverse.qute.web:quarkus-qute-web