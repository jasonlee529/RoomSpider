package cn.lee.housing.spider.lianjia.model.room;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.lee.housing.spider.lianjia.model.IdEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 二手房成交价格
 * Created by jason on 17-7-21.
 */
@Entity
@Table(name = "lianjia_chengjiao")
public class Chengjiao extends IdEntity {

    private String roomId;

    private String totalPrice;

    private String avgPrice;

    private String listPrice;

    private String cycle;

    private String times;

    private String inspectTimes;

    private String attentionTimes;

    private String viewTimes;


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getInspectTimes() {
        return inspectTimes;
    }

    public void setInspectTimes(String inspectTimes) {
        this.inspectTimes = inspectTimes;
    }

    public String getAttentionTimes() {
        return attentionTimes;
    }

    public void setAttentionTimes(String attentionTimes) {
        this.attentionTimes = attentionTimes;
    }

    public String getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(String viewTimes) {
        this.viewTimes = viewTimes;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
