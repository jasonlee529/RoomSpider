package cn.lee.housing.spider.lianjia.spider.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import cn.lee.housing.spider.lianjia.service.proxy.MyProxyService;
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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17/8/24.
 */
@Service
public class MipuProxyProvider implements ProxyProvider, InitializingBean {

    @Autowired
    public MyProxyService myProxyService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Proxy> proxies;

    private final AtomicInteger pointer;

    private ConcurrentMap<Proxy, Long> dates = new ConcurrentHashMap<Proxy, Long>(10);

    @Override
    public void afterPropertiesSet() throws Exception {
        proxies = myProxyService.findAll();
        for (Proxy p : proxies) {
            dates.put(p, System.currentTimeMillis());
        }
    }

    public MipuProxyProvider() {
        proxies = new ArrayList<Proxy>();
        this.pointer = new AtomicInteger(-1);
    }

    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {
        boolean isSuccess = page.isDownloadSuccess();
        if (!isSuccess) {
            synchronized (proxies) {
                removeProxy(proxy);
            }
        }else{
            myProxyService.save(proxy, isSuccess);
        }
    }

    @Override
    public Proxy getProxy(Task task) {
        Proxy proxy = proxies.get(incrForLoop());
        return proxy;
    }

    private synchronized int incrForLoop() {
        int p = pointer.incrementAndGet();
        int size = proxies.size();
        if (size < 200) {
            synchronized (proxies) {
                getProxy();
            }
            size = proxies.size();
        }
        if (p >= size) {
            p = p % size;
        }
        logger.info("current: " + p + " size : " + size);
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
                addProxy(new Proxy(ip, port));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }
    }

    private void addProxy(Proxy proxy) {
        synchronized (proxies) {
            proxies.add(proxy);
            dates.put(proxy, 0L);
            myProxyService.save(proxy, true);
        }
    }

    private void removeProxy(Proxy proxy) {
        synchronized (proxies) {
            proxies.remove(proxy);
            dates.remove(proxy);
            myProxyService.save(proxy, false);
        }
    }
}
