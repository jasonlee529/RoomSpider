package cn.lee.data.sql.repository;

import cn.lee.data.sql.config.ConfigSource;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 17-12-29.
 */
public interface ConfigSourceDao extends PagingAndSortingRepository<ConfigSource, Long>, JpaSpecificationExecutor<ConfigSource> {
}
