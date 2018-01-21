package cn.lee.housing.spider.lianjia.spider.processor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

/**
 * Created by jason on 18-1-21.
 */
public class ErshoufangMoreProcessor extends ErshoufangProcessor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public final static String START_URL = "https://bj.lianjia.com/ershoufang/";

    public void process(Page page) {
        if (isDetailPage(page)) {
            //详情页
            processDetail(page);
        } else {
            //非详情页
            processListItems(page);
            processAreaItems(page);
            processSortItems(page);
            processPageItems(page);
        }
    }

    private void processSortItems(Page page) {
        List<Selectable> areaNodes = page.getHtml().xpath("//div[@class=orderTag]//li//a").nodes();
        for (Selectable node : areaNodes) {
            String url = node.links().get();
            page.addTargetRequest(url);
        }
    }

    /**
     * 处理小区
     *
     * @param page
     */
    protected void processAreaItems(Page page) {
        List<Selectable> areaNodes = page.getHtml().xpath("//div[@class=position]//div//a").nodes();
        for (Selectable node : areaNodes) {
            String url = node.links().get();
            page.addTargetRequest(url);
        }
    }
}
