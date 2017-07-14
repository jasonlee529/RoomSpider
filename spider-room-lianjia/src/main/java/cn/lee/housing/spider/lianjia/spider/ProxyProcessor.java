package cn.lee.housing.spider.lianjia.spider;

import cn.lee.housing.utils.web.CheckIPUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * Created by jason on 17-7-13.
 */
public class ProxyProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(2).setSleepTime(1000);
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Proxy> proxyList = Lists.newArrayList();

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//table[@id=ip_list]//tr").nodes();
        for (int i = 1; i < nodes.size(); i++) {
            Selectable node = nodes.get(i);
            String host = node.xpath("//td[2]/text()").get();
            int port = Integer.parseInt(node.xpath("//td[3]/text()").get());
            String time = StringUtils.substring(node.$("td:eq(7) div", "title").get(), 0, -1);
            Double timeNum = Double.parseDouble(time);
            if (timeNum < 1.00 && CheckIPUtils.checkValidIP(host, port)) {
                proxyList.add(new Proxy(host, port));
            }
        }
        page.putField("proxy", proxyList);
        logger.info("爬取代理IP结束，共爬取" + proxyList.size() + "个代理IP.");
    }

    @Override
    public Site getSite() {
        return site;
    }

}
