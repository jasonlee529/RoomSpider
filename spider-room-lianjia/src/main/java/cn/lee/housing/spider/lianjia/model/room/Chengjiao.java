package cn.lee.housing.spider.lianjia.model.room;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.lee.housing.spider.lianjia.model.IdEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * 二手房成交价格
 * Created by jason on 17-7-21.
 */
@Entity
@Table(name = "lianjia_chengjiao")
public class Chengjiao extends IdEntity {

    private String roomId; // 房间编号
    /**
     * 标题
     */
    private String title;

    private String dealDate;

    private String dealAgent;

    private String totalPrice; // 总价

    private String avgPrice; //　平均价格

    private String area; // 面积

    private String listPrice; // 挂牌价格

    private String cycle; //成交周期

    private String times; // 调价次数

    private String inspectTimes; //带看次数

    private String attentionTimes; // 关注

    private String viewTimes;//浏览

    private String crawTime; //抓取时间

    private boolean reCrawl = true; //是否需要爬取

    private String district; // 小区

    private String region;   //区域

    private String county;   // 区县

    public Chengjiao() {
        this.crawTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
    }

    public String getCrawTime() {
        return crawTime;
    }

    public void setCrawTime(String crawTime) {
        this.crawTime = crawTime;
    }

    public Chengjiao(String roomId) {
        this();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isReCrawl() {
        return reCrawl;
    }

    public void setReCrawl(boolean reCrawl) {
        this.reCrawl = reCrawl;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
