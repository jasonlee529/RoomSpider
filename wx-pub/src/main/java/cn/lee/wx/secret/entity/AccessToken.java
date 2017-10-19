package cn.lee.wx.secret.entity;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;

/**
 * AccessTOken
 * Created by jason on 17-10-19.
 */

public class AccessToken {

    private String accessToken; //token

    private int expiresIn; // 过期秒数

    private DateTime getDate; // 获取时间

    private DateTime expiredDate; //过期时间

    public AccessToken() {
        getDate = new DateTime();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        this.expiredDate = getDate.plusSeconds(expiresIn);
    }

    public DateTime getGetDate() {
        return getDate;
    }

    public void setGetDate(DateTime getDate) {
        this.getDate = getDate;
    }

    public DateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(DateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public boolean isExpired() {
        DateTime now = new DateTime();
        return DateTimeComparator.getInstance().compare(now, expiredDate) >= 0;
    }
}
