package cn.lee.housing.spider.lianjia.repository.proxy;

import cn.lee.housing.spider.lianjia.model.proxy.MyProxy;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jason on 17-8-25.
 */
public interface MyProxyDao extends PagingAndSortingRepository<MyProxy, Long>, JpaSpecificationExecutor<MyProxy> {


    public MyProxy findByHostAndPort(String host, int port);
}
