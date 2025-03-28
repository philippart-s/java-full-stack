#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Start Quarkus in dev mode
quarkus dev #-Dquarkus.profile=prod