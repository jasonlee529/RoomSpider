package cn.lee.housing.spider.lianjia.service.proxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.lee.housing.spider.lianjia.model.proxy.MyProxy;
import cn.lee.housing.spider.lianjia.repository.proxy.MyProxyDao;
import us.codecraft.webmagic.proxy.Proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17-8-25.
 */
@Service
public class MyProxyService {

    @Autowired
    private MyProxyDao myProxyDao;

    public void save(Proxy proxy, boolean avilable) {
        MyProxy p = myProxyDao.findByHostAndPort(proxy.getHost(), proxy.getPort());
        if (avilable) {
            p.addTimes();
            myProxyDao.save(p);
        } else {
            myProxyDao.delete(p);
        }
    }


    public void save(List<Proxy> proxies) {
        for (Proxy proxy : proxies) {
            MyProxy p = myProxyDao.findByHostAndPort(proxy.getHost(), proxy.getPort());
            if (p == null) {
                p = new MyProxy(proxy);
                myProxyDao.save(p);
            }
        }
    }

    public List<Proxy> findAll() {
        List<MyProxy> list = (List<MyProxy>) myProxyDao.findAll();
        Collections.sort(list, new Comparator<MyProxy>() {
            @Override
            public int compare(MyProxy o1, MyProxy o2) {
                return (int)(o2.getTimes() - o1.getTimes());
            }
        });
        List<Proxy> proxies = new ArrayList<Proxy>();
        for(MyProxy p : list){
            proxies.add(new Proxy(p.getHost(),p.getPort(),p.getUsername(),p.getPassword()));
        }
        return proxies;
    }
}
