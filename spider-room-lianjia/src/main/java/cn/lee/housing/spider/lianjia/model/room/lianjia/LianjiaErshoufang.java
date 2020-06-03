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
public class LianjiaErshoufang {
    private Long id;

    private String area;

    private String areaUse;

    private String attentionTimes;

    private String avgPrice;

    private String bJiegou;

    private String buildType;

    private String buildYear;

    private String chanquan;

    private String county;

    private String crawTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");

    private String district;

    private String diya;

    private String elevator;

    private String gongnuan;

    private String huxing;

    private String innerArea;

    private String inspectTimes;

    private String lastTradeDate;

    private String listDate;

    private String louceng;

    private String orientation;

    private String ownerRight;

    private String rJiegou;

    private Boolean reCrawl;

    private String region;

    private String roomDeeed;

    private String roomId;

    private String roomYear;

    private String subTitle;

    /**
     * 成交状态
     */
    private String tihu;

    private String title;

    private String totalPrice;

    private String tradingRight;

    private String zhuangxiu;

    private String status;

    /**
     * 更新时间
     */
    private Date gmtModified;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    public static LianjiaErshoufangBuilder builder() {
        return new LianjiaErshoufangBuilder().crawTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
    }
}