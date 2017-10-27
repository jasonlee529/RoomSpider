#!/bin/bash
ps -ef|grep gulp|awk '{print $2}'|xargs kill -9

git pull

nohup gulp serve &
