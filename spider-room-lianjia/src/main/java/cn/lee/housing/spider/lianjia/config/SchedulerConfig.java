package cn.lee.housing.spider.lianjia.config;

import cn.lee.housing.spider.lianjia.config.quartz.job.ChengjiaoJob;
import org.quartz.*;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * Created by jason on 17-9-7.
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {
    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(ChengjiaoJob.class).withIdentity("cjJob")
                .usingJobData("name", "World").storeDurably().build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        SimpleScheduleBuilder scheduleBuilder2 = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2).repeatForever();
        return TriggerBuilder.newTrigger().forJob(sampleJobDetail())
                .withIdentity("sampleTrigger").withSchedule(scheduleBuilder2).build();
    }


}
