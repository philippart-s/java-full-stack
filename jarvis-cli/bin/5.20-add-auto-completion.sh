#!/bin/bash

# Load enviroment variables
set -a
source ../../.env
set +a

clear

bat -P -r 13:13 $(basename "$0")

# Activate the auto complete in bash
./jarvis --help

read -n 1 -p "Press any key to continue"

clear

bat -P -r 21:21 $(basename "$0")

./jarvis generate-completion --help

read -n 1 -p "Press any key to continue"

clear

bat -P -r 29: $(basename "$0")

source <(./jarvis generate-completion)