package cn.lee.housing.spider.lianjia.repository.room.lianjia;

import cn.lee.housing.spider.lianjia.model.room.lianjia.LianjiaChengjiao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface LianjiaChengjiaoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LianjiaChengjiao record);

    int insertSelective(LianjiaChengjiao record);

    LianjiaChengjiao selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LianjiaChengjiao record);

    int updateByPrimaryKey(LianjiaChengjiao record);

    int updateBatch(List<LianjiaChengjiao> list);

    int updateBatchSelective(List<LianjiaChengjiao> list);

    int batchInsert(@Param("list") List<LianjiaChengjiao> list);

    LianjiaChengjiao findByRoomId(@Param("roomId") String roomId);

    List<LianjiaChengjiao> findAllRrawl();
}