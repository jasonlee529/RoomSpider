SELECT
count(1) amount,
max(deal_date) max_deal_date,
min(deal_date) min_deal_date,
max(craw_time) max_craw_time,
min(craw_time) min_craw_time
FROM
lianjia_chengjiao
