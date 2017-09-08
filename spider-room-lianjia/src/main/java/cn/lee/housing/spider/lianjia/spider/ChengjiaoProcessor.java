package cn.lee.housing.spider.lianjia.spider;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.lee.housing.spider.lianjia.model.Ershoufang;
import cn.lee.housing.spider.lianjia.model.room.Chengjiao;
import cn.lee.housing.spider.lianjia.service.room.ChengjiaoService;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by jason on 17-6-14.
 */
@Service
public class ChengjiaoProcessor implements PageProcessor {

    private Site site = Site.me().setCharset("utf-8").setRetryTimes(5).setCycleRetryTimes(15000).setSleepTime(5000).setDomain("bj.lianjia.com");

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public final static String START_URL = "https://bj.lianjia.com/chengjiao/changping";
    private final static String PAGE_URL = START_URL + "/pg\\d+";

    @Autowired
    private ChengjiaoService chengjiaoService;

    private final static String ROOM_ID = "[1-9]\\d+";

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<String> pageHref = html.xpath("//div[@class=page-box]/a").links().all();
        List<String> pageHrefs = html.xpath("//div[@class=pagination_group_a]").links().all();

        if (StringUtils.equalsIgnoreCase(START_URL, page.getUrl().get()) || page.getUrl().regex(PAGE_URL).match()) {
            //列表页具体的爬去链接
            page.addTargetRequests(page.getHtml().xpath("//div[@class='page-box']//a").links().all());
            List<String> urls = page.getHtml().xpath("//div[@class=leftContent]//ul//li//div[@class=title]//a").links().all();
            for (String str : urls) {
                String roomId = parseRoomId(str);
                if (StringUtils.isNotBlank(roomId) && !chengjiaoService.isExist(roomId)) {
                    page.addTargetRequest(new Request(str).setPriority(10L));
                }
            }
        } else {
            String fwId = parseRoomId(page.getUrl().get());
            if (StringUtils.isNotBlank(fwId)) {
                Ershoufang ershoufang = new Ershoufang();
                ershoufang.setFwId(fwId);
                ershoufang.setTitle(html.xpath("//div[@class=house-title]/div/text()").get());

                Chengjiao chengjiao = new Chengjiao(fwId);
                String t = html.xpath("//div[@class=house-title]/div/span/text()").get();
                String[] deal = StringUtils.split(html.xpath("//div[@class=house-title]/div/span/text()").get(), " ");
                chengjiao.setDealDate(deal != null && deal.length > 0 ? deal[0] : "");
                chengjiao.setDealAgent(deal != null && deal.length > 1 ? deal[1] : "");
                chengjiao.setTitle(ershoufang.getTitle());
                chengjiao.setTotalPrice(html.xpath("//div[@class=price]//span/i/text()").get());
                chengjiao.setAvgPrice(html.xpath("//div[@class=price]/b/text()").get());
                chengjiao.setListPrice(html.xpath("//div[@class=msg]//span[1]/label/text()").get());
                chengjiao.setCycle(html.xpath("//div[@class=msg]//span[2]/label/text()").get());
                chengjiao.setTimes(html.xpath("//div[@class=msg]//span[3]/label/text()").get());
                chengjiao.setInspectTimes(html.xpath("//div[@class=msg]//span[4]/label/text()").get());
                chengjiao.setAttentionTimes(html.xpath("//div[@class=msg]//span[5]/label/text()").get());
                chengjiao.setViewTimes(html.xpath("//div[@class=msg]//span[6]/label/text()").get());
                // 具体爬去字段
                logger.info(ershoufang.toString());
                logger.info(chengjiao.toString());
                page.putField("ershoufang", ershoufang);
                page.putField("chengjiao", chengjiao);
            } else {
                logger.error(fwId + "null page!");
            }
        }
        if (StringUtils.equalsIgnoreCase(START_URL, page.getUrl().get())) {
            //总共多少页的链接
            int total = 1000;//Integer.parseInt(StringUtils.trim(page.getHtml().xpath("//div[@class=resultDes]//div[@class=total]").xpath("//span/text()").get()));
            int pageSize = 30;
            int maxPageNo = total / pageSize + 1;
            List<String> pageList = Lists.newArrayList();
            for (int i = 1; i <= maxPageNo; i++) {
                pageList.add(START_URL + "/pg" + i);
            }
            page.addTargetRequests(pageList, 0L);
        }
    }

    private String parseRoomId(String url) {
        Pattern p = Pattern.compile(ROOM_ID);
        Matcher m = p.matcher(url);
        if (m.find()) {
            String roomId = m.group().replace(".html", "");
            return roomId;
        }
        return null;
    }

    @Override
    public Site getSite() {
        return site;
    }


}
