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

    private String roomId; // 房间编号

    private String dealDate;

    private String dealAgent;

    private String totalPrice; // 总价

    private String avgPrice; //　平均价格

    private String listPrice; // 挂牌价格

    private String cycle; //成交周期

    private String times; // 调价次数

    private String inspectTimes; //带看次数

    private String attentionTimes; // 关注

    private String viewTimes;//浏览

    public Chengjiao(String roomId) {
        this.roomId = roomId;
    }

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

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealAgent() {
        return dealAgent;
    }

    public void setDealAgent(String dealAgent) {
        this.dealAgent = dealAgent;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
