package cn.lee.housing.spider.lianjia.spider.proxy;

import java.util.Date;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import org.springframework.stereotype.Service;

/**
 * 使用讯代理爬取数据
 * Created by jason on 17-9-25.
 */
@Service
public class XdailiProxyProvider implements ProxyProvider {
    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {

    }

    @Override
    public Proxy getProxy(Task task) {
        int timestamp = (int) (new Date().getTime() / 1000);
        //以下订单号，secret参数 须自行改动
        final String authHeader = authHeader("ZF20179259363xxYvyk", "6e3d4d41727f454d983a7ef7fa206e5d", timestamp);
        task.getSite().getHeaders().put("Proxy-Authorization", authHeader);
        return new Proxy("forward.xdaili.cn", 8088);
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
