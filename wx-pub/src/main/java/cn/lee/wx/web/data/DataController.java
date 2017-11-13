package cn.lee.wx.web.data;

import java.util.HashMap;

import cn.lee.wx.data.config.ConfigSqlResult;
import cn.lee.wx.data.service.ConfigSqlTplService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jason on 17-11-13.
 */
@RestController
@RequestMapping(value = "api/data")
public class DataController {

    @Autowired
    private ConfigSqlTplService configSqlTplService;

    @RequestMapping(value = "{id}")
    public ConfigSqlResult getResult(@PathVariable Long id) {
        return configSqlTplService.executeSql(id, new HashMap());
    }
}
