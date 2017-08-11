package cn.lee.housing.spider.lianjia.repository;

import cn.lee.housing.spider.lianjia.model.room.Chengjiao;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 17-7-12.
 */
public interface ChengjiaoDao extends PagingAndSortingRepository<Chengjiao, Long> {

    public Chengjiao findByRoomId(String roomId);
}
