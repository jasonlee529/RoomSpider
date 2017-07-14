package cn.lee.housing.spider.lianjia;

import cn.lee.housing.spider.lianjia.service.ErshoufangPipeline;
import cn.lee.housing.spider.lianjia.spider.ErshoufangProcessor;
import cn.lee.housing.spider.lianjia.spider.ProxyPipeline;
import cn.lee.housing.spider.lianjia.spider.ProxyProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by jason on 17/7/14.
 */
public class Main {

    public static void main(String[] args) {
        ershoufangSpider();
    }

    private static void ershoufangSpider() {
        try {
            ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
            Pipeline pipeline = act.getBean(ErshoufangPipeline.class);
            Spider spider = Spider.create(new ErshoufangProcessor())
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline)
                    .addUrl(ErshoufangProcessor.START_URL);
            SpiderMonitor.instance().register(spider);
            spider.thread(1).start();//启动爬虫
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void proxySpider(String[] args) {
        Spider.create(new ProxyProcessor()).addPipeline(new ProxyPipeline()).addUrl("http://www.xicidaili.com/wt/").thread(3).run();
    }
}
