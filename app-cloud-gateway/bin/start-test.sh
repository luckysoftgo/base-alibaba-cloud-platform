#!/bin/bash
CURR_DIR=`dirname $0`
cd $CURR_DIR
if [ ! -d "../logs" ]; then
  mkdir ../logs
fi
rm -f tpid
nohup java -jar ../app-cloud-gateway-1.0.0-RELEASE.jar --server.port=8080 --spring.profiles.active=test 1>/dev/null 2>> ../logs/nohup-err.out &
echo $! > tpid
echo Project Start Success!