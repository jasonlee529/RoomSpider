#!/bin/bash
ps -ef|grep spider-data.jar|awk '{print $2}'|xargs kill -9

git pull

mvn clean package
JVM_OPT="-Xmx128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/web/logs/java.hprof"
echo $JVM_OPT
nohup  java -jar -Xmx128m target/spider-data.jar  >>t.log & tail -11f t.log

