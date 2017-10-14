#!/bin/bash
ps -ef|grep wx-pub|awk '{print $2}'|xargs kill -9

git pull

mvn clean package

nohup  java -jar target/*.jar -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false >t.log & tail -11f t.log

sleep 10s;

