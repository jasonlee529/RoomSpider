package cn.lee.wx.secret.service;

import org.junit.Test;

/**
 * Created by jason on 17-10-19.
 */
public class TestAccessTokenService {

    @Test
    public void testReqToken() {
        AccessTokenService service = new AccessTokenService();
        service.getAccesToken("wx4cbbda8b2cc5f1b7", "befed58bb397fc1b682f417b37247eeb");

    }
}
