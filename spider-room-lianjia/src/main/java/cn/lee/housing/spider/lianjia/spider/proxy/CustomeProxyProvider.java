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
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import java.io.IOException;

/**
 * 使用自建的代理
 * Created by jason on 17-9-25.
 */
@Service
@Slf4j
public class CustomeProxyProvider implements ProxyProvider, InitializingBean {

    @Value("${proxy.custome.get}")
    private String proxyUrl;
    @Value("${proxy.custome.delete}")
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
            String url = deleteUrl + "?proxy=" + proxy.getHost() + ":" + proxy.getPort();
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
                String jsonStr = jsonObject.getJSONObject("data").getString("proxy");  //取出json数组中的某一个属性
                System.out.println("代理IP: " + jsonStr);
                if (StringUtils.isNotBlank(jsonStr)) {
                    String[] arrs = StringUtils.split(jsonStr, ":");
                    return new Proxy(arrs[0], Integer.parseInt(arrs[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
