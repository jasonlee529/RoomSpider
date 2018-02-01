package cn.lee.housing.spider.lianjia.service.wangqian;

import java.util.HashMap;
import java.util.Map;

import cn.lee.housing.spider.lianjia.model.wangqian.*;
import cn.lee.housing.spider.lianjia.repository.wangqian.*;
import cn.lee.housing.spider.lianjia.spider.MySpider;
import cn.lee.housing.spider.lianjia.spider.pipeline.wangqian.WangqianPipeline;
import cn.lee.housing.spider.lianjia.spider.processor.wangqian.WangqianProcessor;
import cn.lee.housing.spider.lianjia.spider.proxy.XdailiProxyProvider;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 18-2-1.
 */
@Service
public class WangqianService {

    @Autowired
    private XdailiProxyProvider proxyProvider;

    @Autowired
    private WangqianPipeline wangqianPipeline;
    @Autowired
    private DayInfoDao dayInfoDao;
    @Autowired
    private MonthAgentDao monthAgentDao;
    @Autowired
    private MonthAreaDao monthAreaDao;
    @Autowired
    private MonthCountyDao monthCountyDao;
    @Autowired
    private MonthInfoDao monthInfoDao;

    public void saveDayInfo(DayInfo dayInfo) {
        dayInfoDao.save(dayInfo);
    }

    public void saveMonthAgent(MonthAgent obj) {
        monthAgentDao.save(obj);
    }

    public void saveMonthArea(MonthArea obj) {
        monthAreaDao.save(obj);
    }

    public void saveMonthCounty(MonthCounty obj) {
        monthCountyDao.save(obj);
    }

    public void saveMonthInfo(MonthInfo obj) {
        monthInfoDao.save(obj);
    }

    public Map startCrawl() {
        Map result = new HashMap();
        boolean isSuccess = true;
        try {
            HttpClientDownloader downloader = new HttpClientDownloader();
            downloader.setProxyProvider(proxyProvider);
            Spider spider = MySpider.create(new WangqianProcessor())
                    .setScheduler(new PriorityScheduler())
                    .addPipeline(wangqianPipeline)
                    .addPipeline(new ConsolePipeline())
                    .addUrl("http://210.75.213.188/shh/portal/bjjs/index.aspx");
            spider.setDownloader(downloader);
            spider.thread(10).start();//启动爬虫
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
            result.put("desc", e.getMessage());
        }

        result.put("success", isSuccess);
        return result;
    }
}
