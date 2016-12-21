#!/bin/sh
curl -s -XPOST "http://localhost:8181/cxf/tasks?name=$1&description=$2" | xmllint -format -
