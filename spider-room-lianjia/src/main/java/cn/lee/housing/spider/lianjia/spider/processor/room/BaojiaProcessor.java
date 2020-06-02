package cn.lee.housing.spider.lianjia.spider.processor.room;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * @author libo
 * @Title: BaojiaProcessor
 * @Description:
 * @date 2020/6/2 21:19
 * @Version 1.0
 */
@Slf4j
public class BaojiaProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        String fwId = parseRoomId(page.getUrl().get());
    }

    @Override
    public Site getSite() {
        return null;
    }
}
