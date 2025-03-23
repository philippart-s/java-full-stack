#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12:14 $(basename "$0")

# Test chatbot
curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire ce qu'est la conférence Devoxx France s'il te plaît ?" http://localhost:8080/chatbot-api

read -n 1 -p "\nPress any key to continue"
clear

bat -P -r 20: $(basename "$0")

curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire si Stéphane Philippart a des talks à Devoxx ?" http://localhost:8080/chatbot-api