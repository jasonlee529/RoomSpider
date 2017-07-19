package cn.lee.housing.spider.lianjia.spider;

import cn.lee.housing.spider.lianjia.model.Ershoufang;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jason on 17-6-14.
 */
public class ErshoufangProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(2).setCycleRetryTimes(15000).setSleepTime(5000).setDomain("https://bj.lianjia.com/");

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public final static String START_URL = "https://bj.lianjia.com/ershoufang/changping";
    private final static String PAGE_URL = START_URL + "/pg\\d+";

    @Override
    public void process(Page page) {
        if (StringUtils.equalsIgnoreCase(START_URL, page.getUrl().get())) {
            //总共多少页的链接
            int total = Integer.parseInt(StringUtils.trim(page.getHtml().$("div.content div.leftContent h2.total").xpath("span/text()").get()));
            int pageSize = 30;
            int maxPageNo = total / pageSize + 1;
            List<String> pageList = Lists.newArrayList();
            for (int i = 2; i <= maxPageNo; i++) {
                pageList.add(START_URL + "/pg" + i);
            }
            page.addTargetRequests(pageList);
        }
        if (page.getUrl().regex(PAGE_URL).match()) {
            //列表页具体的爬去链接
            Selectable selectable = page.getHtml().xpath("//div[@class='page-box']//a");
            Selectable links = selectable.links();
            page.addTargetRequests(page.getHtml().xpath("//div[@class='page-box']//a").links().all());

            page.addTargetRequests(page.getHtml().xpath("//div[@class=leftContent]//ul//li//div[@class=title]//a").links().all());
        } else {
            Html html = page.getHtml();
            String fwId = html.xpath("//div[@class=houseRecord]/span[@class=info]/text()").get();
            if (StringUtils.isNotBlank(fwId)) {
                Ershoufang ershoufang = new Ershoufang();
                ershoufang.setFwId(fwId);
                ershoufang.setTitle(html.xpath("div[@class=content]").xpath("div[@class=title]").xpath("h1/text()").get());
                ershoufang.setPrice(html.xpath("//div[@class=price]/span[@class=total]/text()").get());
                ershoufang.setAvgPrice(html.xpath("//div[@class=price]//span[@class=unitPriceValue]/text()").get());
                ershoufang.setBuildArea(html.xpath("//div[@class=area]//div[@class=mainInfo]/text()").get());
                ershoufang.setUseArea(html.xpath("//div[@class=price]//span[@class=total]/text()").get());
                ershoufang.setType(html.xpath("//div[@class=introContent]//div[@class=content]//ul//li[1]/text()").get());
                ershoufang.setFloor(html.xpath("//div[@class=introContent]//div[@class=content]//ul//li[2]/text()").get());
                ershoufang.setDecoration(html.xpath("//div[@class=introContent]//div[@class=content]//ul//li[9]/text()").get());
                ershoufang.setDrection(html.xpath("//div[@class=introContent]//div[@class=content]//ul//li[7]/text()").get());
                ershoufang.setBuidYear(html.xpath("//div[@class=area]//div[@class=subInfo]/text()").get());
                ershoufang.setRegion(html.xpath("//div[@class=aroundInfo]//div[@class=areaName]//span[@class=info]//tidyText()").get());
                // 具体爬去字段
                logger.info(ershoufang.toString());
                page.putField("ershoufang", ershoufang);
            }
            logger.error("null page!");
        }

    }

    @Override
    public Site getSite() {
        return site;
    }


}
