package cn.lee.housing.spider.lianjia.service;

import cn.lee.housing.spider.lianjia.model.Ershoufang;
import cn.lee.housing.spider.lianjia.repository.ErshoufangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by jason on 17/7/14.
 */
@Service
public class ErshoufangPipeline implements Pipeline {

    @Autowired
    private ErshoufangDao dao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Ershoufang entity = resultItems.get("ershoufang");
        if (entity != null) {
            dao.save(entity);
        }
    }
}
