#!/bin/bash


COMPILE_ERR=$(javac Main.java 2>&1)
STATUS=$?

if [ $STATUS -ne 0 ]; then
    echo "1 Compilation Error"
    echo "$COMPILE_ERR"
    exit 1
fi

timeout 1s java Main < input.txt > output.txt 2> runtime_err.log
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
