package cn.lee.housing.spider.lianjia;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by jason on 17-6-14.
 */
public class ErshoufangProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().xpath("//ul[@class='sellListContent']/text()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        Spider.create(new ErshoufangProcessor())
                //从"https://github.com/code4craft"开始抓
                .addUrl("https://bj.lianjia.com/ershoufang")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
