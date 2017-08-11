package cn.lee.housing.spider.lianjia.service.proxy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17/7/12.
 */
@Service
public class ProxyService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<Proxy> proxyList = Lists.newArrayList();


    public List<Proxy> getProxyList() {
        if (proxyList == null || proxyList.size() <= 0) {
            synchronized (proxyList) {
                refreshProxy();
            }
        }
        return proxyList;
    }

    public void refreshProxy() {
        refreshMipu();
    }

    private void refreshMipu() {
        Resource resource = new ClassPathResource("proxy.txt");
        BufferedReader fr = null;
        try {
            fr = new BufferedReader(new FileReader(resource.getFile()));
            String inLine = null;
            List<Proxy> proxyList = new ArrayList<Proxy>();
            while ((inLine = fr.readLine()) != null) {
                String[] cols = StringUtils.split(StringUtils.split(inLine, ",")[0], ":");
                String ip = StringUtils.trim(cols[0]);
                int port = Integer.parseInt(cols[1]);
                if (CheckIPUtils.checkValidIP(ip, port)) {
                    proxyList.add(new Proxy(ip, port));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void refreshXici() {
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
