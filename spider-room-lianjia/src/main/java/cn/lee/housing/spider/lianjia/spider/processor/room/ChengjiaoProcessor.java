package cn.lee.housing.spider.lianjia.spider.processor.room;

import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaChengjiao;
import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaErshoufang;
import cn.lee.housing.spider.lianjia.service.room.ChengjiaoService;
import cn.lee.housing.spider.lianjia.spider.PageProcessException;
import cn.lee.housing.utils.mapper.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;
import java.util.regex.Pattern;


/**
 * Created by jason on 17-6-14.
 */
@Service
public class ChengjiaoProcessor implements PageProcessor {

    private Site site = Site.me().setCharset("utf-8").setRetryTimes(10).setCycleRetryTimes(200).setSleepTime(1000).setDomain("bj.lianjia.com");

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static String START_URL = "https://bj.lianjia.com/chengjiao/";

    private String county = "";
    private String[] params = {};

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    private ChengjiaoService chengjiaoService;

    public String getPageUrl() {
        return getStartURL() + "/pg\\d+";
    }

    public String getStartURL() {
        return getUrl("");
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

    private final Pattern pageOnePattern = Pattern.compile("/pg\\d+");

    @Override
    public void process(Page page) {
        if (isDetailPage(page)) {
            //详情页
            processDetail(page);
        } else {
            //非详情页
            processListItems(page);
            processPagerItems(page);
        }
    }

    /**
     * 报价信息
     *
     * @param page
     */
    protected void processListItems(Page page) {
        List<String> urls = page.getHtml().xpath("//div[@class=leftContent]//ul//li//div[@class=title]//a").links().all();
        for (String str : urls) {
            String roomId = RoomIdProvider.parseRoomId(str);
            if (StringUtils.isNotBlank(roomId) && chengjiaoService.isRecrawl(roomId)) {
                Request request = new Request(str).setPriority(10L);
                page.addTargetRequest(request);
                logger.error(" add Request : " + request);
            }
        }
    }

    protected boolean isDetailPage(Page page) {
        return StringUtils.endsWith(page.getUrl().get(), "html");
    }

    protected void processPagerItems(Page page) {
        if (isPageOne(page)) {
            try {
                //总共多少页的链接
                String pageTpl = page.getHtml().xpath("//div[@class=house-lst-page-box]").$("div", "page-url").get();
                for (int i = 2; i <= 100; i++) {
                    String pageIndex = StringUtils.replace(pageTpl, "{page}", i + "");
                    page.addTargetRequest(new Request("https://bj.lianjia.com/" + pageIndex).setPriority(100L));
                }
                page.addTargetRequests(page.getHtml().xpath("//div[@class=m-filter]//div[@class=list-more]//a").links().all(), -1);
                logger.error("total request : " + page.getTargetRequests().size());
            } catch (Exception e) {
                throw new PageProcessException("代理爬取页面错误，需认证，重新爬取！");
            }
        } else {
            page.addTargetRequests(page.getHtml().xpath("//div[@class=m-filter]//div[@class=list-more]//a").links().all(), -1);
            logger.error("total request : " + page.getTargetRequests().size());
        }
    }

    private boolean isPageOne(Page page) {
        return !pageOnePattern.matcher(page.getUrl().get()).find();
    }

    protected void processDetail(Page page) {
        Html html = page.getHtml();
        String fwId = RoomIdProvider.parseRoomId(page.getUrl().get());
        String fwId2 = html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[1]/text()").get();
        if (StringUtils.isNotBlank(fwId) && StringUtils.isNotBlank(fwId2)) {
            LianjiaChengjiao chengjiao = LianjiaChengjiao.builder().roomId(fwId).crawTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss")).build();
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
            String area = StringUtils.trim(html.xpath("//div[@class=base]//div[@class=content]//li[3]/text()").get());
            chengjiao.setArea(StringUtils.replace(area, "㎡", ""));
            chengjiao.setRJiegou(html.xpath("//div[@class=base]//div[@class=content]//li[4]/text()").get());
            String innerArea = StringUtils.trim(html.xpath("//div[@class=base]//div[@class=content]//li[5]/text()").get());
            chengjiao.setInnerArea(StringUtils.replace(innerArea, "㎡", ""));
            chengjiao.setBuildType(html.xpath("//div[@class=base]//div[@class=content]//li[6]/text()").get());
            chengjiao.setOrientation(html.xpath("//div[@class=base]//div[@class=content]//li[7]/text()").get());
            chengjiao.setBuildYear(html.xpath("//div[@class=base]//div[@class=content]//li[8]/text()").get());
            chengjiao.setZhuangxiu(html.xpath("//div[@class=base]//div[@class=content]//li[9]/text()").get());
            chengjiao.setBJiegou(html.xpath("//div[@class=base]//div[@class=content]//li[10]/text()").get());
            chengjiao.setGongnuan(html.xpath("//div[@class=base]//div[@class=content]//li[11]/text()").get());
            chengjiao.setTihu(html.xpath("//div[@class=base]//div[@class=content]//li[12]/text()").get());
//            chengjiao.setChanquan(html.xpath("//div[@class=base]//div[@class=content]//li[13]/text()").get());
            chengjiao.setElevator(html.xpath("//div[@class=base]//div[@class=content]//li[13]/text()").get());
            //jiaoyi

            chengjiao.setTradingRight(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[2]/text()").get());
            chengjiao.setListDate(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[3]/text()").get());
            chengjiao.setAreaUse(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[4]/text()").get());
            chengjiao.setRoomYear(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[5]/text()").get());
            chengjiao.setOwnerRight(html.xpath("//div[@class=baseinform]//div[@class=transaction]//li[6]/text()").get());


            // 具体爬去字段
            logger.error(chengjiao.toString());
            page.putField("chengjiao", chengjiao);
            page.putField("ershoufang", BeanMapper.map(chengjiao, LianjiaErshoufang.class));
        } else {
            logger.error(page.getUrl() + " 爬去失败，代理爬去失败 ,重新爬取!");
            throw new PageProcessException("代理爬取页面错误，需认证，重新爬取！");
        }
    }

    private String subCjjg(String in) {
        return StringUtils.substringBefore(in, "二手房成交价格");
    }

    @Override
    public Site getSite() {
        return site;
    }


    public String getUrl(String pg) {
        List<String> list = Lists.newArrayList();
        list.add("https://bj.lianjia.com");
        list.add("chengjiao");
        list.add(county);
        list.addAll(Lists.<String>newArrayList(params));
        list.add(pg);
        return StringUtils.join(list, "/");
    }


}
