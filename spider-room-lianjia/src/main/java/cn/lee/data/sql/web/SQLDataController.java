package cn.lee.data.sql.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.lee.data.sql.result.SqlResult;
import cn.lee.data.sql.service.ConfigSourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

/**
 * Created by jason on 17-12-29.
 */
@RestController
@RequestMapping(value = "/data/sql")
public class SQLDataController {

    @Autowired
    private ConfigSourceService configSourceService;

    @RequestMapping(value = "{id}")
    public SqlResult one(@PathVariable Long id, HttpServletRequest request) {
        SqlResult result = new SqlResult();
        try {
            Map model = WebUtils.getParametersStartingWith(request, "filter__");
            result = configSourceService.findOne(id, model);
        } catch (Exception e) {
            result.setCode(SqlResult.ERROR);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
