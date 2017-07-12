package cn.lee.housing.spider.lianjia.model;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import us.codecraft.webmagic.model.annotation.ExtractBy;

/**
 * Created by jason on 17-7-11.
 */
public class Ershoufang {

    private String id;//数据保存标识
    @ExtractBy(value = "//div[@class=houseRecord]/span[@class=info]/text()",notNull = true)
    private String fwId; // 链家id
    private String dateTime; //爬取时间
    @ExtractBy("//div[@class=title-wrapper]/h1[@class=main]/text()")
    private String title;
    @ExtractBy("//div[@class=price]/span[@class=total]/text()")
    private String price;
    @ExtractBy("//div[@class=price]/span[@class=unitPriceValue]/text()")
    private String avgPrice;
    @ExtractBy("//div[@class=area]/div[@class=mainInfo]/text()")
    private String buildArea; //建筑面积
    @ExtractBy("//div[@class=price]/span[@class=total]/text()")
    private String useArea; // 使用面积

    private String type;  //　户型
    private String floor; // 楼层
    private String decoration; //装修
    private String drection;  //朝向
    private String buidYear; //建筑时间
    private String buildType; // 建筑类型
    private String region;  // 所在区域


    public Ershoufang() {
        this.id = UUID.randomUUID().toString();
        this.dateTime = new DateTime().toString("yyyy-MM-dd_HH:mm:ss");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFwId() {
        return fwId;
    }

    public void setFwId(String fwId) {
        this.fwId = fwId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(String avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getBuildArea() {
        return buildArea;
    }

    public void setBuildArea(String buildArea) {
        this.buildArea = buildArea;
    }

    public String getUseArea() {
        return useArea;
    }

    public void setUseArea(String useArea) {
        this.useArea = useArea;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public String getDrection() {
        return drection;
    }

    public void setDrection(String drection) {
        this.drection = drection;
    }

    public String getBuidYear() {
        return buidYear;
    }

    public void setBuidYear(String buidYear) {
        this.buidYear = buidYear;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}