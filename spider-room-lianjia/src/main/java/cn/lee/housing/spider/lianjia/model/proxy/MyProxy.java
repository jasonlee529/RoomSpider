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
    private long times;

    public MyProxy(String host, int port) {
        this(host, port, null, null);
    }

    public MyProxy(String host, int port, String username, String password) {
        super(host, port, username, password);
        avaiable = true;
        times = 0;
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
