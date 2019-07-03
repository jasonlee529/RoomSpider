package cn.lee.housing.spider.lianjia.service.room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.lee.housing.spider.lianjia.model.room.Chengjiao;
import cn.lee.housing.spider.lianjia.repository.room.ChengjiaoDao;
import cn.lee.housing.spider.lianjia.spider.MySpider;
import cn.lee.housing.spider.lianjia.spider.pipeline.room.ChengjiaoPipeline;
import cn.lee.housing.spider.lianjia.spider.processor.room.ChengjiaoProcessor;
import cn.lee.housing.spider.lianjia.spider.processor.room.ChengjiaoProcessorFactory;
import cn.lee.housing.spider.lianjia.spider.proxy.CustomeProxyProvider;
import cn.lee.housing.spider.lianjia.spider.proxy.XdailiProxyProvider;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
    private CustomeProxyProvider proxyProvider;

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
     * @param processor
     * @return
     */
    private void doSpider(ChengjiaoProcessor processor) {
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setProxyProvider(proxyProvider);
        Spider spider = MySpider.create(processor)
                .setScheduler(new PriorityScheduler())
                .addPipeline(pipeline)
                .addPipeline(new ConsolePipeline())
                .addUrl(processor.getStartURL());
        spider.setDownloader(downloader);
        spider.thread(10).start();//启动爬虫

    }


    /**
     * 重新爬取库中的待爬取的数据
     *
     * @return
     */
    public Map reCrawl() {
        Map result = new HashMap();
        boolean isSuccess = true;
        try {
            HttpClientDownloader downloader = new HttpClientDownloader();
            downloader.setProxyProvider(proxyProvider);
            Spider spider = MySpider.create(factory.getObject("all"))
                    .setScheduler(new PriorityScheduler())
                    .addPipeline(pipeline)
                    .addPipeline(new ConsolePipeline());
            List<Chengjiao> ids = chengjiaoDao.findAll(new Specification<Chengjiao>() {
                @Override
                public Predicate toPredicate(Root<Chengjiao> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    return criteriaBuilder.equal(root.get("reCrawl"), true);
                }
            });
            for (Chengjiao cj : ids) {
                spider.addUrl("https://bj.lianjia.com/chengjiao/" + StringUtils.trim(cj.getRoomId()) + ".html");
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
