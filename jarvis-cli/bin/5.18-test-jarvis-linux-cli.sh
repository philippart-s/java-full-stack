#!/bin/bash

# Load enviroment variables
set -a
source ../../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Use CLI
./jarvis-linux "Est-ce que Stéphane Philippart a des talks à Devoxx France ?"