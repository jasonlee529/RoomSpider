#!/bin/bash
ps -ef|grep room.lianjia|awk '{print $2}'|xargs kill -9

git pull

mvn clean package

java -jar target/room.lianjia-1.0-SNAPSHOT.jar >>t.log &

curl localhost:18080/lianjia/chengjiao/changping &

tail -f t.log
