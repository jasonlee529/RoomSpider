package cn.lee.housing.spider.lianjia.web.ershoufang;

import cn.lee.housing.spider.lianjia.service.ChengjiaoPipeline;
import cn.lee.housing.spider.lianjia.service.proxy.ProxyService;
import cn.lee.housing.spider.lianjia.spider.ChengjiaoProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.proxy.ProxyProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;


/**
 * Created by jason on 17-8-3.
 */
@RestController
@RequestMapping("/lianjia/chengjiao/")
public class ChengjiaoController {

    @Autowired
    private ChengjiaoPipeline pipeline;
    @Autowired
    private ProxyService proxyService;
    @Autowired
    private ChengjiaoProcessor cjProcessor;
    @Autowired
    private ProxyProvider mipuProxy;

    @RequestMapping(value = "{area}")
    public String area(@PathVariable String area) {
        try {
            HttpClientDownloader downloader = new HttpClientDownloader();
            downloader.setProxyProvider(mipuProxy);
            Spider spider = Spider.create(cjProcessor)
                    .setScheduler(new FileCacheQueueScheduler("/cache"))
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline)
                    .addUrl("https://bj.lianjia.com/chengjiao/changping");
            spider.setDownloader(downloader);
            spider.thread(10).start();//启动爬虫
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{success:true}";
    }
}
