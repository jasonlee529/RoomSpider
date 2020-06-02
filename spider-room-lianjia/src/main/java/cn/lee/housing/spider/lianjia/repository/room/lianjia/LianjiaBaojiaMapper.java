package cn.lee.housing.spider.lianjia.repository.room.lianjia;

import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaBaojia;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper

public interface LianjiaBaojiaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LianjiaBaojia record);

    int insertSelective(LianjiaBaojia record);

    LianjiaBaojia selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LianjiaBaojia record);

    int updateByPrimaryKey(LianjiaBaojia record);

    int updateBatch(List<LianjiaBaojia> list);

    int updateBatchSelective(List<LianjiaBaojia> list);

    int batchInsert(@Param("list") List<LianjiaBaojia> list);

    LianjiaBaojia findFirstByRoomIdOrderByCrawTimeDesc(@Param("roomId") String roomId);
}