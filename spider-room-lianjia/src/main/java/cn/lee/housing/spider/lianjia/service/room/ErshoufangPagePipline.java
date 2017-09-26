package cn.lee.housing.spider.lianjia.service.room;

import cn.lee.housing.spider.lianjia.model.room.Ershoufang;
import cn.lee.housing.spider.lianjia.repository.room.ErshoufangDao;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17-7-12.
 */
@Service
public class ErshoufangPagePipline implements PageModelPipeline<Ershoufang> {
    @Autowired
    private ErshoufangDao dao;

    @Override
    public void process(Ershoufang ershoufang, Task task) {
        dao.save(ershoufang);
    }
}
