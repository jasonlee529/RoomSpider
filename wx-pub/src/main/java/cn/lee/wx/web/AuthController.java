package cn.lee.wx.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 授权的controller
 */
@RestController
public class AuthController {

    @RequestMapping("wx")
    public String wx(HttpServletRequest request) {
        Enumeration<String> params = request.getParameterNames();
        if (!params.hasMoreElements()) {
            return "hello, this is handle view";
        }
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String[] list = new String[]{
                signature, timestamp, nonce
        };
        Arrays.sort(list);
        String hashCode = new String(DigestUtils.sha1(list.toString().getBytes()));
        String secret = "16e85693f56899ec0ae0812ba62b7c12";
        String token = "xiaokele2017";
        System.out.println(signature + "______" + hashCode);
        if (StringUtils.equals(signature, hashCode)) {
            return echostr;
        } else {
            return "";
        }
    }
}
