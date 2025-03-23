#!/bin/bash

# Load enviroment variables
set -a
source ../.env
set +a

clear

bat -P -r 12: $(basename "$0")

# Check Picocli version
mvn dependency:tree -Dincludes=info.picocli