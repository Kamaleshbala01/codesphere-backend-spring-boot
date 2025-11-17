#!/bin/bash

timeout 1s python3 Main.py < input.txt > output.txt 2> runtime_err.log
STATUS=$?

if [ $STATUS -eq 124 ]; then
    echo "2 Time Limit Exceeded"
elif [ $STATUS -ne 0 ]; then
    echo "3 Runtime Error"
    cat runtime_err.log
else
    echo "0 "
    cat output.txt
fi
