package cn.lee.housing.spider.lianjia.web.proxy;

import cn.lee.housing.spider.lianjia.spider.process.proxy.xici.XiciProxyProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 17/7/17.
 */
@RestController
@RequestMapping(value = "proxy")
public class ProxyController {

    @RequestMapping("xici")
    public String proxyXici() {
        XiciProxyProcessor.getSpder().run();
        return "";
    }
}
