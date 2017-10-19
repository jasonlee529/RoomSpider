package cn.lee.wx.secret.service;

/**
 * 读取缓存access toke 的业务类
 * Created by jason on 17-10-19.
 */

import java.io.IOException;
import java.util.Map;

import cn.lee.wx.secret.entity.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

@Service
public class AccessTokenService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, AccessToken> tokens = Maps.newHashMap();

    private ObjectMapper mapper = new ObjectMapper();

    public AccessToken getAccesToken(String appId, String appSecret) {
        String key = appId + appSecret;
        AccessToken token = tokens.get(appId + appSecret);
        if (token == null) {
            token = requestToken(appId, appSecret);
        } else if (token.isExpired()) {
            tokens.remove(key);
            token = requestToken(appId, appSecret);
        }
        return token;
    }

    private AccessToken requestToken(String appId, String appSecret) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet method = new HttpGet(url);
        try {
            HttpResponse response = client.execute(method);
            if (response.getStatusLine().getStatusCode() == 200) {
                Map result = mapper.readValue(response.getEntity().getContent(), Map.class);
                if (result.containsKey("access_token")) {
                    AccessToken token = new AccessToken();
                    token.setAccessToken(String.valueOf(result.get("access_token")));
                    token.setExpiresIn(Integer.valueOf(String.valueOf(result.get("expires_in"))));

                    return token;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            //throw new Exception("获取AcessToken失败，"+result.get("errmsg"));
        }

        return null;
    }
}
