package cn.lee.housing.spider.lianjia.repository.task;

import cn.lee.housing.spider.lianjia.model.task.TaskJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskJobMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskJob record);

    int insertSelective(TaskJob record);

    TaskJob selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskJob record);

    int updateByPrimaryKey(TaskJob record);

    int updateBatch(List<TaskJob> list);

    int updateBatchSelective(List<TaskJob> list);

    int batchInsert(@Param("list") List<TaskJob> list);
}