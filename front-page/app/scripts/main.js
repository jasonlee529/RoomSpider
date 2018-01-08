$(document).ready(function () {
  $.get('http://116.196.86.186:18080/data/sql/1', function (res) {
    var d = res.result[0];
    var data = [{clazz: 'user', title: '总成交量', value: d.total_deal},
      {clazz: 'clock-o', title: '成交时间', value: d.last_deal_date, subTitle: '最早成交时间', value2: d.min_deal_date},
      {clazz: 'clock-o', title: '成交爬取爬取时间', value: d.last_craw_time.substr(0, 10), value2: d.last_craw_time.substr(10)},
      {clazz: 'clock-o', title: '总二手房量', value: d.total_ershoufang},
      {clazz: 'clock-o', title: '二手房爬取时间', value: d.max_list_craw.substr(0, 10), value2: d.max_list_craw.substr(10)},
      {clazz: 'clock-o', title: '报价爬取时间', value: d.max_baojia_craw.substr(0, 10), value2: d.max_baojia_craw.substr(10)}
    ]
    $('#totalKpi').empty();
    data.forEach(function (n) {
      $('#totalKpi').append(kpiTpl(n));
    })
  });

  //最近成交趋势
  $.get('http://116.196.86.186:18080/data/sql/4', function (res) {
    var legend = [], data = [];
    res.result.forEach(function (n) {
      legend.push(n.deal_date);
      data.push(n.amount);
    });
    var option = {
      title: {
        text: '最近百日成交数据'
      },
      tooltip: {
        trigger: 'axis'
      }, grid: {
        x2: 40
      },
      legend: {
        data: ['每日成交']
      },
      toolbox: {
        show: true,
        feature: {
          saveAsImage: {show: true}
        }
      },
      calculable: true,
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          data: legend
        }
      ],
      yAxis: [
        {
          type: 'value',
          axisLabel: {
            formatter: '{value} '
          }
        }
      ],
      series: [
        {
          name: '每日成交',
          type: 'line',
          data: data,
          markPoint: {
            data: [
              {type: 'max', name: '最大值'},
              {type: 'min', name: '最小值'}
            ]
          },
          markLine: {
            data: [
              {type: 'average', name: '平均值'}
            ]
          }
        }
      ]
    };
    var echartDeal = echarts.init(document.getElementById('day_deal_trend'));
    echartDeal.setOption(option, true);

  })
  //最近挂牌趋势
  $.get('http://116.196.86.186:18080/data/sql/5', function (res) {
    var legend = [], data = [];
    res.result.forEach(function (n) {
      legend.push(n.deal_date);
      data.push(n.amount);
    });
    var option = {
      title: {
        text: '最近百日挂牌数据'
      },
      tooltip: {
        trigger: 'axis'
      }, grid: {
        x2: 40
      },
      legend: {
        data: ['每日挂牌']
      },
      toolbox: {
        show: true,
        feature: {
          saveAsImage: {show: true}
        }
      },
      calculable: true,
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          data: legend
        }
      ],
      yAxis: [
        {
          type: 'value',
          axisLabel: {
            formatter: '{value} '
          }
        }
      ],
      series: [
        {
          name: '每日挂牌',
          type: 'line',
          data: data,
          markPoint: {
            data: [
              {type: 'max', name: '最大值'},
              {type: 'min', name: '最小值'}
            ]
          },
          markLine: {
            data: [
              {type: 'average', name: '平均值'}
            ]
          }
        }
      ]
    };
    var echartDeal = echarts.init(document.getElementById('day_list_trend'));
    echartDeal.setOption(option, true);
  })


})
