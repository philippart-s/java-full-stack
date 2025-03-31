#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Test mks Jarvis
curl -X GET $JARVIS_API_URL/devoxx-talks/synchro

curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire si Stéphane Philippart a des talks à Devoxx ?" $JARVIS_API_URL/chatbot-api