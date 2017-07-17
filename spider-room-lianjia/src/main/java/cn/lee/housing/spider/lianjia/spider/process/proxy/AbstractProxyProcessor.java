package cn.lee.housing.spider.lianjia.spider.process.proxy;

import java.util.List;

import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;

/**
 * Created by jason on 17-7-17.
 */
public abstract class AbstractProxyProcessor implements PageProcessor {

    protected Site site = Site.me().setRetryTimes(2).setSleepTime(1000);
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected List<Proxy> proxyList = Lists.newArrayList();

    @Override
    public Site getSite() {
        return site;
    }
}
