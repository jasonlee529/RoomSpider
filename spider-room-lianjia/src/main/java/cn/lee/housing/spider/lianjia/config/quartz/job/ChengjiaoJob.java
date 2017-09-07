package cn.lee.housing.spider.lianjia.config.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by jason on 17-9-7.
 */
public class ChengjiaoJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String name;

    // Invoked if a Job data map entry with that name
    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println(String.format("Hello %s!", this.name));
        logger.error(String.format("Hello %s!", this.name));
    }
}