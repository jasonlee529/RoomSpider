package cn.lee.housing.spider.lianjia.service.room;

import cn.lee.housing.spider.lianjia.model.room.Ershoufang;
import cn.lee.housing.spider.lianjia.model.room.Chengjiao;
import cn.lee.housing.spider.lianjia.repository.room.ChengjiaoDao;
import cn.lee.housing.spider.lianjia.repository.room.ErshoufangDao;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import org.springframework.beans.BeanUtils;
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
        if (entity != null) {
            Chengjiao cj = cjDao.findByRoomId(entity.getRoomId());
            if (cj != null) {
                Long id = cj.getId();
                BeanUtils.copyProperties(entity, cj);
                cj.setId(id);
                cjDao.save(cj);
            } else {
                cjDao.save(entity);
            }
        }

        Ershoufang ershoufang = resultItems.get("ershoufang");
        if (ershoufang != null) {
            Ershoufang esf = roomDao.findByFwId(ershoufang.getFwId());
            if (esf != null) {
                Long id = esf.getId();
                BeanUtils.copyProperties(ershoufang, esf);
                esf.setId(id);
                roomDao.save(esf);
            } else {
                roomDao.save(ershoufang);
            }
        }

    }
}
