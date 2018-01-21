package cn.lee.housing.spider.lianjia.config.quartz.job;

import cn.lee.housing.spider.lianjia.service.room.ChengjiaoService;
import cn.lee.housing.spider.lianjia.service.room.ErshoufangService;
import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by jason on 17-9-7.
 */
public class ChengjiaoJob extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        try {
            // 通过这种方式才能获取service，直接使用@Autowired无法注入。
            SchedulerContext skedCtx = context.getScheduler().getContext();
            ChengjiaoService service = (ChengjiaoService) skedCtx.get("cjService");
            logger.error(new DateTime().toString("yyyy-MM-dd HH:mm:ss") + " 开始任务执行！");
            service.spiderDay("all");

            ErshoufangService ershoufagService = (ErshoufangService) skedCtx.get("ershoufagService");
            ershoufagService.spiderDay("all");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}