#!/bin/bash

SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )

ls

# Source environment variables
set -a
source $SCRIPT_DIR/.talk.env
source $SCRIPT_DIR/.env
set +a