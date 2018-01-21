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
    public ChengjiaoProcessor getMoreProcessor() throws Exception {
        processor = new ChengjiaoMoreProcessor();
        processor.setChengjiaoService(chengjiaoService);
        return processor;
    }

    public static String convertName(String county) {
        if (StringUtils.equalsIgnoreCase(county, "all")) {
            return "";
        }
        for (String c : BEIJING_COUNTIES) {
            if (StringUtils.equalsIgnoreCase(c, county)) {
                return "" + county;
            }
        }
        throw new IllegalArgumentException(" no county " + county);
    }

    public static String[] BEIJING_COUNTIES = {"haidian", "changping", "shunyi", "chaoyang", "tongzhou", "daxing", "fengtai", "fangshan", "shijingshan", "mentougou", "yizhuangkaifaqu","pinggu"};

}
