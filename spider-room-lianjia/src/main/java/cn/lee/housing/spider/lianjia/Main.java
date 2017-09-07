package cn.lee.housing.spider.lianjia;

import cn.lee.housing.spider.lianjia.config.SchedulerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by jason on 17/7/14.
 */
@ImportResource(locations = {"classpath*:applicationContext.xml"})
@Import({SchedulerConfig.class})
@SpringBootApplication
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
