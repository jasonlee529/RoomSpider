package cn.lee.housing.spider.lianjia.service.task;

import cn.lee.housing.spider.lianjia.model.task.TaskJob;
import cn.lee.housing.spider.lianjia.repository.task.TaskJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskJobMapper jobDao;


    public TaskJob createJob() {
        TaskJob job = TaskJob.builder().name("爬虫任务_" + System.currentTimeMillis()).build();
        return saveTaskJob(job);
    }

    public TaskJob saveTaskJob(TaskJob job) {
        jobDao.insertSelective(job);
        return job;
    }
}
