package cn.lee.housing.spider.lianjia.spider.pipeline;

import cn.lee.housing.spider.lianjia.model.room.Baojia;
import cn.lee.housing.spider.lianjia.model.room.Ershoufang;
import cn.lee.housing.spider.lianjia.service.room.ErshoufangService;
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
public class ErshoufangPipeline implements Pipeline {

    @Autowired
    private ErshoufangService service;


    @Override
    public void process(ResultItems resultItems, Task task) {
        Ershoufang ershoufang = resultItems.get("ershoufang");
        if (ershoufang != null) {
            Ershoufang esf = service.findByRoomId(ershoufang.getRoomId());
            if (esf != null) {
                Long id = esf.getId();
                BeanUtils.copyProperties(ershoufang, esf);
                esf.setId(id);
                service.saveErshoufang(esf);
            } else {
                service.saveErshoufang(ershoufang);
            }
        }
        Baojia bj = resultItems.get("baojia");
        if (bj != null) {
            service.saveBaojia(bj);
        }
    }
}
