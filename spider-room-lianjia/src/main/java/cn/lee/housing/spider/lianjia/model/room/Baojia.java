package cn.lee.housing.spider.lianjia.model.room;

import cn.lee.housing.spider.lianjia.model.IdEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 每日房子报价，相同价格不更新
 * Created by jason on 17-7-21.
 */
@Entity
@Table(name = "lianjia_ershoufang_baojia")
public class Baojia extends IdEntity {

    private String roomId;

    private String price; //报价

    private String title; // 标题

    private String avgPrice; // 平均价格

    private String info; // 简介

    private String crawTime;//抓取时间

    public Baojia() {
    }

    public Baojia(String roomId) {
        this.roomId = roomId;
        this.crawTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCrawTime() {
        return crawTime;
    }

    public void setCrawTime(String crawTime) {
        this.crawTime = crawTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
