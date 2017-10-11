#!/bin/bash
echo 'do week '
mysql -u root -p'sa' --database room_spider   -e 'call p_lianjia_chengjiao_week()'
