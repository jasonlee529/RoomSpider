package cn.lee.housing.spider.lianjia.service.room;

import java.util.HashMap;
import java.util.Map;

import cn.lee.housing.spider.lianjia.model.room.Chengjiao;
import cn.lee.housing.spider.lianjia.repository.ChengjiaoDao;
import cn.lee.housing.spider.lianjia.service.ChengjiaoPipeline;
import cn.lee.housing.spider.lianjia.spider.MySpider;
import cn.lee.housing.spider.lianjia.spider.processor.ChengjiaoProcessor;
import cn.lee.housing.spider.lianjia.spider.processor.ChengjiaoProcessorFactory;
import cn.lee.housing.spider.lianjia.spider.proxy.XdailiProxyProvider;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jason on 8/27/2017.
 */
@Service
public class ChengjiaoService {
    @Autowired
    private ChengjiaoDao chengjiaoDao;
    @Autowired
    private ChengjiaoPipeline pipeline;
    @Autowired
    private ChengjiaoProcessorFactory factory;
    @Autowired
    private XdailiProxyProvider proxyProvider;

    /**
     * 是否需要重新爬取，成交不存在或者有成交记录但需要重新爬的
     *
     * @param roomId
     * @return
     */
    public boolean isRecrawl(String roomId) {
        Chengjiao cj = chengjiaoDao.findByRoomId(roomId);
        return cj == null || cj.isReCrawl();
    }

    public Map doSpider(String area) {
        Map result = new HashMap();
        boolean isSuccess = true;
        try {
            HttpClientDownloader downloader = new HttpClientDownloader();
            downloader.setProxyProvider(proxyProvider);
            Spider spider = MySpider.create(factory.getObject(area))
                    .setScheduler(new PriorityScheduler())
                    .addPipeline(pipeline)
                    .addPipeline(new ConsolePipeline())
                    .addUrl(ChengjiaoProcessor.START_URL + ChengjiaoProcessorFactory.convertName(area));
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
