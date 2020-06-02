package cn.lee.housing.spider.lianjia.repository.room.lianjia;

import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaErshoufang;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper

public interface LianjiaErshoufangMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LianjiaErshoufang record);

    int insertSelective(LianjiaErshoufang record);

    LianjiaErshoufang selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LianjiaErshoufang record);

    int updateByPrimaryKey(LianjiaErshoufang record);

    int updateBatch(List<LianjiaErshoufang> list);

    int updateBatchSelective(List<LianjiaErshoufang> list);

    int batchInsert(@Param("list") List<LianjiaErshoufang> list);

    LianjiaErshoufang findByRoomId(@Param("roomId") String roomId);
}