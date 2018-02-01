package cn.lee.housing.spider.lianjia.service.wangqian;

import cn.lee.housing.spider.lianjia.model.wangqian.*;
import cn.lee.housing.spider.lianjia.repository.wangqian.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 18-2-1.
 */
@Service
public class WangqianService {
    @Autowired
    private DayInfoDao dayInfoDao;
    @Autowired
    private MonthAgentDao monthAgentDao;
    @Autowired
    private MonthAreaDao monthAreaDao;
    @Autowired
    private MonthCountyDao monthCountyDao;
    @Autowired
    private MonthInfoDao monthInfoDao;

    public void saveDayInfo(DayInfo dayInfo) {
        dayInfoDao.save(dayInfo);
    }

    public void saveMonthAgent(MonthAgent obj) {
        monthAgentDao.save(obj);
    }

    public void saveMonthArea(MonthArea obj) {
        monthAreaDao.save(obj);
    }

    public void saveMonthCounty(MonthCounty obj) {
        monthCountyDao.save(obj);
    }

    public void saveMonthInfo(MonthInfo obj) {
        monthInfoDao.save(obj);
    }

}
