#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Creat an app named jarvis (artifactId) with groupId 'fr.wilda.fullstack' and version '0.0.1-SNAPSHOT'
quarkus create app fr.wilda.fullstack:jarvis-app-init:0.0.1-SNAPSHOT