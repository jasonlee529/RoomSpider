package cn.lee.wx.deal.repository;

import java.util.Map;

import cn.lee.wx.util.template.FreeMarkerUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by jason on 17-11-7.
 */
@Repository
public class DealMonthRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * @return
     */
    public Map monthData() {
        String sql = FreeMarkerUtils.mergeTemplate("test.ftl");
        return jdbcTemplate.queryForMap(sql);
    }
}
