#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Use CLI
./target/jarvis-cli-0.0.1-SNAPSHOT-runner "Est-ce que Stéphane Philippart a des talks à Devoxx France ?"