package cn.lee.housing.spider.lianjia.spider.processor;

import cn.lee.housing.spider.lianjia.service.room.ErshoufangService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.lee.housing.spider.lianjia.spider.processor.ChengjiaoProcessorFactory.convertName;

@Service
public class ErshoufangProcessorFactory implements InitializingBean {

    private ErshoufangProcessor processor;
    @Autowired
    private ErshoufangService service;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ErshoufangProcessor getObject(String county) throws Exception {
        processor = new ErshoufangProcessor();
        processor.setService(service);
        processor.setCounty(convertName(county));
        return processor;
    }
    public ErshoufangProcessor getMoreProcessor() throws Exception {
        processor = new ErshoufangMoreProcessor();
        processor.setService(service);
        return processor;
    }
}
