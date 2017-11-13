package cn.lee.wx.web.deal;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.lee.wx.deal.service.DealMonthService;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DealMonthService dealMonthService;

    /**
     * 分区或者全北京的数据量
     *
     * @param area
     * @param request
     * @return
     */
    @RequestMapping("month/{area}")
    public Map area(@PathVariable String area, HttpServletRequest request) {
        return dealMonthService.monthData();
    }

    @RequestMapping("month")
    public Map areaAll(HttpServletRequest request) {
        return area("all", request);
    }


}
