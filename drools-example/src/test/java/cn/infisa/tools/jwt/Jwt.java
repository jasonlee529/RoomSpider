package cn.infisa.tools.jwt;

import lombok.extern.slf4j.Slf4j;
/**  
    * @Title: Jwt
    * @Description: 
    * @author libo
    * @date 2020/8/4 16:16
    * @Version 1.0
    */
@Slf4j
public class Jwt {
    private String token;

    public Jwt() {

    }

    public Jwt(String token) {
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
