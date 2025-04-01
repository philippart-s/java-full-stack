#!/bin/bash

# Load environment variables
source ../../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Use CLI
./jarvis-macos "Est-ce que Stéphane Philippart a des talks à Devoxx France ?"