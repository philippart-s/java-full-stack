#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Creat an app named jarvis (artifactId) with groupId 'fr.wilda.fullstack' and version '0.0.1-SNAPSHOT'
quarkus create app fr.wilda.fullstack:jarvis:0.0.1-SNAPSHOT