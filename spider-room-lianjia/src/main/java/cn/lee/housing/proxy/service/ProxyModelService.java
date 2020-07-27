package cn.lee.housing.proxy.service;

import cn.lee.housing.proxy.model.ProxyModel;
import cn.lee.housing.proxy.repository.ProxyModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 代理管理类
 */
@Service
public class ProxyModelService {

    @Resource
    private ProxyModelMapper proxyModelMapper;


    public int deleteByPrimaryKey(Long id) {
        return proxyModelMapper.deleteByPrimaryKey(id);
    }


    public int insert(ProxyModel record) {
        return proxyModelMapper.insert(record);
    }


    public int insertSelective(ProxyModel record) {
        return proxyModelMapper.insertSelective(record);
    }


    public ProxyModel selectByPrimaryKey(Long id) {
        return proxyModelMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(ProxyModel record) {
        return proxyModelMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(ProxyModel record) {
        return proxyModelMapper.updateByPrimaryKey(record);
    }


    public int updateBatch(List<ProxyModel> list) {
        return proxyModelMapper.updateBatch(list);
    }


    public int updateBatchSelective(List<ProxyModel> list) {
        return proxyModelMapper.updateBatchSelective(list);
    }


    public int batchInsert(List<ProxyModel> list) {
        return proxyModelMapper.batchInsert(list);
    }


}

