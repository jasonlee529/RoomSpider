package cn.lee.housing.spider.lianjia.spider.pipeline.room;

import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaChengjiao;
import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaErshoufang;
import cn.lee.housing.spider.lianjia.repository.room.lianjia.LianjiaChengjiaoMapper;
import cn.lee.housing.spider.lianjia.repository.room.lianjia.LianjiaErshoufangMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * Created by jason on 17/7/14.
 */
@Service
public class ChengjiaoPipeline implements Pipeline {

    @Autowired
    private LianjiaChengjiaoMapper cjDao;
    @Autowired
    private LianjiaErshoufangMapper roomDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        LianjiaChengjiao entity = resultItems.get("chengjiao");
        if (entity != null) {
            LianjiaChengjiao cj = cjDao.findByRoomId(entity.getRoomId());
            if (cj != null) {
                Long id = cj.getId();
                BeanUtils.copyProperties(entity, cj);
                cj.setId(id);
                cjDao.updateByPrimaryKeySelective(cj);
            } else {
                cjDao.insertSelective(entity);
            }
        }

        LianjiaErshoufang ershoufang = resultItems.get("ershoufang");
        if (ershoufang != null) {
            ershoufang.setStatus("已成交");
            LianjiaErshoufang esf = roomDao.findByRoomId(ershoufang.getRoomId());
            if (esf != null) {
                Long id = esf.getId();
                BeanUtils.copyProperties(ershoufang, esf);
                esf.setId(id);
                roomDao.updateByPrimaryKeySelective(esf);
            } else {
                roomDao.insertSelective(ershoufang);
            }
        }

    }
}
