#!/bin/bash
ps -ef|grep room.lianjia|awk '{print $2}'|xargs kill -9

git pull

mvn clean package

nohup  java -jar target/room.lianjia-*.jar >t.log & tail -11f t.log

sleep 10s;

curl localhost:18080/lianjia/chengjiao/changping &
