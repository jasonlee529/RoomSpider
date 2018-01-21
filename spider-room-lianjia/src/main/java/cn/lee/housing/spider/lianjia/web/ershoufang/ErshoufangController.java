package cn.lee.housing.spider.lianjia.web.ershoufang;

import java.util.Map;

import cn.lee.housing.spider.lianjia.service.room.ErshoufangService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 爬取二手房数据
 * Created by jason on 17/7/17.
 */
@RestController
@RequestMapping(value = "lianjia/ershoufang")
public class ErshoufangController {

    @Autowired
    private ErshoufangService service;

    @RequestMapping(value = "{area}")
    public Map area(@PathVariable String area) {
        return service.spiderDay(area);
    }


    @RequestMapping(value = "more")
    public Map area() {
        return service.spiderDepth();
    }
}
