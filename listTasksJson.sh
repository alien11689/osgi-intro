#!/bin/sh
curl -s -H 'Accept: application/json'  "http://localhost:8181/cxf/tasks" | jq .
