package cn.lee.housing.spider.lianjia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * Created by jason on 17-6-14.
 */
public class ErshoufangProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void process(Page page) {
        logger.error("======================");
        logger.info(page.getHtml().$("div.leftContent").$("div.info div.title a").links().all().toString());
        logger.info(page.getHtml().links().regex("https://bj.lianjia.com/ershoufang/[\\d$].html").all().toString());

        page.addTargetRequests(page.getHtml().$("div.leftContent div.info div.title a").links().all());

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws IOException {
        Resource resource = new ClassPathResource("proxy.txt");
        BufferedReader fr = new BufferedReader(new FileReader(resource.getFile()));
        String inLine = null;
        List<Proxy> proxyList = new ArrayList<Proxy>();
        while ((inLine = fr.readLine()) != null) {
            String[] cols = StringUtils.split(inLine,",");
            proxyList.add(new Proxy(cols[0], Integer.valueOf(cols[1])));

        }
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setProxyProvider(SimpleProxyProvider.from(proxyList.toArray(new Proxy[]{})));
        downloader.setProxyProvider(SimpleProxyProvider.from());
        Spider.create(new ErshoufangProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://bj.lianjia.com/ershoufang/")
                .addPipeline(new Pipeline() {
                    @Override
                    public void process(ResultItems resultItems, Task task) {

                    }
                })
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
