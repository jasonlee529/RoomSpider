package cn.lee.housing.spider.lianjia.spider.process.proxy.xici;

import java.util.List;

import cn.lee.housing.spider.lianjia.spider.process.proxy.AbstractProxyProcessor;
import cn.lee.housing.utils.web.CheckIPUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 爬取西刺网的免费代理ip,
 * 代理都不可用，放弃
 * Created by jason on 17-7-13.
 */
public class ProxyProcessor extends AbstractProxyProcessor implements PageProcessor {

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
        page.addTargetRequests(page.getHtml().xpath("//div[@class=pagination]//a").links().all());
    }

}
