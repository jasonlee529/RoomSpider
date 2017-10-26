#!/bin/bash
ps -ef|grep wx-pub|awk '{print $2}'|xargs kill -9

git pull

nohup gulp serve &
