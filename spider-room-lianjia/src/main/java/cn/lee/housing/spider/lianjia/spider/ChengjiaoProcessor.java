package cn.lee.housing.spider.lianjia.spider;

import java.util.List;

import cn.lee.housing.spider.lianjia.model.Ershoufang;
import cn.lee.housing.spider.lianjia.model.room.Chengjiao;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;


/**
 * Created by jason on 17-6-14.
 */
public class ChengjiaoProcessor implements PageProcessor {

    private Site site = Site.me().setCharset("utf-8").setRetryTimes(2).setCycleRetryTimes(15000).setSleepTime(5000).setDomain("https://bj.lianjia.com/");

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public final static String START_URL = "https://bj.lianjia.com/chengjiao/changping";
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

                Chengjiao chengjiao = new Chengjiao(fwId);
                chengjiao.setTotalPrice(html.xpath("").get());
                chengjiao.setAvgPrice(html.xpath("").get());
                chengjiao.setListPrice(html.xpath("").get());
                chengjiao.setCycle(html.xpath("").get());
                chengjiao.setTimes(html.xpath("").get());
                chengjiao.setInspectTimes(html.xpath("").get());
                chengjiao.setAttentionTimes(html.xpath("").get());
                chengjiao.setViewTimes(html.xpath("").get());
                // 具体爬去字段
                logger.info(ershoufang.toString());
                logger.info(chengjiao.toString());
                page.putField("ershoufang", ershoufang);
                page.putField("chengjiao", chengjiao);
            }
            logger.error("null page!");
        }

    }

    @Override
    public Site getSite() {
        return site;
    }


}
