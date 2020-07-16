#!/bin/bash

echo start sentinel-dashboard 

nohup java -Dserver.port=8718 -Dcsp.sentinel.dashboard.server=localhost:8718 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.7.2.jar 1>/dev/null 2>> nohup-err.out &

echo finish sentinel-dashboard 
