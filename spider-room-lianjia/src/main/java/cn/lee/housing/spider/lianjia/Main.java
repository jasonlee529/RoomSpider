package cn.lee.housing.spider.lianjia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by jason on 17/7/14.
 */
@Configuration
@ComponentScan(value = {"cn.lee.housing"})
@EnableAutoConfiguration
@ImportResource(locations = "classpath*:applicationContext.xml")
@EnableWebMvc
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
