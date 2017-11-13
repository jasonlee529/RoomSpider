package cn.lee.wx.data.config;

import java.util.List;
import java.util.Map;

/**
 * Created by jason on 17-11-13.
 */
public class ConfigSqlResult extends ConfigSqlTpl {

    private List<Map<String, Object>> result;

    public ConfigSqlResult(ConfigSqlTpl tpl) {
        this.setId(tpl.getId());
        this.setName(tpl.getName());
        this.setLabel(tpl.getLabel());
        this.setType(tpl.getType());
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }
}
