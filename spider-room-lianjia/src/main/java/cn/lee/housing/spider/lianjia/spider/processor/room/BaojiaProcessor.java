package cn.lee.housing.spider.lianjia.spider.processor.room;

import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaBaojia;
import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaErshoufang;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Html;

/**
 * @author libo
 * @Title: BaojiaProcessor
 * @Description:
 * @date 2020/6/2 21:19
 * @Version 1.0
 */
@Slf4j
public class BaojiaProcessor extends ErshoufangProcessor {

    private Site site = Site.me().setCharset("utf-8").setRetryTimes(10).setCycleRetryTimes(200).setSleepTime(1000).setDomain("bj.lianjia.com");


    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        String fwId = RoomIdProvider.parseRoomId(page.getUrl().get());
        if (StringUtils.isNotBlank(fwId) && StringUtils.isNotBlank(fwId)) {
            LianjiaBaojia entity = LianjiaBaojia.builder().roomId(fwId).build();
            entity.setTitle(html.xpath("//div[@class=sellDetailHeader]//div[@class=title]//h1/text()").get());
            entity.setAvgPrice(html.xpath("//div[@class=overview]//div[@class=price]//span[@class=unitPriceValue]/text()").get());
            entity.setPrice(html.xpath("//div[@class=overview]//div[@class=price]/span[@class=total]/text()").get());
            entity.setInfo(html.xpath("//div[@class=overview]//div[@class=houseInfo]/allText()").get());
            String status = html.xpath("//div[@class=sellDetailHeader]//div[@class=title]//span/text()").get();
            if (StringUtils.contains(page.getUrl().get(), "chengjiao") || StringUtils.isNotBlank(status)) {
                LianjiaErshoufang ershoufang = LianjiaErshoufang.builder().roomId(fwId).status(status).build();
                page.putField("ershoufang", ershoufang);

            }
            page.putField("baojia", entity);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
}
