package cn.lee.housing.spider.lianjia.spider.proxy;

import java.util.Date;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 使用讯代理爬取数据
 * Created by jason on 17-9-25.
 */
@Service
public class XdailiProxyProvider implements ProxyProvider {

    @Value("${xdaili.secret}")
    private String secret;
    @Value("${xdaili.order}")
    private String orderId;
    @Value("${xdaili.url}")
    private String url;
    @Value("${xdaili.port}")
    private int port;

    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {

    }

    @Override
    public Proxy getProxy(Task task) {
        int timestamp = (int) (new Date().getTime() / 1000);
        //以下订单号，secret参数 须自行改动
        final String authHeader = authHeader(secret, orderId, timestamp);
        task.getSite().getHeaders().put("Proxy-Authorization", authHeader);
        return new Proxy(url, port);
    }


    public String authHeader(String orderno, String secret, int timestamp) {
        //拼装签名字符串
        String planText = String.format("orderno=%s,secret=%s,timestamp=%d", orderno, secret, timestamp);

        //计算签名
        String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(planText).toUpperCase();

        //拼装请求头Proxy-Authorization的值
        String authHeader = String.format("sign=%s&orderno=%s&timestamp=%d", sign, orderno, timestamp);
        return authHeader;
    }
}
