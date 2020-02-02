package cn.lee.housing.spider.lianjia.repository.task;

import cn.lee.housing.spider.lianjia.model.task.TaskJob;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskJobDao extends PagingAndSortingRepository<TaskJob, Long>, JpaSpecificationExecutor<TaskJob> {
}
