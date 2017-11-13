package cn.lee.wx.data.repository;

import cn.lee.wx.data.config.ConfigSqlTpl;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jason on 17-11-13.
 */
@Repository
public interface ConfigSqlTplRespository extends CrudRepository<ConfigSqlTpl, Long>, JpaSpecificationExecutor<ConfigSqlTpl> {
}
