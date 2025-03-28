#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Add quarkus-rest-jackson Quarkus extension to do some REST call and expose some REST API
quarkus ext add quarkus-rest-jackson