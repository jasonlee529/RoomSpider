package cn.lee.housing.spider.lianjia.spider.process.proxy.kuai;

import java.util.List;

import cn.lee.housing.spider.lianjia.spider.process.proxy.AbstractProxyProcessor;
import cn.lee.housing.spider.lianjia.spider.process.proxy.ProxyPipeline;
import cn.lee.housing.utils.web.CheckIPUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 爬取西刺网的免费代理ip,
 * http://www.kuaidaili.com/free/outtr/
 * Created by jason on 17-7-13.
 */
public class KuaiProxyProcessor extends AbstractProxyProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//div[@id=list]//tr").nodes();
        for (int i = 1; i < nodes.size(); i++) {
            Selectable node = nodes.get(i);
            String host = node.xpath("//td[1]/text()").get();
            int port = Integer.parseInt(node.xpath("//td[2]/text()").get());
            String time = StringUtils.substring(node.xpath("//td[6]//text()").get(), 0, -1);
            if (StringUtils.isNotBlank(time)) {
                Double timeNum = Double.parseDouble(time);
                if (timeNum <= 5.00 && CheckIPUtils.checkValidIP(host, port)) {
                    proxyList.add(new Proxy(host, port));
                }
            }
        }
        page.putField("proxy", proxyList);
        logger.info("爬取" + page.getUrl() + "代理IP结束，共爬取" + proxyList.size() + "个代理IP.");
        page.addTargetRequests(page.getHtml().xpath("//div[@id=listnav]//a").links().all());
    }


    public static Spider getSpider() {
        return Spider.create(new KuaiProxyProcessor())
                .addPipeline(new ProxyPipeline())
                .addUrl("http://www.kuaidaili.com/free/outtr/").thread(5);
    }

}
