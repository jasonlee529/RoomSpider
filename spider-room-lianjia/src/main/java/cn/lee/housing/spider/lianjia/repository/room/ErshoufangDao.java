package cn.lee.housing.spider.lianjia.repository.room;

import cn.lee.housing.spider.lianjia.model.room.Ershoufang;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 17-7-12.
 */
public interface ErshoufangDao extends PagingAndSortingRepository<Ershoufang, Long> {

    public Ershoufang findByFwId(String fwId);
}
