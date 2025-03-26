#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Test local Jarvis
curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire si Stéphane Philippart a des talks à Devoxx ?" http://localhost:8181/chatbot-api