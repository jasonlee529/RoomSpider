package cn.lee.housing.spider.lianjia.model.wangqian;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 每日核验房源和成交房源数
 * Created by jason on 18-2-1.
 */
@Entity
@Table(name = "wangqian_day_info")
public class DayInfo {
    private String date;
    private String verifiedAmt; //核验房源数
    private String verifiedAreas; // 核验面积
    private String verifiedResAmt; // 住宅套数
    private String verifiedResAreas; // 住宅面积
    private String dealAmt; // 成交套数
    private String dealAreas; // 成交面积
    private String dealResAmt;//成交数量
    private String dealResAreas; // 成交面积

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVerifiedAmt() {
        return verifiedAmt;
    }

    public void setVerifiedAmt(String verifiedAmt) {
        this.verifiedAmt = verifiedAmt;
    }

    public String getVerifiedAreas() {
        return verifiedAreas;
    }

    public void setVerifiedAreas(String verifiedAreas) {
        this.verifiedAreas = verifiedAreas;
    }

    public String getVerifiedResAmt() {
        return verifiedResAmt;
    }

    public void setVerifiedResAmt(String verifiedResAmt) {
        this.verifiedResAmt = verifiedResAmt;
    }

    public String getVerifiedResAreas() {
        return verifiedResAreas;
    }

    public void setVerifiedResAreas(String verifiedResAreas) {
        this.verifiedResAreas = verifiedResAreas;
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

    public String getDealResAmt() {
        return dealResAmt;
    }

    public void setDealResAmt(String dealResAmt) {
        this.dealResAmt = dealResAmt;
    }

    public String getDealResAreas() {
        return dealResAreas;
    }

    public void setDealResAreas(String dealResAreas) {
        this.dealResAreas = dealResAreas;
    }
}
