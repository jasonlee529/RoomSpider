var kpiTpl = template.compile(
  ' <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">' +
  '<span class="count_top"><i class="fa fa-user fa-{{clazz}}"></i> {{title}}</span>' +
  '<div class="count">{{value}}</div>' +
  '<span class="count_bottom"><i class="green">{{value2}} </i> {{subTitle}}</span>' +
  '</div>');


var top10TrTpl = template.compile(
  '{{each $data.result}}' +
  '<tr>' +
  '<th scope="row">{{$index}}</th>' +
  '<td>{{$value.county}}</td>' +
  '<td>{{$value.region}}</td>' +
  '<td>{{$value.district}}</td>' +
  '<td>{{$value.total_price}}</td>' +
  '<td>{{$value.avg_price}}</td>' +
  '<td>{{$value.area}}</td>' +
  '<td>{{$value.build_year}}</td>' +
  '<td>{{$value.trading_right}}</td>' +
  '</tr>' +
  '{{/each}}'
);
