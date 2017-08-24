package cn.lee.housing.spider.lianjia.spider.page.proxy;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.ProxyProvider;

/**
 * Created by jason on 17/8/24.
 */
public class MipuProxyProvider implements ProxyProvider {
    @Override
    public void returnProxy(Proxy proxy, Page page, Task task) {

    }

    @Override
    public Proxy getProxy(Task task) {
        return null;
    }
}
