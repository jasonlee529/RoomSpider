package cn.lee.housing.spider.lianjia.spider.processor.room;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Selectable;

import org.springframework.stereotype.Service;


/**
 * Created by jason on 17-6-14.
 */
@Service
public class ChengjiaoMoreProcessor extends ChengjiaoProcessor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static String START_URL = "https://bj.lianjia.com/chengjiao/";

    @Override
    public void process(Page page) {
        if (isDetailPage(page)) {
            //详情页
            processDetail(page);
        } else {
            //非详情页
            processListItems(page);
            processAreaItems(page);
            processListMoreItems(page);
            processSortItems(page);
            processPagerItems(page);
        }
    }

    /**
     * 区域筛选条件
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

    /**
     * 更多筛选条件
     *
     * @param page
     */
    protected void processListMoreItems(Page page) {
        List<Selectable> areaNodes = page.getHtml().xpath("//div[@class=list-more]//a").nodes();
        for (Selectable node : areaNodes) {
            String url = node.links().get();
            page.addTargetRequest(url);
        }
    }

    /**
     * 排序字段
     *
     * @param page
     */
    protected void processSortItems(Page page) {
        List<Selectable> areaNodes = page.getHtml().xpath("//div[@class=orderTag]//li//a").nodes();
        for (Selectable node : areaNodes) {
            String url = node.links().get();
            page.addTargetRequest(url);
        }
    }

}
