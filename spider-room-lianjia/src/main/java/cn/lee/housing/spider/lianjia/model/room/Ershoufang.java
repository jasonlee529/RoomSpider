package cn.lee.housing.spider.lianjia.model.room;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.lee.housing.spider.lianjia.model.IdEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

/**
 * Created by jason on 17-7-11.
 */
@Entity
@Table(name = "lianjia_ershoufang")
public class Ershoufang extends IdEntity {

    private String roomId; // 房间编号,链家id
    /**
     * 标题
     */
    private String title;

    private String subTitle;//副标题

    private String totalPrice; // 总价

    private String avgPrice; //　平均价格

    private String area; // 面积

    private String inspectTimes; //带看次数

    private String attentionTimes; // 关注(人）

    private String crawTime; //抓取时间

    private boolean reCrawl = true; //是否需要爬取

    private String district; // 小区

    private String region;   //区域

    private String county;   // 区县

    private String huxing; // 房屋户型

    private String louceng; // 所在楼层

    private String rJiegou; // 户型结构

    private String innerArea; //套内面积

    private String buildType; //建筑类型

    private String orientation; // 房屋朝向

    private String buildYear;    //建成年代

    private String zhuangxiu; //装修情况

    private String bJiegou; //建筑结构

    private String gongnuan; //供暖方式

    private String tihu; //梯户比例

    private String chanquan; //产权年限

    private String elevator;//配备电梯

    private String tradingRight;//交易权属

    private String areaUse; //房屋用途

    private String listDate; //挂牌时间

    private String roomYear;//房屋年限

    private String ownerRight; //房权所属

    private String lastTradeDate; // 上次交易日期

    private String diya; //是否抵押

    private String roomDeeed; // 其他

    private String status; // 状态

    public Ershoufang() {
    }

    public Ershoufang(String roomId) {
        this.roomId = roomId;
        this.crawTime = new DateTime().toString("yyyy-MM-dd_HH:mm:ss");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getCrawTime() {
        return crawTime;
    }

    public void setCrawTime(String crawTime) {
        this.crawTime = crawTime;
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

    public String getHuxing() {
        return huxing;
    }

    public void setHuxing(String huxing) {
        this.huxing = huxing;
    }

    public String getLouceng() {
        return louceng;
    }

    public void setLouceng(String louceng) {
        this.louceng = louceng;
    }

    public String getrJiegou() {
        return rJiegou;
    }

    public void setrJiegou(String rJiegou) {
        this.rJiegou = rJiegou;
    }

    public String getInnerArea() {
        return innerArea;
    }

    public void setInnerArea(String innerArea) {
        this.innerArea = innerArea;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }

    public String getZhuangxiu() {
        return zhuangxiu;
    }

    public void setZhuangxiu(String zhuangxiu) {
        this.zhuangxiu = zhuangxiu;
    }

    public String getbJiegou() {
        return bJiegou;
    }

    public void setbJiegou(String bJiegou) {
        this.bJiegou = bJiegou;
    }

    public String getGongnuan() {
        return gongnuan;
    }

    public void setGongnuan(String gongnuan) {
        this.gongnuan = gongnuan;
    }

    public String getTihu() {
        return tihu;
    }

    public void setTihu(String tihu) {
        this.tihu = tihu;
    }

    public String getChanquan() {
        return chanquan;
    }

    public void setChanquan(String chanquan) {
        this.chanquan = chanquan;
    }

    public String getElevator() {
        return elevator;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public String getTradingRight() {
        return tradingRight;
    }

    public void setTradingRight(String tradingRight) {
        this.tradingRight = tradingRight;
    }

    public String getAreaUse() {
        return areaUse;
    }

    public void setAreaUse(String areaUse) {
        this.areaUse = areaUse;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getRoomYear() {
        return roomYear;
    }

    public void setRoomYear(String roomYear) {
        this.roomYear = roomYear;
    }

    public String getOwnerRight() {
        return ownerRight;
    }

    public void setOwnerRight(String ownerRight) {
        this.ownerRight = ownerRight;
    }

    public String getLastTradeDate() {
        return lastTradeDate;
    }

    public void setLastTradeDate(String lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }

    public String getDiya() {
        return diya;
    }

    public void setDiya(String diya) {
        this.diya = diya;
    }

    public String getRoomDeeed() {
        return roomDeeed;
    }

    public void setRoomDeeed(String roomDeeed) {
        this.roomDeeed = roomDeeed;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
