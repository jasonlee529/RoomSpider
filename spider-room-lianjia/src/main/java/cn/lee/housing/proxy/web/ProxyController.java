package cn.lee.housing.proxy.web;

import cn.lee.housing.proxy.fetcher.ProxyFetcher;
import cn.lee.housing.proxy.fetcher.impl.XiciProxyFetcher;
import cn.lee.housing.proxy.model.ProxyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author libo
 * @Title: ProxyController
 * @Description:
 * @date 2020/6/15 17:02
 * @Version 1.0
 */
@RestController
@RequestMapping("proxy")
public class ProxyController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("start")
    public List<ProxyModel> start() {
        ProxyFetcher proxyFetcher = new XiciProxyFetcher();
        List<ProxyModel> modelList = proxyFetcher.fetch("http://www.xicidaili.com/nn/",
                "http://www.xicidaili.com/nt/");
        return modelList;
    }
}
