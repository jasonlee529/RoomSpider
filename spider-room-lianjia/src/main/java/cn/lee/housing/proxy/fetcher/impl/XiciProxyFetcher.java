package cn.lee.housing.proxy.fetcher.impl;

import cn.lee.housing.proxy.fetcher.ProxyFetcher;
import cn.lee.housing.proxy.model.ProxyModel;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author libo
 * @Title: XiciProxyFetcher
 * @Description:
 * @date 2020/6/15 12:02
 * @Version 1.0
 */
@Slf4j
public class XiciProxyFetcher implements ProxyFetcher {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<ProxyModel> fetch(String... urls) {
        List<ProxyModel> models = Lists.newArrayList();
        for (String url : urls) {
            int count = 0;
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
                Elements trs = doc.select("#ip_list").select("tr:gt(0)");
                for (Element tr : trs) {
                    models.add(ProxyModel.builder()
                            .host(tr.select("td:eq(1)").text())
                            .port(tr.select("td:eq(2)").text())
                            .region(tr.select("td:eq(3)").text())
                            .anonymous(tr.select("td:eq(4)").text())
                            .type(tr.select("td:eq(5)").text())
                            .source(url).quality(0).nextVerifyTime(System.currentTimeMillis() + 1000 * 60 * 60 * 6).build());
                    count++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
            log.info("{} 爬取链接 {} at {}", url, count, new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        }
        return models;
    }

}
