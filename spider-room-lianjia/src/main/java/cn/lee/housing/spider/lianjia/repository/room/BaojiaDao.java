package cn.lee.housing.spider.lianjia.repository.room;

import java.util.List;

import cn.lee.housing.spider.lianjia.model.room.Baojia;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 17-7-12.
 */
public interface BaojiaDao extends PagingAndSortingRepository<Baojia, Long>, JpaSpecificationExecutor<Baojia> {

    public List<Baojia> findByRoomId(String roomId);

    public Baojia findFirstByRoomIdOrderByCrawTimeDesc(String roomId);
}
