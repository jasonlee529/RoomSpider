package cn.lee.housing.spider.lianjia.web.ershoufang;

import cn.lee.housing.spider.lianjia.service.ChengjiaoPipeline;
import cn.lee.housing.spider.lianjia.service.proxy.ProxyService;
import cn.lee.housing.spider.lianjia.spider.ChengjiaoProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @RequestMapping(value = "{area}")
    public String area(@PathVariable String area) {
        try {
            HttpClientDownloader downloader = new HttpClientDownloader();
            downloader.setProxyProvider(SimpleProxyProvider.from(proxyService.getProxyList().toArray(new Proxy[]{})));
            Spider spider = Spider.create(new ChengjiaoProcessor())
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline)
                    .addUrl("https://bj.lianjia.com/chengjiao/changping");
            spider.setDownloader(downloader);
            spider.thread(3).start();//启动爬虫
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
