package cn.lee.wx.message.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import cn.lee.wx.message.entity.Article;
import cn.lee.wx.secret.service.AccessTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17-10-19.
 */
@Service
public class GroupMessageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private AccessTokenService accessTokenService;


    public void sendMessage() throws IOException {
        List<Article> articles = Lists.newArrayList();
        Article a1 = new Article();
        a1.setThumb_media_id("dRDM-2jjZf9TUXgBfZuvq1ZazQx0xwB-bW8Lp74JbcAmH7dQqdRJSNwpNthPTcC3");
        a1.setAuthor("龙城飞将");
        a1.setAuthor("测试");
        a1.setContent("测试内容");
        articles.add(a1);
        Map<String, List> mesages = Maps.newHashMap();
        mesages.put("articles", articles);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost method = new HttpPost("https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=" + accessTokenService.getAccesToken("wx4cbbda8b2cc5f1b7", "befed58bb397fc1b682f417b37247eeb").getAccessToken());
        method.addHeader("Content-type", "application/json; charset=utf-8");
        method.setHeader("Accept", "application/json");
        method.setEntity(new StringEntity(mapper.writeValueAsString(mesages), Charset.forName("UTF-8")));
        HttpResponse response = client.execute(method);
        if (200 == response.getStatusLine().getStatusCode()) {
            logger.info(response.getEntity().toString());
        }
        logger.info(response.toString());

    }
}
