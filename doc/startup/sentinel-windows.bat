@echo off
title redis-server
set ENV_HOME="D:\sentinel"
D:
color 0a
cd %ENV_HOME%
java -Dserver.port=8718 -Dcsp.sentinel.dashboard.server=localhost:8718 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.7.2.jar
exit