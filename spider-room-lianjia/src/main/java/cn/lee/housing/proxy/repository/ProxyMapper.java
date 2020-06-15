package cn.lee.housing.proxy.repository;

import cn.lee.housing.proxy.model.ProxyModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProxyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProxyModel record);

    int insertSelective(ProxyModel record);

    ProxyModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProxyModel record);

    int updateByPrimaryKey(ProxyModel record);

    int updateBatch(List<ProxyModel> list);

    int updateBatchSelective(List<ProxyModel> list);

    int batchInsert(@Param("list") List<ProxyModel> list);
}