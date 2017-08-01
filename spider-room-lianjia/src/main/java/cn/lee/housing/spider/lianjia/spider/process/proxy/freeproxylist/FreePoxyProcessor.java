package cn.lee.housing.spider.lianjia.spider.process.proxy.freeproxylist;

import java.util.List;

import cn.lee.housing.spider.lianjia.spider.process.proxy.AbstractProxyProcessor;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * Created by jason on 17-7-20.
 */
public class FreePoxyProcessor extends AbstractProxyProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<Selectable> trs = page.getHtml().xpath("//table[@class=DataGrid]//tr[@class!=Caption]").nodes();
        for (Selectable tr : trs) {
            String ip = tr.xpath("//td[1]//a/text()").get();
            int port = Integer.parseInt(tr.xpath("//td[2]/text()").get());


        }


    }


}
