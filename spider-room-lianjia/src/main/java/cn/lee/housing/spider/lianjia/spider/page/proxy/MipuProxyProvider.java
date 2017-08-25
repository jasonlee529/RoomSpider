package cn.lee.housing.spider.lianjia.spider.page.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import cn.lee.housing.utils.web.CheckIPUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

import org.springframework.stereotype.Service;

/**
 * Created by jason on 17/8/24.
 */
@Service
public class MipuProxyProvider implements ProxyProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<Proxy> proxies;

    private final AtomicInteger pointer;

    public MipuProxyProvider() {
        proxies = new ArrayList<Proxy>();
        this.pointer = new AtomicInteger(-1);
    }

    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {
        logger.info(page.isDownloadSuccess()+" ==========");
    }

    @Override
    public Proxy getProxy(Task task) {
        Proxy proxy = new Proxy("124.192.39.248",3128);//proxies.get(incrForLoop());
        logger.info(proxy.toString());
        return proxy;
    }

    private int incrForLoop() {
        int p = pointer.incrementAndGet();
        int size = proxies.size();
        if (p < size) {
            return p;
        } else {
            synchronized (proxies) {
                getProxy();
                pointer.set(-1);
                p = incrForLoop();
            }
        }
        return p;
    }

    private void getProxy() {
        StringBuilder api = new StringBuilder("http://proxy.mimvp.com/api/fetch.php?");
        Map<String, String> params = new HashMap<>();
        params.put("orderid", "860170823143754231");
        params.put("http_type", "2");
        params.put("ping_time", "1");
        params.put("transer_time", "1");
        params.put("num", "100");
        params.put("result_fields", "1,2");
        params.put("result_format", "json");
        for (String key : params.keySet()) {
            api.append("&").append(key).append("=").append(params.get(key));
        }
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(api.toString());
        try {
            HttpResponse response = client.execute(get);
            ObjectMapper mapper = new ObjectMapper();
            String json = IOUtils.toString(response.getEntity().getContent());
            Map<String, Object> result = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
            logger.info(json);
            List<Map<String, String>> data = (List<Map<String, String>>) result.get("result");
            for (Map<String, String> m : data) {
                String[] cols = StringUtils.split(m.get("ip:port"), ":");
                String ip = StringUtils.trim(cols[0]);
                int port = Integer.parseInt(cols[1]);
                if (CheckIPUtils.checkValidIP(ip, port)) {
                    proxies.add(new Proxy(ip, port));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
    }
}
