package cn.lee.housing.proxy.fetcher;

import cn.lee.housing.proxy.model.ProxyModel;

import java.util.List;

/**
 * 爬取远程Proxy的接口
 */
public interface ProxyFetcher {

    public List<ProxyModel> fetch(String... urls);
}
