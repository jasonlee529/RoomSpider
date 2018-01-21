package cn.lee.housing.spider.lianjia.service.room;

import java.util.HashMap;
import java.util.Map;

import cn.lee.housing.spider.lianjia.model.room.Baojia;
import cn.lee.housing.spider.lianjia.model.room.Ershoufang;
import cn.lee.housing.spider.lianjia.repository.room.BaojiaDao;
import cn.lee.housing.spider.lianjia.repository.room.ErshoufangDao;
import cn.lee.housing.spider.lianjia.spider.MySpider;
import cn.lee.housing.spider.lianjia.spider.pipeline.ErshoufangPipeline;
import cn.lee.housing.spider.lianjia.spider.processor.ErshoufangProcessor;
import cn.lee.housing.spider.lianjia.spider.processor.ErshoufangProcessorFactory;
import cn.lee.housing.spider.lianjia.spider.proxy.XdailiProxyProvider;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by jason on 17-9-29.
 */
@Service
public class ErshoufangService {

    @Autowired
    private ErshoufangDao dao;
    @Autowired
    private BaojiaDao baojiaoDao;
    @Autowired
    private ErshoufangPipeline pipeline;
    @Autowired
    private XdailiProxyProvider proxyProvider;
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
        Ershoufang cj = dao.findByRoomId(roomId);
        return cj == null || cj.isReCrawl();
    }


    public Baojia saveBaojia(Baojia baojia) {
        Baojia last = baojiaoDao.findFirstByRoomIdOrderByCrawTimeDesc(baojia.getRoomId());
        if (last == null || !StringUtils.equalsIgnoreCase(last.getPrice(), baojia.getPrice())) {
            baojiaoDao.save(baojia);
        }
        return baojia;
    }

    public Ershoufang saveErshoufang(Ershoufang ershoufang) {
        dao.save(ershoufang);
        return ershoufang;
    }

    public Ershoufang findByRoomId(String roomId) {
        return dao.findByRoomId(roomId);
    }
}
