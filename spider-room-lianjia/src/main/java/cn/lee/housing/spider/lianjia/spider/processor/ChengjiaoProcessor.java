package cn.lee.housing.spider.lianjia.spider.processor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.lee.housing.spider.lianjia.model.room.Ershoufang;
import cn.lee.housing.spider.lianjia.model.room.Chengjiao;
import cn.lee.housing.spider.lianjia.service.room.ChengjiaoService;
import cn.lee.housing.spider.lianjia.spider.PageProcessException;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import org.springframework.stereotype.Service;


/**
 * Created by jason on 17-6-14.
 */
@Service
public class ChengjiaoProcessor implements PageProcessor {

    private Site site = Site.me().setCharset("utf-8").setRetryTimes(10).setCycleRetryTimes(200).setSleepTime(1000).setDomain("bj.lianjia.com");

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static String START_URL = "https://bj.lianjia.com/chengjiao/";

    private String county = "";

    private ChengjiaoService chengjiaoService;

    public String getPageUrl() {
        return getStartURL() + "/pg\\d+";
    }

    public String getStartURL() {
        return START_URL + county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public void setChengjiaoService(ChengjiaoService chengjiaoService) {
        this.chengjiaoService = chengjiaoService;
    }

    private final static String ROOM_ID = "[1-9]\\d+";

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        List<String> pageHref = html.xpath("//div[@class=page-box]/a").links().all();
        List<String> pageHrefs = html.xpath("//div[@class=pagination_group_a]").links().all();

        if (StringUtils.equalsIgnoreCase(getStartURL(), page.getUrl().get()) || page.getUrl().regex(getPageUrl()).match()) {
            //列表页具体的爬去链接
            page.addTargetRequests(page.getHtml().xpath("//div[@class='page-box']//a").links().all());
            List<String> urls = page.getHtml().xpath("//div[@class=leftContent]//ul//li//div[@class=title]//a").links().all();
            for (String str : urls) {
                String roomId = parseRoomId(str);
                if (StringUtils.isNotBlank(roomId) && chengjiaoService.isRecrawl(roomId)) {
                    Request request = new Request(str).setPriority(Long.parseLong(roomId));
                    page.addTargetRequest(request);
                    logger.error(" add Request : " + request);
                }
            }
        } else {
            String fwId = parseRoomId(page.getUrl().get());
            String fwId2 = html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[1]/text()").get();
            if (StringUtils.isNotBlank(fwId) && StringUtils.isNotBlank(fwId2)) {
                Chengjiao chengjiao = new Chengjiao(fwId);
                String[] deal = StringUtils.split(html.xpath("//div[@class=house-title]/div/span/text()").get(), " ");
                chengjiao.setDealDate(deal != null && deal.length > 0 ? deal[0] : "");
                chengjiao.setDealAgent(deal != null && deal.length > 1 ? deal[1] : "");
                chengjiao.setTitle(html.xpath("//div[@class=house-title]/div/text()").get());
                chengjiao.setTotalPrice(html.xpath("//div[@class=price]//span/i/text()").get());
                chengjiao.setAvgPrice(html.xpath("//div[@class=price]/b/text()").get());
                chengjiao.setListPrice(html.xpath("//div[@class=msg]//span[1]/label/text()").get());
                chengjiao.setCycle(html.xpath("//div[@class=msg]//span[2]/label/text()").get());
                chengjiao.setTimes(html.xpath("//div[@class=msg]//span[3]/label/text()").get());
                chengjiao.setInspectTimes(html.xpath("//div[@class=msg]//span[4]/label/text()").get());
                chengjiao.setAttentionTimes(html.xpath("//div[@class=msg]//span[5]/label/text()").get());
                chengjiao.setViewTimes(html.xpath("//div[@class=msg]//span[6]/label/text()").get());

                //quyue
                chengjiao.setCounty(subCjjg(html.xpath("//div[@class=deal-bread]//a[3]/text()").get()));
                chengjiao.setRegion(subCjjg(html.xpath("//div[@class=deal-bread]//a[4]/text()").get()));
                chengjiao.setReCrawl(false);
                chengjiao.setDistrict(StringUtils.split(chengjiao.getTitle(), " ")[0]);

                //jiben
                chengjiao.setHuxing(html.xpath("//div[@class=base]//div[@class=content]//li[1]/text()").get());
                chengjiao.setLouceng(html.xpath("//div[@class=base]//div[@class=content]//li[2]/text()").get());
                chengjiao.setArea(html.xpath("//div[@class=base]//div[@class=content]//li[3]/text()").get());
                chengjiao.setrJiegou(html.xpath("//div[@class=base]//div[@class=content]//li[4]/text()").get());
                chengjiao.setInnerArea(html.xpath("//div[@class=base]//div[@class=content]//li[5]/text()").get());
                chengjiao.setBuildType(html.xpath("//div[@class=base]//div[@class=content]//li[6]/text()").get());
                chengjiao.setOrientation(html.xpath("//div[@class=base]//div[@class=content]//li[7]/text()").get());
                chengjiao.setBuildYear(html.xpath("//div[@class=base]//div[@class=content]//li[8]/text()").get());
                chengjiao.setZhuangxiu(html.xpath("//div[@class=base]//div[@class=content]//li[9]/text()").get());
                chengjiao.setbJiegou(html.xpath("//div[@class=base]//div[@class=content]//li[10]/text()").get());
                chengjiao.setGongnuan(html.xpath("//div[@class=base]//div[@class=content]//li[11]/text()").get());
                chengjiao.setTihu(html.xpath("//div[@class=base]//div[@class=content]//li[12]/text()").get());
                chengjiao.setChanquan(html.xpath("//div[@class=base]//div[@class=content]//li[13]/text()").get());
                chengjiao.setElevator(html.xpath("//div[@class=base]//div[@class=content]//li[14]/text()").get());
                //jiaoyi

                chengjiao.setTradingRight(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[2]/text()").get());
                chengjiao.setListDate(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[3]/text()").get());
                chengjiao.setAreaUse(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[4]/text()").get());
                chengjiao.setRoomYear(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[5]/text()").get());
                chengjiao.setOwnerRight(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[6]/text()").get());


                // 具体爬去字段
                logger.error(chengjiao.toString());
                page.putField("chengjiao", chengjiao);
            } else {
                logger.error(page.getUrl() + " 爬去失败，代理爬去失败 ,重新爬取!");
                throw new PageProcessException("代理爬取页面错误，需认证，重新爬取！");
            }
        }
        if (StringUtils.equalsIgnoreCase(getStartURL(), page.getUrl().get())) {
            try {
                //总共多少页的链接
                int total = Integer.parseInt(StringUtils.trim(page.getHtml().xpath("//div[@class=resultDes]//div[@class=total]").xpath("//span/text()").get()));
                int pageSize = 30;
                int maxPageNo = total / pageSize + 1;
                List<String> pageList = Lists.newArrayList();
                total = total > 100 ? 100 : total;
                for (int i = 1; i <= total; i++) {
                    pageList.add(getStartURL() + "/pg" + i);
                    page.addTargetRequest(new Request(getStartURL() + "/pg" + i).setPriority(-i));
                }
                logger.error("total page : " + maxPageNo + " total Records : " + total);
            } catch (Exception e) {
                throw new PageProcessException("代理爬取页面错误，需认证，重新爬取！");
            }
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

    private String subCjjg(String in) {
        return StringUtils.substringBefore(in, "二手房成交价格");
    }

    @Override
    public Site getSite() {
        return site;
    }


}
