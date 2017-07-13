package cn.lee.housing.spider.lianjia.spider;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.proxy.Proxy;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * Created by jason on 17-7-13.
 */
public class ProxyPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Proxy> proxyList = resultItems.get("proxy");
        FileWriter fw = null;
        try {
            Resource resource = new ClassPathResource("proxy.txt");
            fw = new FileWriter(resource.getFile());
            for (Proxy p : proxyList) {
                fw.write(p.getHost() + "," + p.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
