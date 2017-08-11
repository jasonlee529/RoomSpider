package cn.lee.housing.spider.lianjia.web.ershoufang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.lee.housing.spider.lianjia.service.ChengjiaoPipeline;
import cn.lee.housing.spider.lianjia.spider.ChengjiaoProcessor;
import cn.lee.housing.utils.web.CheckIPUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.proxy.Proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 17-8-3.
 */
@RestController
@RequestMapping("/lianjia/chengjiao/")
public class ChengjiaoController {

    @Autowired
    private ChengjiaoPipeline pipeline;

    @RequestMapping(value = "{area}")
    public String area(@PathVariable String area) {
        try {
            HttpClientDownloader downloader = new HttpClientDownloader();
            //downloader.setProxyProvider(SimpleProxyProvider.from(getProxies().toArray(new Proxy[]{})));
            Spider spider = Spider.create(new ChengjiaoProcessor())
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline)
                    .addUrl("https://bj.lianjia.com/chengjiao/changping");
            spider.setDownloader(downloader);
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
            String[] cols = StringUtils.split(StringUtils.split(inLine, ",")[0], ":");
            String ip = StringUtils.trim(cols[0]);
            int port = Integer.parseInt(cols[1]);
            if (CheckIPUtils.checkValidIP(ip, port)) {
                proxyList.add(new Proxy(ip, port));
            }
        }
        return proxyList;
    }
}
