package cn.lee.housing.utils.web;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by jason on 17-7-13.
 */
public class CheckIPUtils {

    /**
     * 校验代理IP的有效性，测试地址为：http://www.ip138.com
     *
     * @param ip   代理IP地址
     * @param port 代理IP端口
     * @return 此代理IP是否有效
     */
    public static boolean checkValidIP(String ip, Integer port) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("https://bj.lianjia.com");
            //代理服务器
            InetSocketAddress proxyAddr = new InetSocketAddress(ip, port);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddr);
            connection = (HttpURLConnection) url.openConnection(proxy);
            connection.setReadTimeout(200);
            connection.setConnectTimeout(200);
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {
                connection.disconnect();
                return true;
            }

        } catch (Exception e) {
            connection.disconnect();
            return false;
        }
        return false;
    }
}
