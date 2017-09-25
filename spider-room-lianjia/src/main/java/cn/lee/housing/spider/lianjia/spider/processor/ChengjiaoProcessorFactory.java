package cn.lee.housing.spider.lianjia.spider.processor;

import cn.lee.housing.spider.lianjia.service.room.ChengjiaoService;
import com.alibaba.druid.util.StringUtils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        processor.setCounty(convertName(county));
        return processor;
    }

    public static String convertName(String county) {
        String[] counties = {"haidian", "changping", "shunyi", "chaoyang", "tongzhou", "daxing", "fengtai", "fangshan", "shijingshan", "mentoukou"};
        if (StringUtils.equalsIgnoreCase(county, "all")) {
            return "";
        }
        for (String c : counties) {
            if (StringUtils.equalsIgnoreCase(c, county)) {
                return "" + county;
            }
        }
        throw new IllegalArgumentException(" no county " + county);
    }

}
