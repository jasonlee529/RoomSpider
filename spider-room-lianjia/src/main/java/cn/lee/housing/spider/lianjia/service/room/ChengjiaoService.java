package cn.lee.housing.spider.lianjia.service.room;

import cn.lee.housing.spider.lianjia.model.room.Chengjiao;
import cn.lee.housing.spider.lianjia.repository.ChengjiaoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jason on 8/27/2017.
 */
@Service
public class ChengjiaoService {
    @Autowired
    private ChengjiaoDao chengjiaoDao;

    public boolean isExist(String roomId) {
        Chengjiao cj = chengjiaoDao.findByRoomId(roomId);
        return cj != null;
    }
}
