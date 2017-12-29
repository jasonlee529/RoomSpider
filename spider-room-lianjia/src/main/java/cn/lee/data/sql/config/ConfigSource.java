package cn.lee.data.sql.config;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.lee.housing.spider.lianjia.model.IdEntity;

/**
 * Created by jason on 17-12-29.
 */
@Entity
@Table(name = "lee_config_sql")
public class ConfigSource extends IdEntity {


    private String name;
    private String sqlTpl;
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSqlTpl() {
        return sqlTpl;
    }

    public void setSqlTpl(String sqlTpl) {
        this.sqlTpl = sqlTpl;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
