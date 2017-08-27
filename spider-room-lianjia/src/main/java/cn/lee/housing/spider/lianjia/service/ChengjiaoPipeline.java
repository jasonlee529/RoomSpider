package cn.lee.housing.spider.lianjia.service;

import cn.lee.housing.spider.lianjia.model.Ershoufang;
import cn.lee.housing.spider.lianjia.model.room.Chengjiao;
import cn.lee.housing.spider.lianjia.repository.ChengjiaoDao;
import cn.lee.housing.spider.lianjia.repository.ErshoufangDao;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17/7/14.
 */
@Service
public class ChengjiaoPipeline implements Pipeline {

    @Autowired
    private ChengjiaoDao cjDao;
    @Autowired
    private ErshoufangDao roomDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        Chengjiao entity = resultItems.get("chengjiao");
        if (entity != null && roomDao.findByFwId(entity.getRoomId()) == null) {
            cjDao.save(entity);
        }

        Ershoufang ershoufang = resultItems.get("ershoufang");
        if (ershoufang != null && roomDao.findByFwId(ershoufang.getFwId()) == null) {
            roomDao.save(ershoufang);
        }

    }
}