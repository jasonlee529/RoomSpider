package cn.lee.housing.spider.lianjia.spider.pipeline.wangqian;

import cn.lee.housing.spider.lianjia.model.wangqian.*;
import cn.lee.housing.spider.lianjia.service.wangqian.WangqianService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 18-2-1.
 */
@Service
public class WangqianPipeline implements Pipeline {
    @Autowired
    private WangqianService wangqianService;

    @Override
    public void process(ResultItems resultItems, Task task) {
//        wangqianService.saveDayInfo((DayInfo) resultItems.get("dayInfo"));
//        wangqianService.saveMonthAgent((MonthAgent) resultItems.get("monthAgent"));
//        wangqianService.saveMonthArea((MonthArea) resultItems.get("monthArea"));
//        wangqianService.saveMonthCounty((MonthCounty) resultItems.get("monthCounty"));
//        wangqianService.saveMonthInfo((MonthInfo) resultItems.get("monthInfo"));
    }
}
