package cn.lee.housing.spider.lianjia.repository.wangqian;

import cn.lee.housing.spider.lianjia.model.wangqian.MonthAgent;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 18-2-1.
 */
public interface MonthAgentDao extends PagingAndSortingRepository<MonthAgent, Long>, JpaSpecificationExecutor<MonthAgent> {
}
