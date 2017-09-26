package cn.lee.housing.spider.lianjia.model.room;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by jason on 17-7-11.
 */
@Entity
@Table(name = "lianjia_ershoufang")
@TargetUrl("https://bj.lianjia.com/ershoufang/\\w+.html")
@HelpUrl("https://bj.lianjia.com/ershoufang/changping/pg\\d+/")
public class Ershoufang implements Serializable {


    private Long id;//数据保存标识
    @ExtractBy(value = "//div[@class=houseRecord]/span[@class=info]/text()", notNull = true)
    private String fwId; // 链家id
    private String dateTime; //爬取时间
    @ExtractBy("//div[@class=sellDetailHeader]//h1[@class=main]/text()")
    private String title;
    @ExtractBy("//div[@class=price]/span[@class=total]/text()")
    private String price;
    @ExtractBy("//div[@class=price]//span[@class=unitPriceValue]/text()")
    private String avgPrice;
    @ExtractBy("//div[@class=area]//div[@class=mainInfo]/text()")
    private String buildArea; //建筑面积
    @ExtractBy("//div[@class=price]//span[@class=total]/text()")
    private String useArea; // 使用面积

    @ExtractBy("//div[@class=introContent]//div[@class=content]//ul//li[1]/text()")
    private String type;  //　户型
    @ExtractBy("//div[@class=introContent]//div[@class=content]//ul//li[2]/text()")
    private String floor; // 楼层
    @ExtractBy("//div[@class=introContent]//div[@class=content]//ul//li[9]/text()")
    private String decoration; //装修
    @ExtractBy("//div[@class=introContent]//div[@class=content]//ul//li[7]/text()")
    private String drection;  //朝向
    @ExtractBy("//div[@class=area]//div[@class=subInfo]/text()")
    private String buidYear; //建筑时间
    @ExtractBy("//div[@class=introContent]//div[@class=content]//ul//li[6]/text()")
    private String buildType; // 建筑类型
    @ExtractBy("//div[@class=aroundInfo]//div[@class=areaName]//span[@class=info]//tidyText()")
    private String region;  // 所在区域


    public Ershoufang() {
        this.dateTime = new DateTime().toString("yyyy-MM-dd_HH:mm:ss");
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
