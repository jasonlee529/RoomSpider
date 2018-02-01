package cn.lee.housing.spider.lianjia.model.wangqian;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jason on 18-2-1.
 */
@Entity
@Table(name="wangqian_month_area")
public class MonthArea {

    private String month;   //月份
    private String areaName; //面积分段
    private String newAmt; //发布套数
    private String newAreas;    // 发布面积
    private String dealAmt; //成交套数
    private String dealAreas; // 成交面积

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getNewAmt() {
        return newAmt;
    }

    public void setNewAmt(String newAmt) {
        this.newAmt = newAmt;
    }

    public String getNewAreas() {
        return newAreas;
    }

    public void setNewAreas(String newAreas) {
        this.newAreas = newAreas;
    }

    public String getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(String dealAmt) {
        this.dealAmt = dealAmt;
    }

    public String getDealAreas() {
        return dealAreas;
    }

    public void setDealAreas(String dealAreas) {
        this.dealAreas = dealAreas;
    }
}
