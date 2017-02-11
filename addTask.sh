#!/bin/sh
curl -vvv -s -XPOST "http://localhost:8181/rest/tasks?name=$1&description=$2"
