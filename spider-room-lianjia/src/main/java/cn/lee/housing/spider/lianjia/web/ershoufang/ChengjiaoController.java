package cn.lee.housing.spider.lianjia.web.ershoufang;

import java.util.Map;

import cn.lee.housing.spider.lianjia.service.room.ChengjiaoService;

import org.springframework.beans.factory.annotation.Autowired;
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
    private ChengjiaoService cJService;

    /**
     * 按区域爬取
     *
     * @param area
     * @return
     */
    @RequestMapping(value = "{area}")
    public Map area(@PathVariable String area) {
        return cJService.spiderDay(area);
    }
    /**
     * 深度爬虫
     *
     * @return
     */
    @RequestMapping(value = "more")
    public Map area() {
        return cJService.spiderDepth();
    }


    /**
     * 重新爬取所有的需要重爬取的数据
     *
     * @return
     */
    @RequestMapping(value = "recrawl")
    public Map recrawl() {
        return cJService.reCrawl();
    }
}
