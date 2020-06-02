package cn.lee.housing.spider.lianjia.model.wangqian;

import cn.lee.housing.spider.lianjia.model.IdEntity;

/**
 * Created by jason on 18-2-1.
 */
//@Table(name = "wangqian_month")
public class MonthInfo extends IdEntity {

    private String month;   //月份
    private String dealAmt; //成交套数
    private String dealAreas; // 成交面积
    private String resAmt; // 住宅套数
    private String resAreas; //住宅面积

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public String getResAmt() {
        return resAmt;
    }

    public void setResAmt(String resAmt) {
        this.resAmt = resAmt;
    }

    public String getResAreas() {
        return resAreas;
    }

    public void setResAreas(String resAreas) {
        this.resAreas = resAreas;
    }
}
