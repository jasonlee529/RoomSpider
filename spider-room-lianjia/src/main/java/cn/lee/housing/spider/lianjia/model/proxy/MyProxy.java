package cn.lee.housing.spider.lianjia.model.proxy;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.lee.housing.spider.lianjia.model.IdEntity;
import org.joda.time.DateTime;
import us.codecraft.webmagic.proxy.Proxy;

/**
 * Created by jason on 17-8-25.
 */
@Entity
@Table(name = "my_proxy")
public class MyProxy extends IdEntity {


    private String host;
    private int port;
    private String username;
    private String password;
    private boolean avaiable;
    private long times = -1;
    private String lastUsed;

    public MyProxy(Proxy proxy, boolean avaiable) {
        this.host = proxy.getHost();
        this.port = proxy.getPort();
        this.username = proxy.getUsername();
        this.password = proxy.getPassword();
        this.avaiable = avaiable;
        this.resetLastUsed();
    }

    public MyProxy(Proxy proxy) {
        this(proxy, true);
    }

    public MyProxy() {

    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(String lastUsed) {
        this.lastUsed = lastUsed;
    }

    public void resetLastUsed() {
        this.lastUsed = new DateTime().toString("yyyy-MM-dd_HH:mm:ss");
    }

    public void addTimes() {
        synchronized (this) {
            this.times++;
        }
    }
}
