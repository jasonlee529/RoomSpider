package cn.lee.housing.spider.lianjia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
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

    private final static String START_URL = "https://bj.lianjia.com/ershoufang";
    private final static String PAGE_URL = "https://bj\\.lianjia\\.com/ershoufang/pg\\d+";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(START_URL).match()) {
            //总共多少页的链接
            int total = Integer.parseInt(StringUtils.trim(page.getHtml().$("div.content div.leftContent h2.total ").xpath("span/text()").get()));
            int pageSize = 30;
            int maxPageNo = total / pageSize;
            List<String> pageList = Lists.newArrayList();
            for (int i = 1; i < 100; i++) {
                pageList.add(START_URL+"/pg"+i);
            }
            page.addTargetRequests(pageList);
        } else if (page.getUrl().regex(PAGE_URL).match()) {
            //列表页具体的爬去链接
            page.addTargetRequests(page.getHtml().$("div.leftContent div.info div.title a").links().all());
        } else {
            // 具体爬去字段
            String title = page.getHtml().xpath("div[@class=content]").xpath("div[@class=title]").xpath("h1").get();
            logger.info(title);
            page.putField("title",title );
        }

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
            String[] cols = StringUtils.split(inLine, ",");
            proxyList.add(new Proxy(cols[0], Integer.valueOf(cols[1])));

        }
        HttpClientDownloader downloader = new HttpClientDownloader();
        downloader.setProxyProvider(SimpleProxyProvider.from(proxyList.toArray(new Proxy[]{})));
        downloader.setProxyProvider(SimpleProxyProvider.from());
        Spider.create(new ErshoufangProcessor())
                .addUrl(START_URL)
                .addPipeline(new JsonFilePipeline("target/ershoufang.json"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
