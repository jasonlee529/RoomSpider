package cn.lee.housing.spider.lianjia.spider.processor.wangqian;

import java.util.List;

import cn.lee.housing.spider.lianjia.model.wangqian.DayInfo;
import cn.lee.housing.spider.lianjia.model.wangqian.MonthInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * Created by jason on 18-2-1.
 */
public class WangqianProcessor implements PageProcessor {

    private Site site = Site.me().setCharset("utf-8").setRetryTimes(10).setCycleRetryTimes(200).setSleepTime(1000);

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void process(Page page) {
        page.putField("dayInfo", parseDayInfo(page));
        page.putField("monthAgent", parseMonthAgent(page));
        page.putField("monthArea", parseMonthArea(page));
        page.putField("monthCounty", parseMonthCounty(page));
        page.putField("month", parseMonthInfo(page));
    }

    private Object parseMonthInfo(Page page) {
        return null;
    }

    private Object parseMonthCounty(Page page) {
        return null;
    }

    private Object parseMonthArea(Page page) {
        return null;
    }

    private MonthInfo parseMonthAgent(Page page) {
        MonthInfo obj = new MonthInfo();
        Selectable table = page.getHtml().$("table.tjInfo[1]");
        obj.setMonth(table.$("thead tr").get().replace("存量房网上签约", ""));
        obj.setDealAmt(table.$("tbody tr[0] td[1]").get());
        obj.setDealAreas(table.$("tbody tr[1] td[1]").get());
        obj.setResAmt(table.$("tbody tr[2] td[1]").get());
        obj.setResAreas(table.$("tbody tr[3] td[1]").get());
        return obj;
    }

    private DayInfo parseDayInfo(Page page) {
        //day info
        DayInfo dayInfo = new DayInfo();
        List<Selectable> tables = page.getHtml().$("table.tjInfo").nodes();
        Selectable verified = tables.get(0);
        dayInfo.setDate(verified.$("thead tr").get().replace("核验房源", ""));
        dayInfo.setVerifiedAmt(verified.$("tbody tr[0] td[1]").get());
        dayInfo.setVerifiedAreas(verified.$("tbody tr[1] td[1]").get());
        dayInfo.setVerifiedResAmt(verified.$("tbody tr[2] td[1]").get());
        dayInfo.setVerifiedResAreas(verified.$("tbody tr[3] td[1]").get());
        Selectable deal = tables.get(0);
        dayInfo.setDealAmt(deal.$("tbody tr[0] td[1]").get());
        dayInfo.setDealAreas(deal.$("tbody tr[1] td[1]").get());
        dayInfo.setDealResAmt(deal.$("tbody tr[2] td[1]").get());
        dayInfo.setDealResAreas(deal.$("tbody tr[3] td[1]").get());
        return dayInfo;
    }

    @Override
    public Site getSite() {
        return site;
    }
}
