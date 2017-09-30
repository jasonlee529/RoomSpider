package cn.lee.housing.spider.lianjia.model.room;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.lee.housing.spider.lianjia.model.IdEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * 每日房子报价，相同价格不更新
 * Created by jason on 17-7-21.
 */
@Entity
@Table(name = "lianjia_ershoufang_baojia")
public class Baojia extends IdEntity {

    private String roomId;

    private String price; //报价

    private String crawTime;//抓取时间

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

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
