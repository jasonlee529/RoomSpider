package cn.lee.housing.spider.lianjia.service.room;

import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaBaojia;
import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaChengjiao;
import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaErshoufang;
import cn.lee.housing.spider.lianjia.repository.room.lianjia.LianjiaBaojiaMapper;
import cn.lee.housing.spider.lianjia.repository.room.lianjia.LianjiaErshoufangMapper;
import cn.lee.housing.spider.lianjia.spider.MySpider;
import cn.lee.housing.spider.lianjia.spider.pipeline.room.ErshoufangPipeline;
import cn.lee.housing.spider.lianjia.spider.processor.room.ErshoufangProcessor;
import cn.lee.housing.spider.lianjia.spider.processor.room.ErshoufangProcessorFactory;
import cn.lee.housing.spider.lianjia.spider.proxy.CustomeProxyProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by jason on 17-9-29.
 */
@Service
public class ErshoufangService {

    @Autowired
    private LianjiaErshoufangMapper dao;
    @Autowired
    private LianjiaBaojiaMapper baojiaoDao;
    @Autowired
    private ErshoufangPipeline pipeline;
    @Autowired
    private CustomeProxyProvider proxyProvider;
    @Autowired
    private ErshoufangProcessorFactory factory;

    public Map spiderDay(String area) {
        Map result = new HashMap();
        boolean isSuccess = true;
        try {
            doSpider(factory.getObject(area));

        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
            result.put("desc", e.getMessage());
        }

        result.put("success", isSuccess);
        return result;
    }

    public Map spiderDepth() {
        Map result = new HashMap();
        boolean isSuccess = true;
        try {
            doSpider(factory.getMoreProcessor());
        } catch (Exception e) {
            isSuccess = false;
            e.printStackTrace();
            result.put("desc", e.getMessage());
        }

        result.put("success", isSuccess);
        return result;
    }

    /**
     * 爬取
     *
     * @return
     */
    public void doSpider(ErshoufangProcessor processor) {
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setProxyProvider(proxyProvider);
        Spider spider = MySpider.create(processor)
                .setScheduler(new PriorityScheduler())
                .addPipeline(pipeline)
                .addPipeline(new ConsolePipeline())
                .addUrl(processor.getStartUrl());
        spider.setDownloader(downloader);
        spider.thread(10).start();//启动爬虫
    }

    public boolean isRecrawl(String roomId) {
        LianjiaErshoufang cj = dao.findByRoomId(roomId);
        return cj == null || cj.getReCrawl();
    }


    public LianjiaBaojia saveBaojia(LianjiaBaojia baojia) {
        LianjiaBaojia last = baojiaoDao.findFirstByRoomIdOrderByCrawTimeDesc(baojia.getRoomId());
        if (last == null || !StringUtils.equalsIgnoreCase(last.getPrice(), baojia.getPrice())) {
            baojiaoDao.insertSelective(baojia);
        }
        return baojia;
    }

    public LianjiaErshoufang saveErshoufang(LianjiaErshoufang ershoufang) {
        if (ershoufang.getId() == null) {
            dao.insertSelective(ershoufang);
        } else {
            dao.updateByPrimaryKeySelective(ershoufang);
        }
        return ershoufang;
    }

    public LianjiaErshoufang updateByRoomId(LianjiaErshoufang ershoufang) {
        dao.updateByRoomId(ershoufang);
        return ershoufang;
    }


    public LianjiaErshoufang findByRoomId(String roomId) {
        return dao.findByRoomId(roomId);
    }

    public Map spiderBaojia() {
        Map result = new HashMap();
        boolean isSuccess = true;
        try {
            HttpClientDownloader downloader = new HttpClientDownloader();
            downloader.setProxyProvider(proxyProvider);
            PriorityScheduler scheduler = new PriorityScheduler();
            Spider spider = MySpider.create(factory.getBaojiaProcessor())
                    .setScheduler(scheduler)
                    .addPipeline(pipeline)
                    .addPipeline(new ConsolePipeline());
            List<LianjiaErshoufang> ids = dao.findByStatus();
            for (LianjiaErshoufang cj : ids) {
                spider.addUrl("https://bj.lianjia.com/ershoufang/" + StringUtils.trim(cj.getRoomId()) + ".html");
            }
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
