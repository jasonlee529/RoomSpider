package cn.lee.housing.spider.lianjia.repository.room;

import cn.lee.housing.spider.lianjia.model.room.Chengjiao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 17-7-12.
 */
public interface ChengjiaoDao extends PagingAndSortingRepository<Chengjiao, Long>, JpaSpecificationExecutor<Chengjiao> {

    public Chengjiao findByRoomId(String roomId);
}
