$(document).ready(function () {
  $.get('http://116.196.86.186:18080/data/sql/1', function (res) {
    var d = res.result[0];
    var data = [{clazz: 'user', title: '总成交量', value: d.total_deal},
      {clazz: 'clock-o', title: '最新成交时间', value: d.last_deal_date, subTitle: '最早成交时间', value2: d.min_deal_date},
      {clazz: 'clock-o', title: '成交爬取爬取时间', value: d.last_craw_time.substr(0, 10), value2: d.last_craw_time.substr(10)},
      {clazz: 'clock-o', title: '总挂牌量', value: d.total_ershoufang},
      {clazz: 'clock-o', title: '二手房爬取时间', value: d.max_list_craw.substr(0, 10), value2: d.max_list_craw.substr(11)},
      {clazz: 'clock-o', title: '报价爬取时间', value: d.max_baojia_craw.substr(0, 10), value2: d.max_baojia_craw.substr(10)}
    ]
    $('#totalKpi').empty();
    data.forEach(function (n) {
      $('#totalKpi').append(kpiTpl(n));
    })
  });

  //最近成交趋势
  $.get('http://116.196.86.186:18080/data/sql/6', function (res) {
    var days = [], listAmount = [], dealAmount = [];
    res.result.reverse().forEach(function (n) {
      days.push(n.date);
      listAmount.push(n.list_amount);
      if(n.deal_amount!=0){
        dealAmount.push(n.deal_amount);
      }
    });
    var option = {
      title: {
        x:'center',
        text: '最近百日挂牌/成交数量对比'
      },
      tooltip: {
        trigger: 'axis'
      },
      grid: {
        x: 40,
        x2: 40
      },
      legend: {
        x:'center',
        y:30,
        data: ['挂牌量', '成交量']
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
          boundaryGap: false,
          data: days
        }
      ],
      yAxis: [
        {
          type: 'value', name: '数量',
          axisLabel: {
            formatter: '{value} '
          }
        }
      ],
      series: [
        {
          name: '挂牌量',
          type: 'line',
          data: listAmount,
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
          name: '成交量',
          type: 'line',
          data: dealAmount,
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
  });
  //最近挂牌趋势
  $.get('http://116.196.86.186:18080/data/sql/7', function (res) {
    var days = [], list = [], deal = [];
    res.result.reverse().forEach(function (n) {
      days.push(n.date);
      list.push(n.list_avg);
      if(n.deal_avg!=0){
        deal.push(n.deal_avg);
      }
    });
    var option2 = {
      title: {
        x:'center',
        text: '最近百日挂牌/成交均价对比'
      },
      tooltip: {
        trigger: 'axis'
      }, grid: {
        x: 60,
        x2: 60
      },
      legend: {
        x:'center',
        y:30,
        data: ['挂牌均价', '成交均价']
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
          name: '均价(元/平米)',
          min:40000,
          axisLabel: {
            formatter: '{value} '
          }
        }
      ],
      series: [
        {
          name: '挂牌均价',
          type: 'line',
          data: list,
          markPoint: {
            data: [
              {type: 'max', name: '最大值',symbolSize:[60,30],symbol:'rect'},
              {type: 'min', name: '最小值',symbolSize:[60,30],symbol:'rect'}
            ]
          },
          markLine: {
            data: [
              {type: 'average', name: '平均值'}
            ]
          }
        }, {
          name: '成交均价',
          type: 'line',
          data: deal,
          markPoint: {
            data: [
              {type: 'max', name: '最大值',symbolSize:[100,60]},
              {type: 'min', name: '最小值',symbolSize:[100,60]}
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
    var echartList = echarts.init(document.getElementById('day_list_trend'));
    echartList.setOption(option2, true);
  })


})
