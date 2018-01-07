$(document).ready(function () {
  $.get('http://116.196.86.186:18080/data/sql/1', function (res) {
    var d = res.result[0];
    var data = [{clazz: 'user', title: '总成交量', value: d.total_deal},
      {clazz: 'clock-o', title: '成交时间', value: d.last_deal_date, subTitle: '最早成交时间', value2: d.min_deal_date},
      {clazz: 'clock-o', title: '成交爬取爬取时间', value: d.last_craw_time.substr(0,10),value2:d.last_craw_time.substr(10)},
      {clazz: 'clock-o', title: '总二手房量', value: d.total_ershoufang},
      {clazz: 'clock-o', title: '二手房爬取时间', value: d.max_list_craw.substr(0,10),value2:d.max_list_craw.substr(10)},
      {clazz: 'clock-o', title: '报价爬取时间', value: d.max_baojia_craw.substr(0,10),value2:d.max_baojia_craw.substr(10)}
    ]
    $('#totalKpi').empty();
    data.forEach(function (n) {
      $('#totalKpi').append(kpiTpl(n));
    })
  });
})
