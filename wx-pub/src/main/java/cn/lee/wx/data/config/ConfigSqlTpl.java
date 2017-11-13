package cn.lee.wx.data.config;

import java.io.Serializable;
import javax.persistence.*;

/**
 * sql模板配置类
 * Created by jason on 17-11-13.
 */
@Entity
@Table(name = "lee_config_sql_tpl")
public class ConfigSqlTpl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 对这条SQL的描述
     */
    private String name;
    /**
     * 这条SQL对应的标签名称
     */
    private String label;
    /**
     * 类型
     */
    private String type;
    /**
     * SQL模板
     */
    private String sqlTemplate;
    /**
     * 限制个数
     */
    private String limiting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSqlTemplate() {
        return sqlTemplate;
    }

    public void setSqlTemplate(String sqlTemplate) {
        this.sqlTemplate = sqlTemplate;
    }

    public String getLimiting() {
        return limiting;
    }

    public void setLimiting(String limiting) {
        this.limiting = limiting;
    }
}
