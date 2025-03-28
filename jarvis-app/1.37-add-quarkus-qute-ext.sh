#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Add Qute extension to add frontend to Jarvis
quarkus ext add io.quarkiverse.qute.web:quarkus-qute-web