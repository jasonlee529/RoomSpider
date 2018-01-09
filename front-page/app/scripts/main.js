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
    var legend = [], data = [], price = [];
    res.result.reverse().forEach(function (n) {
      legend.push(n.deal_date);
      data.push(n.amount);
      price.push(n.avg);
    });
    var option = {
      title: {
        text: '最近百日成交数据'
      },
      tooltip: {
        trigger: 'axis'
      },
      grid: {
        x: 40,
        x2: 60
      },
      legend: {
        data: ['成交量', '每平米均价']
      },
      toolbox: {
        show: true,
        feature: {
          saveAsImage: {show: true}
        }
      },
      xAxis: [
        {
          type: 'category',
          show: false,
          boundaryGap: false,
          data: legend
        }
      ],
      yAxis: [
        {
          type: 'value', name: "成交量",
          axisLabel: {
            formatter: '{value} '
          }
        }, {
          type: 'value', name: '每平米均价',
          axisLabel: {
            formatter: '{value} '
          }
        }
      ],
      series: [
        {
          name: '成交量',
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
        }, {
          name: '每平米均价',
          yAxisIndex: 1,
          type: 'line',
          data: price
        }
      ]
    };
    var echartDeal = echarts.init(document.getElementById('day_deal_trend'));
    echartDeal.setOption(option, true);
  });
  //最近挂牌趋势
  $.get('http://116.196.86.186:18080/data/sql/5', function (res) {
    var days = [], data = [], avg = [];
    res.result.reverse().forEach(function (n) {
      days.push(n.list_date);
      data.push(n.amount);
      avg.push(n.avg);
    });
    var option2 = {
      title: {
        text: '最近百日挂牌数据'
      },
      tooltip: {
        trigger: 'axis'
      }, grid: {
        x: 40,
        x2: 60
      },
      legend: {
        data: ['挂牌量', '挂牌均价']
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
          show: true,
          boundaryGap: false,
          data: days
        }
      ],
      yAxis: [
        {
          type: 'value',
          name: '挂牌量',
          axisLabel: {
            formatter: '{value} '
          }
        }, {
          type: 'value',
          name: '每平米均价',
          axisLabel: {
            formatter: '{value} '
          }
        }
      ],
      series: [
        {
          name: '挂牌量',
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
        }, {
          name: '挂牌均价',
          type: 'line',
          yAxisIndex: 1,
          data: avg
        }
      ]
    };
    var echartList = echarts.init(document.getElementById('day_list_trend'));
    echartList.setOption(option2, true);
  })


})
