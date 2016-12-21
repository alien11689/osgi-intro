#!/bin/sh
curl -s "http://localhost:8181/cxf/tasks" | xmllint -format -
