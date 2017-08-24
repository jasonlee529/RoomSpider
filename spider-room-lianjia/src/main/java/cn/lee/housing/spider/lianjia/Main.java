package cn.lee.housing.spider.lianjia;

import cn.lee.housing.spider.lianjia.service.ChengjiaoPipeline;
import cn.lee.housing.spider.lianjia.service.ErshoufangPipeline;
import cn.lee.housing.spider.lianjia.service.proxy.ProxyService;
import cn.lee.housing.spider.lianjia.spider.ChengjiaoProcessor;
import cn.lee.housing.spider.lianjia.spider.process.proxy.kuai.KuaiProxyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.util.List;

/**
 * Created by jason on 17/7/14.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource(locations = "classpath:applicationContext.xml")
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        ershoufangSpider();
    }

    private static void ershoufangSpider() {
        try {
            ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
            Pipeline pipeline = act.getBean(ErshoufangPipeline.class);
            ChengjiaoPipeline pipeline1 = act.getBean(ChengjiaoPipeline.class);
            HttpClientDownloader downloader = new HttpClientDownloader();
            ProxyService proxyService = act.getBean(ProxyService.class);
            List<Proxy> proxies = proxyService.getProxyList();
            if (proxies != null && proxies.size() > 0) {
                logger.info("proxyList size "+proxies.size());
                downloader.setProxyProvider(SimpleProxyProvider.from(proxies.toArray(new Proxy[]{})));
            }
            Spider spider = Spider.create(new ChengjiaoProcessor())
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline1)
                    .addUrl("https://bj.lianjia.com/chengjiao/changping");
            spider.setDownloader(downloader);
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
