package cn.lee.housing.spider.lianjia.spider.proxy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import java.io.IOException;

/**
 * @Title: B_ProxyProvider
 * @Description:
 * @author: libo
 * @date: 2021/7/11 0011 1:57
 * @Version: 1.0
 */
@Slf4j
@Component
public class B_ProxyProvider implements ProxyProvider, InitializingBean {

    @Value("${proxy.b_proxy.get}")
    private String proxyUrl;
    @Value("${proxy.b_proxy.delete}")
    private String deleteUrl;

    HttpClient client = null;
    HttpGet get = null;
    HttpGet delete = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        client = HttpClientBuilder.create().build();
        get = new HttpGet(proxyUrl);
    }

    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {
        if (!page.isDownloadSuccess()) {
            // Remove proxy
            String url = deleteUrl + "?host=" + proxy.getHost() + "&port=" + proxy.getPort();
            delete = new HttpGet(url);
            try {
                HttpResponse response = client.execute(delete);
                log.info(EntityUtils.toString(response.getEntity(), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Proxy getProxy(Task task) {
        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = response.getEntity();
                String respContent = EntityUtils.toString(he, "UTF-8");
                JSONObject jsonObject = JSON.parseObject(respContent);
                String host = jsonObject.getJSONObject("data").getString("host");  //取出json数组中的某一个属性
                String port = jsonObject.getJSONObject("data").getString("port");  //取出json数组中的某一个属性
                System.out.println("代理IP: " + host);
                if (StringUtils.isNotBlank(host)) {
                    return new Proxy(host, Integer.parseInt(port));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}