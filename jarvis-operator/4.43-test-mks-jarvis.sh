#!/bin/bash

# Load environment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Test mks Jarvis
curl -X GET $JARVIS_API_URL/devoxx-talks/synchro

curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire si Stéphane Philippart a des talks à Devoxx ?" $JARVIS_API_URL/chatbot-api