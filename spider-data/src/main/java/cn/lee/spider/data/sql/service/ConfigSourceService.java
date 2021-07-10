package cn.lee.spider.data.sql.service;

import java.util.List;
import java.util.Map;


import cn.lee.spider.data.sql.config.ConfigSource;
import cn.lee.spider.data.sql.repository.ConfigSourceDao;
import cn.lee.spider.data.sql.result.SqlResult;
import cn.lee.utils.template.FreeMarkerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


/**
 * Created by jason on 17-12-29.
 */
@Service
public class ConfigSourceService {

    @Autowired
    private ConfigSourceDao dao;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public SqlResult findOne(Long id, Map model) {
        ConfigSource config = dao.findById(id).get();
        String sqlTpl = config.getSqlTpl();
        String sql = FreeMarkerUtils.mergeTemplateFromString(sqlTpl, model);
        List<Map<String, Object>> reslt = jdbcTemplate.queryForList(sql);
        SqlResult sr = new SqlResult(reslt);
        return sr;
    }
}
