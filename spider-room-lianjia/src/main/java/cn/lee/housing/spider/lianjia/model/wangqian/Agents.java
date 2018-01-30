package cn.lee.housing.spider.lianjia.model.wangqian;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.lee.housing.spider.lianjia.model.IdEntity;

/**
 * 网签机构
 * Created by jason on 18-1-29.
 */
@Entity
@Table(name = "wangqian_agents")
public class Agents extends IdEntity {

    private String month;
    private String seq;
    private String agent;
    private String uploadAmt;
    private String dealAmt;
    private String refundAmt;

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

    public String getUploadAmt() {
        return uploadAmt;
    }

    public void setUploadAmt(String uploadAmt) {
        this.uploadAmt = uploadAmt;
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
