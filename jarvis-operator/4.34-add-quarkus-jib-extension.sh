#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 8: $(basename "$0")

# Add JIB extension
quarkus ext add io.quarkus:quarkus-container-image-jib