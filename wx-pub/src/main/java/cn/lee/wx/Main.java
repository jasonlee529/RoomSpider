package cn.lee.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 17-10-13.
 */
@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @RequestMapping("wx")
    public String wx() {
        return "fdsafs";
    }
}
