package cn.lee.housing.spider.lianjia.web.wangqian;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.lee.housing.spider.lianjia.service.wangqian.WangqianService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 18-2-1.
 */
@RestController
@RequestMapping(value = "wangqian")
public class WangqianController {

    @Autowired
    private WangqianService wangqianService;

    @RequestMapping
    public Map wangqian(HttpServletRequest request) {
        wangqianService.startCrawl();
        return new HashMap();
    }

}
