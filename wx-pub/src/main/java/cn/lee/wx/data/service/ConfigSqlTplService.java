package cn.lee.wx.data.service;

import java.util.List;
import java.util.Map;

import cn.lee.wx.data.config.ConfigSqlResult;
import cn.lee.wx.data.config.ConfigSqlTpl;
import cn.lee.wx.data.repository.ConfigSqlTplRespository;
import cn.lee.wx.util.template.FreeMarkerUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17-11-13.
 */
@Service
public class ConfigSqlTplService {
    @Autowired
    private ConfigSqlTplRespository configSqlTplRespository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ConfigSqlResult executeSql(long id, Map data) {
        ConfigSqlTpl tpl = configSqlTplRespository.findOne(id);
        String sql = FreeMarkerUtils.mergeTemplateFromString(tpl.getSqlTemplate(), data);
        List<Map<String, Object>> sqlResult = jdbcTemplate.queryForList(sql);
        ConfigSqlResult configSqlResult = new ConfigSqlResult(tpl);
        configSqlResult.setResult(sqlResult);
        return configSqlResult;
    }

}
