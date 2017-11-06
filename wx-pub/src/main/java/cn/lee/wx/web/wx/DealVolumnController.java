package cn.lee.wx.web.wx;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 成交的量分析
 * Created by jason on 17-11-6.
 */
@RestController
@RequestMapping(value = "deal/volumn")
public class DealVolumnController {

    /**
     * 分区或者全北京的数据量
     *
     * @param area
     * @param request
     * @return
     */
    @RequestMapping("month/{area}")
    public String area(@PathVariable String area, HttpServletRequest request) {


        return "";
    }

    @RequestMapping("month")
    public String areaAll(HttpServletRequest request) {
        return area("all", request);
    }
}
