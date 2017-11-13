package cn.lee.wx;

import java.io.IOException;

import cn.lee.wx.util.template.FreeMarkerUtils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 17-10-13.
 */
@SpringBootApplication
@RestController
@ImportResource("classpath:applicationContext.xml")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Bean
    public FreeMarkerUtils getFreeMarkerUtils() throws IOException {
        return new FreeMarkerUtils();
    }

}
