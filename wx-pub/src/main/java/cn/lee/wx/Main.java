package cn.lee.wx;

import java.io.IOException;

import cn.lee.wx.util.template.FreeMarkerUtils;
import freemarker.template.Configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Created by jason on 17-10-13.
 */
@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    public FreeMarkerUtils getFreeMarkerUtils() throws IOException {
        return new FreeMarkerUtils();
    }

    @Bean
    public Configuration getFreeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:template/");
        return freeMarkerConfigurer.getConfiguration();
    }
}
