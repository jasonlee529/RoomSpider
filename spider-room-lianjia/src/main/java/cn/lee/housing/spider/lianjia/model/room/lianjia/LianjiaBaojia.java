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
public class LianjiaBaojia {
    private Long id;

    private String crawTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");

    private String price;

    private String roomId;

    private String avgPrice;

    private String info;

    private String title;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 更新时间
     */
    private Date gmtModified;

    public static LianjiaBaojiaBuilder builder() {
        return new LianjiaBaojiaBuilder();
    }
}