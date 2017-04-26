#!/bin/sh

target=/thoughts-api/api-config.yml

dbURL=$1
dbPort=$2
dbName=$3
collectionName=$4

echo dbURL: '"'$dbURL'"' > $target
echo dbPort: '"'$dbPort'"' >> $target
echo dbName: '"'$dbName'"' >> $target
echo collectionName: '"'$collectionName'"' >> $target
