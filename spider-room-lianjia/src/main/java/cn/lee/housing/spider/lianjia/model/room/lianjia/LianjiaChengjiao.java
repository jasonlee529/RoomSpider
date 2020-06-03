package cn.lee.housing.spider.lianjia.model.room.lianjia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LianjiaChengjiao {
    private Long id;

    /**
     * 房间编号
     */
    private String roomId;

    /**
     * 标题
     */
    private String title;

    /**
     * 总价
     */
    private String totalPrice;

    /**
     * 均价
     */
    private String avgPrice;

    /**
     * 面积
     */
    private String area;

    /**
     * 关注
     */
    private String attentionTimes;

    /**
     * 成交周期
     */
    private String cycle;

    /**
     * 成交公司
     */
    private String dealAgent;

    /**
     * 成交日期
     */
    private String dealDate;

    /**
     * 带看次数
     */
    private String inspectTimes;

    /**
     * 挂牌价格
     */
    private String listPrice;

    /**
     * 调价次数
     */
    private String times;

    /**
     * 浏览
     */
    private String viewTimes;

    /**
     * 抓取时间
     */
    private String crawTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");

    /**
     * 区县
     */
    private String county;

    /**
     * 小区
     */
    private String district;

    /**
     * 是否需要爬取
     */
    private Boolean reCrawl;

    /**
     * 区域
     */
    private String region;

    private String areaUse;

    private String bJiegou;

    private String buildType;

    private String buildYear;

    private String chanquan;

    private String elevator;

    private String gongnuan;

    private String huxing;

    private String innerArea;

    private String listDate;

    private String louceng;

    private String orientation;

    private String ownerRight;

    private String rJiegou;

    private String roomYear;

    private String tihu;

    private String tradingRight;

    private String zhuangxiu;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 更新时间
     */
    private Date gmtModified;

    public static LianjiaChengjiaoBuilder builder() {
        return new LianjiaChengjiaoBuilder().crawTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
    }
}