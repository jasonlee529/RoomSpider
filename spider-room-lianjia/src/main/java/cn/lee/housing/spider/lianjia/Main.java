package cn.lee.housing.spider.lianjia;

import java.util.List;

import cn.lee.housing.spider.lianjia.service.ErshoufangPipeline;
import cn.lee.housing.spider.lianjia.service.proxy.ProxyService;
import cn.lee.housing.spider.lianjia.spider.ChengjiaoProcessor;
import cn.lee.housing.spider.lianjia.spider.process.proxy.kuai.KuaiProxyProcessor;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jason on 17/7/14.
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ershoufangSpider();
    }

    private static void ershoufangSpider() {
        try {
            ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
            Pipeline pipeline = act.getBean(ErshoufangPipeline.class);
            HttpClientDownloader downloader = new HttpClientDownloader();
            ProxyService proxyService = act.getBean(ProxyService.class);
            List<Proxy> proxies = proxyService.getProxyList();
            if (proxies != null && proxies.size() > 0) {
                downloader.setProxyProvider(SimpleProxyProvider.from(proxies.toArray(new Proxy[]{})));
            }
            Spider spider = Spider.create(new ChengjiaoProcessor())
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline)
                    .addUrl("https://bj.lianjia.com/chengjiao/changping");
            SpiderMonitor.instance().register(spider);
            spider.thread(3).start();//启动爬虫
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void proxySpider() {
        KuaiProxyProcessor.getSpider().run();
    }
}
