package cn.lee.housing.spider.lianjia.service.proxy;

import java.util.List;

import cn.lee.housing.utils.web.CheckIPUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.proxy.Proxy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17/7/12.
 */
@Service
public class ProxyPieline implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<Proxy> proxyList = Lists.newArrayList();

    @Override
    public void afterPropertiesSet() throws Exception {
        refreshProxy();
    }

    public List<Proxy> getProxyList() {
        return proxyList;
    }

    public void refreshProxy() {
        try {
            Document doc = Jsoup.connect("http://www.xicidaili.com/wt/3")
                    .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36")
                    .get();
            Elements trs = doc.select("table#ip_list tr:gt(1)");
            for (Element ele : trs) {
                String host = ele.select("td:eq(1)").text();
                int port = Integer.parseInt(ele.select("td:eq(2)").text());
                String time = StringUtils.substring(ele.select("td:eq(7) div").attr("title"), 0, -1);
                Double timeNum = Double.parseDouble(time);
                if (timeNum < 1.00 && CheckIPUtils.checkValidIP(host, port)) {
                    proxyList.add(new Proxy(host, port));
                }
            }
            logger.info("爬取代理IP结束，共爬取" + proxyList.size() + "个代理IP.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
