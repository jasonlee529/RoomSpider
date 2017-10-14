package cn.lee.wx.web;

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
//        DigestUtils.shaHex(list.toString());
//        try:
//        data = web.input()
//        if len(data) == 0:
//        return "hello, this is handle view"
//        signature = data.signature
//        timestamp = data.timestamp
//        nonce = data.nonce
//        echostr = data.echostr
//        token = "xxxx" #请按照公众平台官网\基本配置中信息填写
//
//        list = [token, timestamp, nonce]
//        list.sort()
//        sha1 = hashlib.sha1()
//        map(sha1.update, list)
//        hashcode = sha1.hexdigest()
//        print "handle/GET func: hashcode, signature: ", hashcode, signature
//        if hashcode == signature:
//        return echostr
//            else:
//        return ""
//        except Exception, Argument:
//        return Argument
        return "fdsafs";
    }
}
