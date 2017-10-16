package cn.lee.wx.web;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lee.wx.util.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 授权的controller
 */
@Controller
@RequestMapping("/wechat")
public class AuthController {

    @Value("${DNBX_TOKEN}")
    private String DNBX_TOKEN;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("wx")
    public void wx(HttpServletRequest request, HttpServletResponse response) {
        logger.info(request.getPathInfo());
        logger.info(request.getRequestURI());
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        PrintWriter out = null;
        try {
            request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
            response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；boolean isGet = request.getMethod().toLowerCase().equals("get");
            out = response.getWriter();
            String signature = request.getParameter("signature");// 微信加密签名
            String timestamp = request.getParameter("timestamp");// 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");//随机字符串

            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignUtils.checkSignature(DNBX_TOKEN, signature, timestamp, nonce)) {
                logger.info("Connect the weixin server is successful.");
                response.getWriter().write(echostr);
            } else {
                logger.error("Failed to verify the signature!");
            }
        } catch (Exception e) {
            logger.error("Connect the weixin server is error.");
            out.print(e.getMessage());
        } finally {
            out.close();
        }
    }
}
