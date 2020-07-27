package cn.lee.housing.proxy.valid;

import cn.lee.housing.proxy.model.ProxyModel;
import cn.lee.housing.proxy.repository.ProxyModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author libo
 * @Title: ProxyValidService
 * @Description:
 * @date 2020/6/16 15:08
 * @Version 1.0
 */
@Slf4j
public class ProxyValidService {

    @Autowired
    private ProxyModelMapper proxyModelMapper;


    public void valid() {
        List<ProxyModel> proxyModelList = proxyModelMapper.findNextValid(System.currentTimeMillis());
        for (ProxyModel proxy : proxyModelList) {
            if (ProxyValidator.valid("www.baidu.com", proxy.getHost(), Integer.parseInt(proxy.getPort()))) {
                proxy.setQuality(proxy.getQuality() + 1);
                proxy.setNextVerifyTime(System.currentTimeMillis() + System.currentTimeMillis() + 1000 * 60 * 60 * 6);
            } else {
                proxy.setQuality(proxy.getQuality() - 1);
                proxy.setNextVerifyTime(System.currentTimeMillis() + System.currentTimeMillis() + 1000 * 60 * 60 * 6);
            }
        }
        proxyModelMapper.updateBatchSelective(proxyModelList);
    }
}
