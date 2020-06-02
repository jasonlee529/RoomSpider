package cn.lee.housing.spider.lianjia.model.wangqian;

import cn.lee.housing.spider.lianjia.model.IdEntity;

/**
 * 区县网签数据
 * Created by jason on 18-1-29.
 */
//@Table(name = "wangqian_month_county")
public class MonthCounty extends IdEntity {

    private String month;
    private String county;//区县
    private String dealAmt;//成交套数
    private String totalArea;//成交面积

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(String dealAmt) {
        this.dealAmt = dealAmt;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }
}
