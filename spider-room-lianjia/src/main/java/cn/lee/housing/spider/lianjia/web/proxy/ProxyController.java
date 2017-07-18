package cn.lee.housing.spider.lianjia.web.proxy;

import cn.lee.housing.spider.lianjia.spider.process.proxy.kuai.KuaiProxyProcessor;
import cn.lee.housing.spider.lianjia.spider.process.proxy.xici.XiciProxyProcessor;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 17/7/17.
 */
@RestController
@RequestMapping(value = "proxy")
public class ProxyController {

    @RequestMapping("")
    public String proxy() {
        return proxyKuai();
    }

    @RequestMapping("{name}")
    public String proxyName(@PathVariable String name) {
        if (StringUtils.equalsIgnoreCase("xici", name)) {
            return proxyXici();
        }
        return proxyKuai();
    }

    @RequestMapping("xici")
    public String proxyXici() {
        XiciProxyProcessor.getSpider().run();
        return "";
    }

    @RequestMapping("kuai")
    public String proxyKuai() {
        KuaiProxyProcessor.getSpider().run();
        return "";
    }
}
