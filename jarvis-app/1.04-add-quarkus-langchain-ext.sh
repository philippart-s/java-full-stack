#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Add LangChain4J extension
quarkus ext add io.quarkiverse.langchain4j:quarkus-langchain4j-mistral-ai:0.25.0