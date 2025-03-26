#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Add LangChain4J extension
quarkus ext add io.quarkiverse.langchain4j:quarkus-langchain4j-mistral-ai:0.25.0