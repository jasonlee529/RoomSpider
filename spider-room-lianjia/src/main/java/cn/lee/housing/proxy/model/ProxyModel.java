package cn.lee.housing.proxy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author libo
 * @Title: ProxyModel
 * @Description:
 * @date 2020/6/13 8:59
 * @Version 1.0
 */
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
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 更新时间
     */
    private Date gmtModified;
}
