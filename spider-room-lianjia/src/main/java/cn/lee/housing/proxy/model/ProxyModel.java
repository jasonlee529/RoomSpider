package cn.lee.housing.proxy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProxyModel {
    private Long id;

    /**
     * IP地址
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 类型，http https socks5
     */
    private String type;

    /**
     * 地域
     */
    private String region;

    /**
     * 匿名性
     */
    private String anonymous;

    /**
     * 质量
     */
    private Integer quality;

    /**
     * 来源
     */
    private String source;

    private Integer succ;

    /**
     * 下次校验时间
     */
    private Long nextVerifyTime;
    private Date gmtCreated;
    private Date gmtModified;
    public static ProxyModelBuilder builder() {
        return new ProxyModelBuilder();
    }
}