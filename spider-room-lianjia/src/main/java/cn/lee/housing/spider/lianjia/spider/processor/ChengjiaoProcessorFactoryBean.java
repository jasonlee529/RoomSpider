package cn.lee.housing.spider.lianjia.spider.processor;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.processor.PageProcessor;


public class ChengjiaoProcessorFactoryBean implements FactoryBean<ChengjiaoProcessor> {
    @Override
    public ChengjiaoProcessor getObject() throws Exception {
        ChengjiaoProcessor processor = new ChengjiaoProcessor();

        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
