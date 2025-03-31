#!/bin/bash

# 🛠️ Load environment variables 🛠️
source ../0.01-source-env.sh

clear

bat -P -r 10: $(basename "$0")

# Check Picocli version
mvn dependency:tree -Dincludes=info.picocli