package cn.lee.housing.spider.lianjia;

import cn.lee.housing.spider.lianjia.service.ChengjiaoPipeline;
import cn.lee.housing.spider.lianjia.spider.ChengjiaoProcessor;
import cn.lee.housing.spider.lianjia.spider.page.proxy.MipuProxyProvider;
import cn.lee.housing.spider.lianjia.spider.process.proxy.kuai.KuaiProxyProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.proxy.ProxyProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by jason on 17/7/14.
 */
@Configuration
@ComponentScan(value={"cn.lee.housing"})
@EnableAutoConfiguration
@ImportResource(locations = "classpath*:applicationContext.xml")
@EnableWebMvc
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        // ershoufangSpider();
    }

    private static void ershoufangSpider() {
        try {
            ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
            ChengjiaoPipeline pipeline1 = act.getBean(ChengjiaoPipeline.class);
            ChengjiaoProcessor cjProcessor = act.getBean(ChengjiaoProcessor.class);
            HttpClientDownloader downloader = new HttpClientDownloader();
            ProxyProvider mipuProxy = act.getBean(MipuProxyProvider.class);
            downloader.setProxyProvider(mipuProxy);
            Spider spider = Spider.create(cjProcessor)
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline1)
                    .addUrl("https://bj.lianjia.com/chengjiao/changping");
            spider.setDownloader(downloader);
            spider.thread(5).start();//启动爬虫
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void proxySpider() {
        KuaiProxyProcessor.getSpider().run();
    }
}
