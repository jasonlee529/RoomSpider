package cn.lee.housing.spider.lianjia.spider.processor;

import cn.lee.housing.spider.lianjia.service.room.ChengjiaoService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.processor.PageProcessor;

@Service
public class ChengjiaoProcessorFactory implements InitializingBean {

    private ChengjiaoProcessor processor;
    @Autowired
    private ChengjiaoService chengjiaoService;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ChengjiaoProcessor getObject(String county) throws Exception {
        processor = new ChengjiaoProcessor();
        processor.setChengjiaoService(chengjiaoService);
        processor.setCounty(county);
        return processor;
    }

}
