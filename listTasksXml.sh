#!/bin/sh
curl -s -H 'Accept: application/xml'  "http://localhost:8181/rest/tasks" | xmllint -format -
