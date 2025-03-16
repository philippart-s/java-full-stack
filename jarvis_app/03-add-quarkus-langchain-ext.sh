#!/bin/bash

# Load enviroment variables
source ../.env

clear

bat -P -r 10: $(basename "$0")

# Add quarkus-rest-jackson Quarkus extension to do some REST call and expose some REST API
quarkus ext add io.quarkiverse.langchain4j:quarkus-langchain4j-mistral-ai