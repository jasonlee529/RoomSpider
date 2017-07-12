package cn.lee.housing.spider.lianjia.service.proxy;

import java.util.List;

import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.selector.Selectable;

/**
 * Created by jason on 17-7-12.
 */

/**
 * 启动前从代理爬取100个地址做代理池
 */
public class ProxyProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(2).setSleepTime(1000);

    private List<Proxy> proxyList = Lists.newArrayList();

    @Override
    public void process(Page page) {
        List<Selectable> nodes = page.getHtml().xpath("//div[@id=ip_list]//tr").nodes();
        for (int i = 1; i < nodes.size(); i++) {
            Selectable node = nodes.get(i);
            String host = node.xpath("/td[1]/text()").get();
            String port = node.xpath("/td[2]/text()").get();
            proxyList.add(new Proxy(host, Integer.parseInt(port)));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public List<Proxy> parserProxy() {
        Spider.create(new ProxyProcessor()).setSpawnUrl(false).start();
        return proxyList;
    }
}
