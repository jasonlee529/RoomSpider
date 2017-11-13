package cn.lee.wx.deal.service;

import java.util.Map;

import cn.lee.wx.data.service.ConfigSqlTplService;
import cn.lee.wx.deal.repository.DealMonthRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17-11-7.
 */
@Service
public class DealMonthService {

    @Autowired
    private ConfigSqlTplService configSqlTplService;
    @Autowired
    private DealMonthRepository dealMonthRepository;

    public Map monthData() {
        return dealMonthRepository.monthData();
    }


    public void kpi() {
        configSqlTplService.executeSql(1, null);
    }
}
