package cn.lee.housing.spider.lianjia.web.ershoufang;

import cn.lee.housing.spider.lianjia.service.ErshoufangPipeline;
import cn.lee.housing.spider.lianjia.spider.ErshoufangProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * 爬取二手房数据
 * Created by jason on 17/7/17.
 */
@RestController
@RequestMapping(value = "lianjia/room")
public class RoomController {

    @Autowired
    private ErshoufangPipeline pipeline;
    
    @RequestMapping(value = "${area}")
    public String area(@PathVariable String area) {
        try {
            Spider spider = Spider.create(new ErshoufangProcessor())
                    .addPipeline(new ConsolePipeline())
                    .addPipeline(pipeline)
                    .addUrl("https://bj.lianjia.com/ershoufang/changping");
            SpiderMonitor.instance().register(spider);
            spider.thread(3).start();//启动爬虫
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
