package cn.lee.housing.spider.lianjia.service.task;

import cn.lee.housing.spider.lianjia.model.task.TaskJob;
import cn.lee.housing.spider.lianjia.repository.task.TaskJobDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskJobDao jobDao;


    public TaskJob createJob() {
        return saveTaskJob(new TaskJob());
    }

    public TaskJob saveTaskJob(TaskJob job) {
        return jobDao.save(job);
    }
}
