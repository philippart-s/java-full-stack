#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Test mks Jarvis
curl -X GET http://n7dxqz.nodes.c1.gra7.k8s.ovh.net:30080/devoxx-talks/synchro


curl -N -X POST -H "Content-Type: text/plain" -d "Bonjour, peux-tu me dire si Stéphane Philippart a des talks à Devoxx ?" http://n7dxqz.nodes.c1.gra7.k8s.ovh.net:30080/chatbot-api