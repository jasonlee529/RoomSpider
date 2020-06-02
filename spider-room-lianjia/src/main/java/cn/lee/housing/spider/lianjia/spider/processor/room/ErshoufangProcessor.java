package cn.lee.housing.spider.lianjia.spider.processor.room;

import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaBaojia;
import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaErshoufang;
import cn.lee.housing.spider.lianjia.service.room.ErshoufangService;
import cn.lee.housing.spider.lianjia.spider.PageProcessException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by jason on 17-6-14.
 */
public class ErshoufangProcessor implements PageProcessor {

    private Site site = Site.me().setCharset("utf-8").setRetryTimes(10).setCycleRetryTimes(200).setSleepTime(1000).setDomain("bj.lianjia.com");

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ErshoufangService service;
    private String county = "";

    public final static String START_URL = "https://bj.lianjia.com/ershoufang/co32";
    private final Pattern pageOnePattern = Pattern.compile("/pg\\d+");

    private final Pattern numberPattern = Pattern.compile("/\\d+");

    @Override
    public void process(Page page) {
        if (isDetailPage(page)) {
            //详情页
            processDetail(page);
        } else {
            //非详情页
            processListItems(page);
            processPageItems(page);
        }
    }


    /**
     * 处理页码
     *
     * @param page
     */
    protected void processPageItems(Page page) {
        if (isPageOne(page)) {
            //是否第一页,添加分页爬取链接
            int total = Integer.parseInt(StringUtils.trim(page.getHtml().xpath("//div[@class=resultDes]//h2[@class=total]").xpath("//span/text()").get()));
            int pageSize = 30;
            int maxPageNo = total / pageSize + 1;
            total = total > 100 ? 100 : total;
            for (int i = 2; i <= total; i++) {
                page.addTargetRequest(new Request(page.getUrl().get() + "/pg" + i).setPriority(100L));
            }
            logger.error("total page : " + maxPageNo + " total Records : " + total);
        } else {
            //
        }
    }


    /**
     * 处理分页数据
     *
     * @param page
     */
    protected void processListItems(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//ul[@class=sellListContent]/li").nodes();
        for (Selectable node : nodes) {
            String url = node.xpath("//a[@class=img]").links().get();
            String roomId = RoomIdProvider.parseRoomId(url);
            if (StringUtils.isNotBlank(roomId)) {
                LianjiaBaojia bj = LianjiaBaojia.builder().roomId(roomId).crawTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss")).build();
                bj.setTitle(node.xpath("//div[@class=title]/a/text()").get());
                bj.setPrice(node.xpath("//div[@class=totalPrice]/span/text()").get());
                bj.setInfo(node.xpath("//div[@class=houseInfo]/text()").get());
                bj.setAvgPrice(node.$("div.unitPrice", "data-price").get());
                page.putField("baojia", bj);
                logger.info("{} baojia : ", bj);
                if (service.isRecrawl(roomId)) {
                    Request request = new Request(url).setPriority(10L);
                    page.addTargetRequest(request);
                    logger.info(" add Request : " + request);
                }
            }
        }
    }

    /**
     * 爬取详情页
     *
     * @param page
     */
    protected void processDetail(Page page) {
        Html html = page.getHtml();
        String fwId = RoomIdProvider.parseRoomId(page.getUrl().get());
        String fwId2 = html.xpath("//div[@class=overview]//div[@class=houseRecord]//span[@class=info]/text()").get();
        if (StringUtils.isNotBlank(fwId) && StringUtils.isNotBlank(fwId2)) {
            LianjiaErshoufang entity = LianjiaErshoufang.builder().roomId(fwId).crawTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss")).build();
            entity.setTitle(html.xpath("//div[@class=sellDetailHeader]//div[@class=title]//h1/text()").get());
            entity.setSubTitle(html.xpath("//div[@class=sellDetailHeader]//div[@class=title]//div/text()").get());
            entity.setTotalPrice(html.xpath("//div[@class=overview]//div[@class=price]/span[@class=total]/text()").get());
            entity.setAvgPrice(html.xpath("//div[@class=overview]//div[@class=price]//span[@class=unitPriceValue]/text()").get());
            entity.setInspectTimes(html.xpath("//span[@id=cartCount]/text()").get());
            entity.setAttentionTimes(html.xpath("//span[@id=favCount]/text()").get());

            //quyue
            entity.setCounty(subCjjg(html.xpath("//div[@class=intro]//a[3]/text()").get()));
            entity.setRegion(subCjjg(html.xpath("//div[@class=intro]//a[4]/text()").get()));
            entity.setReCrawl(false);
            entity.setDistrict(subCjjg(html.xpath("//div[@class=intro]//a[5]/text()").get()));

            //jiben
            entity.setHuxing(html.xpath("//div[@class=base]//div[@class=content]//li[1]/text()").get());
            entity.setLouceng(html.xpath("//div[@class=base]//div[@class=content]//li[2]/text()").get());
            String area = StringUtils.trim(html.xpath("//div[@class=base]//div[@class=content]//li[3]/text()").get());
            entity.setArea(StringUtils.replace(area, "㎡", ""));
            entity.setRJiegou(html.xpath("//div[@class=base]//div[@class=content]//li[4]/text()").get());
            String innerArea = StringUtils.trim(html.xpath("//div[@class=base]//div[@class=content]//li[5]/text()").get());
            entity.setInnerArea(StringUtils.replace(innerArea, "㎡", ""));
            entity.setBuildType(html.xpath("//div[@class=base]//div[@class=content]//li[6]/text()").get());
            entity.setOrientation(html.xpath("//div[@class=base]//div[@class=content]//li[7]/text()").get());
            entity.setBJiegou(html.xpath("//div[@class=base]//div[@class=content]//li[8]/text()").get());
            entity.setZhuangxiu(html.xpath("//div[@class=base]//div[@class=content]//li[9]/text()").get());
            entity.setTihu(html.xpath("//div[@class=base]//div[@class=content]//li[10]/text()").get());
            entity.setGongnuan(html.xpath("//div[@class=base]//div[@class=content]//li[11]/text()").get());
            entity.setElevator(html.xpath("//div[@class=base]//div[@class=content]//li[12]/text()").get());
            entity.setChanquan(html.xpath("//div[@class=base]//div[@class=content]//li[13]/text()").get());
            entity.setBuildYear(html.xpath("//div[@class=houseInfo]//div[@class=subInfo]/text()").get());
            //jiaoyi

            entity.setListDate(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[1]/span[2]/tidyText()").get());
            entity.setTradingRight(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[2]//span[2]/tidyText()").get());
            entity.setLastTradeDate(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[3]//span[2]/tidyText()").get());
            entity.setAreaUse(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[4]//span[2]/tidyText()").get());
            entity.setRoomYear(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[5]//span[2]/tidyText()").get());
            entity.setOwnerRight(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[6]//span[2]/tidyText()").get());
            entity.setDiya(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[7]/span[2]/text()").get());
            entity.setRoomDeeed(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[8]//span[2]/tidyText()").get());

            // 具体爬去字段
            page.putField("ershoufang", entity);
        } else {
            logger.error(page.getUrl() + " 爬去失败，代理爬去失败 ,重新爬取!");
            throw new PageProcessException("代理爬取页面错误，需认证，重新爬取！");
        }
    }

    private boolean isPageOne(Page page) {
        return !pageOnePattern.matcher(page.getUrl().get()).find();
    }

    protected boolean isDetailPage(Page page) {
        return StringUtils.endsWith(page.getUrl().get(), "html");
    }

    @Override
    public Site getSite() {
        return site;
    }


    public ErshoufangService getService() {
        return service;
    }

    public void setService(ErshoufangService service) {
        this.service = service;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    private String subCjjg(String in) {
        return StringUtils.substringBefore(in, "二手房");
    }

    public String getStartUrl() {
        return START_URL;
    }
}
