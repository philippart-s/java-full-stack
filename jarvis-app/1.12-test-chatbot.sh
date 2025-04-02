#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10:12 $(basename "$0")

# Test chatbot
curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire ce qu'est la conférence Devoxx France s'il te plaît ?" http://localhost:8080/chatbot-api

echo ""
echo ""

read -n 1 -p "Press any key to continue"
clear

bat -P -r 18: $(basename "$0")

curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire si Stéphane Philippart a des talks à Devoxx ?" http://localhost:8080/chatbot-api