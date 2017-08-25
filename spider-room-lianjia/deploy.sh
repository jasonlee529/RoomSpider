#!/bin/bash

mvn clean package

scp target/room.lianjia-1.0-SNAPSHOT.jar jd:/home/web
