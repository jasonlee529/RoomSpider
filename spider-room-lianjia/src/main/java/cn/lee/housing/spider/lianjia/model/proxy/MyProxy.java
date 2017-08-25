package cn.lee.housing.spider.lianjia.model.proxy;

import java.io.Serializable;
import javax.persistence.*;

import us.codecraft.webmagic.proxy.Proxy;

/**
 * Created by jason on 17-8-25.
 */
@Entity
@Table(name = "my_proxy")
public class MyProxy extends Proxy implements Serializable {


    private Long id;
    private boolean avaiable;
    private long times = 0;

    public MyProxy(Proxy proxy, boolean avaiable) {
        super(proxy.getHost(), proxy.getPort(), proxy.getUsername(), proxy.getPassword());
        avaiable = avaiable;
    }

    public MyProxy(Proxy proxy) {
        this(proxy, true);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAvaiable() {
        return avaiable;
    }

    public void setAvaiable(boolean avaiable) {
        this.avaiable = avaiable;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }
}
