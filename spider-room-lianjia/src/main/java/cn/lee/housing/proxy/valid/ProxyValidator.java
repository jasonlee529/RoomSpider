package cn.lee.housing.proxy.valid;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * @author libo
 * @Title: ProxyValidator
 * @Description:
 * @date 2020/6/16 9:44
 * @Version 1.0
 */
@Slf4j
public class ProxyValidator {

    private static final int CONNECTION_TIME_OUT = 1000;

    public static boolean valid(String distUrl, String host, Integer port) {
        HttpGet httpGet = new HttpGet(distUrl);
        CloseableHttpResponse response = null;
        try {
            //1、创建httpClient
            CloseableHttpClient client = null;
            try {
                //把代理设置到请求配置        代理IP     端口
                HttpHost proxy = new HttpHost(host, port);
                //超时时间单位为毫秒
                RequestConfig defaultRequestConfig = RequestConfig.custom()
                        .setConnectTimeout(CONNECTION_TIME_OUT).setSocketTimeout(CONNECTION_TIME_OUT)
                        .setProxy(proxy).build();
                client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

                response = client.execute(httpGet);
            } catch (UnknownHostException hostEx) {
                hostEx.printStackTrace();
            }

            return response.getStatusLine().getStatusCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
