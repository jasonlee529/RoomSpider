package cn.lee.housing.spider.lianjia.model.wangqian;

import cn.lee.housing.spider.lianjia.model.IdEntity;

/**
 * 网签机构
 * Created by jason on 18-1-29.
 */
//@Table(name = "wangqian_month_agents")
public class MonthAgent extends IdEntity {

    private String month;
    private String seq; //排序
    private String agent; // 机构
    private String newAmt; //发布套数
    private String dealAmt;//成交套数
    private String refundAmt;//退房套数

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getNewAmt() {
        return newAmt;
    }

    public void setNewAmt(String newAmt) {
        this.newAmt = newAmt;
    }

    public String getDealAmt() {
        return dealAmt;
    }

    public void setDealAmt(String dealAmt) {
        this.dealAmt = dealAmt;
    }

    public String getRefundAmt() {
        return refundAmt;
    }

    public void setRefundAmt(String refundAmt) {
        this.refundAmt = refundAmt;
    }
}
