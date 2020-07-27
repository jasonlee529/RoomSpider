select  deal_date,count(1)  from lianjia_chengjiao t
group by deal_date
order by deal_date desc;


select  left(deal_date,4),count(1)  from lianjia_chengjiao t
group by left(deal_date,4)
order by left(deal_date,4) desc;