#!/bin/bash
ps -ef|grep room.lianjia|awk '{print $2}'|xargs kill -9

git pull

mvn clean package
JVM_OPT="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/admin/logs/java.hprof"
nohup  java $JVM_OPTS -jar target/room.lianjia-*.jar  >>t.log & tail -11f t.log

sleep 10s;

curl localhost:18080/lianjia/chengjiao/changping &
