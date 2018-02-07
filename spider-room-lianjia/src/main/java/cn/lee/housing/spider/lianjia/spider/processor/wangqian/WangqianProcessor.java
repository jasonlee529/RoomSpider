package cn.lee.housing.spider.lianjia.spider.processor.wangqian;

import java.util.List;

import cn.lee.housing.spider.lianjia.model.wangqian.*;
import com.google.common.collect.Lists;
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
        page.putField("crawlMonth", parseMonth(page));
        page.putField("dayInfo", parseDayInfo(page));
        page.putField("monthAgent", parseMonthAgent(page));
        page.putField("monthArea", parseMonthArea(page));
        page.putField("monthCounty", parseMonthCounty(page));
        page.putField("monthInfo", parseMonthInfo(page));
    }

    private MonthInfo parseMonthInfo(Page page) {
        MonthInfo obj = new MonthInfo();
        Selectable table = page.getHtml().$("table.tjInfo[1]");
        obj.setMonth(table.$("thead tr").get().replace("存量房网上签约", ""));
        obj.setDealAmt(table.$("tbody tr[0] td[1]").get());
        obj.setDealAreas(table.$("tbody tr[1] td[1]").get());
        obj.setResAmt(table.$("tbody tr[2] td[1]").get());
        obj.setResAreas(table.$("tbody tr[3] td[1]").get());
        return obj;
    }

    private List<MonthCounty> parseMonthCounty(Page page) {
        List<MonthCounty> result = Lists.newArrayList();
        Selectable table = page.getHtml().$("table.tjInfo01[2]");
        for (int i = 0; i < 6; i = i + 3) {
            Selectable trTitle = table.$("tr[" + i + "]");
            Selectable trAmt = table.$("tr[" + i + 1 + "]");
            Selectable trArea = table.$("tr[" + i + 2 + "]");
            int len = trTitle.$("td").nodes().size();
            for (int j = 1; j < len; j++) {
                MonthCounty mc = new MonthCounty();
                mc.setMonth(parseMonth(page));
                mc.setCounty(trTitle.$("td[" + j + "]").get());
                mc.setDealAmt(trAmt.$("td[" + j + "]").get());
                mc.setTotalArea(trArea.$("td[" + j + "]").get());
                result.add(mc);
            }
        }
        return result;
    }

    private List<MonthArea> parseMonthArea(Page page) {
        List<MonthArea> result = Lists.newArrayList();
        Selectable table = page.getHtml().$("table.tjInfo01[3]");
        Selectable trArea = table.$("tr[0]");
        Selectable trNewAmt = table.$("tr[1]");
        Selectable trNeaArea = table.$("tr[2]");
        Selectable trDealAmt = table.$("tr[3]");
        Selectable trDealArea = table.$("tr[4]");
        int len = table.$("tr[0] td").nodes().size();
        for (int j = 1; j < len; j++) {
            MonthArea mc = new MonthArea();
            mc.setMonth(parseMonth(page));
            mc.setAreaName(trArea.$("td[" + j + "]").get());
            mc.setNewAmt(trNewAmt.$("td[" + j + "]").get());
            mc.setNewAreas(trNeaArea.$("td[" + j + "]").get());
            mc.setDealAmt(trDealAmt.$("td[" + j + "]").get());
            mc.setDealAreas(trDealArea.$("td[" + j + "]").get());
            result.add(mc);
        }
        return result;
    }

    private List<MonthAgent> parseMonthAgent(Page page) {
        List<MonthAgent> result = Lists.newArrayList();
        for (int i = 0; i < 2; i++) {
            Selectable table = page.getHtml().$("table.tjInfo01").nodes().get(i);
            for (Selectable tr : table.$("tbody tr").nodes()) {
                MonthAgent agent = new MonthAgent();
                agent.setMonth(parseMonth(page));
                agent.setSeq(tr.$("td[0]").get());
                agent.setAgent(tr.$("td[1]").get());
                agent.setNewAmt(tr.$("td[2]").get());
                agent.setDealAmt(tr.$("td[3]").get());
                agent.setRefundAmt(tr.$("td[4]").get());
                result.add(agent);
            }
        }
        return result;
    }

    private String parseMonth(Page page) {
        return  page.getHtml().xpath("//div[@class=Title1000]//div[2]/text()").get();
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
