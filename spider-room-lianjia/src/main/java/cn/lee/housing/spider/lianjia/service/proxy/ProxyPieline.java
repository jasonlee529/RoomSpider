package cn.lee.housing.spider.lianjia.service.proxy;

import org.assertj.core.util.Lists;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.proxy.Proxy;

import java.util.List;

/**
 * Created by jason on 17/7/12.
 */
public class ProxyPieline implements Pipeline {

    private List<Proxy> proxyList = Lists.newArrayList();

    @Override
    public void process(ResultItems resultItems, Task task) {
        proxyList = resultItems.get("proxy");
    }


}
