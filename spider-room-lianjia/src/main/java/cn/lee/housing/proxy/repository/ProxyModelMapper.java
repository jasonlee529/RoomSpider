package cn.lee.housing.proxy.repository;

import cn.lee.housing.proxy.model.ProxyModel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ProxyModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProxyModel record);

    int insertSelective(ProxyModel record);

    ProxyModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProxyModel record);

    int updateByPrimaryKey(ProxyModel record);

    int updateBatch(List<ProxyModel> list);

    int updateBatchSelective(List<ProxyModel> list);

    int batchInsert(@Param("list") List<ProxyModel> list);

    List<ProxyModel> findByHostAndPort(@Param("host") String host, @Param("port") String port);

    List<ProxyModel> findNextValid(@Param("nextValid") Long nextValid);
}