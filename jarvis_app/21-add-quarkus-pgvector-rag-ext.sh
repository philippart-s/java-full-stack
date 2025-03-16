#!/bin/bash

# Load enviroment variables
source ../.env

clear

bat -P -r 10: $(basename "$0")

# Add PGVector and Easy RAG extensions to do RAG
quarkus ext add quarkus-langchain4j-pgvector, quarkus-langchain4j-easy-rag
