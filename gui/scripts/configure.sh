#!/bin/sh

target=/thoughts-gui/public/script/config/rest-api.js

apiUrl=$1
apiPort=$2
apiResource=$3

echo apiUrl='"'$apiUrl'"' > $target
echo apiPort='"'$apiPort'"' >> $target 
echo apiResource='"'$apiResource'"' >> $target
