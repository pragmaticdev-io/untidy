#!/bin/bash
pip install cqlsh
function cassandra_ready() {
    count=0
    while ! cqlsh -e "describe cluster;" 2>&1 ; do
        echo "waiting for cassandra"
        if [ $count -gt 60 ]
        then
            exit
        fi
        (( count += 1 ))
        sleep 1
    done
    echo "cassandra is ready"
}

cassandra_ready
cqlsh -e "create keyspace unit with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};"