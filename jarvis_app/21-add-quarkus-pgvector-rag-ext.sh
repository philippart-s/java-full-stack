#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Add PGVector and Easy RAG extensions to do RAG
quarkus ext add quarkus-langchain4j-pgvector, quarkus-langchain4j-easy-rag
