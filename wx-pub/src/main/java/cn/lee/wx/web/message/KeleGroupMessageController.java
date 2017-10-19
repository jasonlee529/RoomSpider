package cn.lee.wx.web.message;

import java.util.Map;

import cn.lee.wx.message.service.GroupMessageService;
import com.google.common.collect.Maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 可乐小数据的推送代码
 * Created by jason on 17-10-19.
 */
@RestController
@RequestMapping("group/message/kele")
public class KeleGroupMessageController {


    @Autowired
    private GroupMessageService service;

    @RequestMapping("")
    public Map send() {
        Map result = Maps.newHashMap();

        return result;
    }
}
