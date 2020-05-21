#!/bin/bash
ps -ef|grep room.lianjia|awk '{print $2}'|xargs kill -9

git pull

mvn clean package
JVM_OPT="-Xmx1G -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/web/logs/java.hprof"
echo $JVM_OPT
nohup  java -jar -Xmx1G $JVM_OPT target/room.lianjia-*.jar  >>t.log & tail -11f t.log

sleep 10s;

curl localhost:18080/lianjia/chengjiao/changping &
