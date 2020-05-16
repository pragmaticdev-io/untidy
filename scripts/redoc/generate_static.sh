#!/usr/bin/env bash
set -e
pip install pyyaml
API_VERSION=$(python3 -c 'import yaml; print(yaml.load(open("openapi.yaml", "r"))["info"]["version"])')

if [[ -n ${API_VERSION} ]]; then
  redoc-cli bundle -o docs/api/latest/index.html openapi.yaml
  redoc-cli bundle -o docs/api/${API_VERSION}/index.html openapi.yaml
fi
