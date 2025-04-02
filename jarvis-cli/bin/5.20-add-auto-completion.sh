#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../../0.01-source-env.sh

clear

bat -P -r 10:11 $(basename "$0")

# Activate the auto complete in bash
jarvis --help

read -n 1 -p "Press any key to continue"

clear

bat -P -r 19:19 $(basename "$0")

jarvis generate-completion --help

read -n 1 -p "Press any key to continue"

clear

bat -P -r 27: $(basename "$0")

source <(jarvis generate-completion)