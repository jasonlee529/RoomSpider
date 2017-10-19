package cn.lee.wx.messages;

import java.io.IOException;

import cn.lee.wx.message.service.GroupMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jason on 17-10-19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("cn.lee")
public class TestGroupMessageService {

    @Autowired
    private GroupMessageService groupMessageService;

    @Test
    public void testSend() {
        try {
            groupMessageService.sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
