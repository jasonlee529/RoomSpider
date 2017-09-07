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

    @RequestMapping(value = "{area}")
    public Map area(@PathVariable String area) {
        return cJService.doSpider(area);
    }
}
