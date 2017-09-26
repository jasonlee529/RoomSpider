package cn.lee.housing.spider.lianjia.web.ershoufang;

import cn.lee.housing.spider.lianjia.service.room.ErshoufangPipeline;
import cn.lee.housing.spider.lianjia.spider.processor.ErshoufangProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬取二手房数据
 * Created by jason on 17/7/17.
 */
@RestController
@RequestMapping(value = "lianjia/room")
public class RoomController {

    @Autowired
    private ErshoufangPipeline pipeline;

    @RequestMapping(value = "{area}")
    public String area(@PathVariable String area) {
        try {
            HttpClientDownloader downloader = new HttpClientDownloader();
            downloader.setProxyProvider(SimpleProxyProvider.from(getProxies().toArray(new Proxy[]{})));
            Spider spider = Spider.create(new ErshoufangProcessor())
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline)
                    .addUrl("https://bj.lianjia.com/ershoufang/changping");
            spider.setDownloader(downloader);
            SpiderMonitor.instance().register(spider);
            spider.thread(3).start();//启动爬虫
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static List<Proxy> getProxies() throws IOException {
        Resource resource = new ClassPathResource("proxy.txt");
        BufferedReader fr = new BufferedReader(new FileReader(resource.getFile()));
        String inLine = null;
        List<Proxy> proxyList = new ArrayList<Proxy>();
        while ((inLine = fr.readLine()) != null) {
            String[] cols = StringUtils.split(inLine, ",");
            proxyList.add(new Proxy(cols[0], Integer.valueOf(cols[1])));
        }
        proxyList.add(new Proxy("p1.jp1.dancehey.com", 80, "jasonlee", "anguixue"));
        return proxyList;
    }
}
