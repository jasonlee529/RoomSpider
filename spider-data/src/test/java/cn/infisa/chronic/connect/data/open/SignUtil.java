package cn.infisa.chronic.connect.data.open;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * @Title: b
 * @Description:
 * @author libo
 * @date 2020/4/3 20:14
 * @Version 1.0
 */

/**
 * Created by yinwei on 2017/5/3.
 *
 * @desc 签名工具类
 */
public class SignUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final Logger LOG = LoggerFactory.getLogger(SignUtil.class);
    private static SignUtil instance = new SignUtil();

    public static SignUtil getInstance() {
        return instance;
    }

    /**
     * 计算签名:对除签名和图片外的所有非空参数(系统参数+业务参数)
     */
    public String getSign(Map<String, Object> map, String appsecret) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String info = getSortString(map, appsecret);
        LOG.debug("待加密字符串:" + info);
        byte[] infoMd5 = encryptMD5(info.getBytes("UTF-8"));
        String sign = byte2hex(infoMd5);
        LOG.debug("客户端签名SIGN:" + sign);
        return sign;
    }

    /**
     * 按key字母先后顺序排序
     */
    private String getSortString(Map<String, Object> map, String appsecret) {
        String[] keys = map.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder stringBuilder = new StringBuilder("appsecret");
        for (String key : keys) {
            String value = map.get(key).toString();
            stringBuilder.append(key).append(value);
        }
        stringBuilder.append(appsecret);
        return stringBuilder.toString();
    }

    /**
     * md5加密
     *
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    private byte[] encryptMD5(byte[] data) throws NoSuchAlgorithmException {
        byte[] bytes = null;
        MessageDigest md = MessageDigest.getInstance("MD5");
        bytes = md.digest(data);
        return bytes;
    }

    /**
     * 二进制转换为大写的十六进制
     *
     * @param bytes
     * @return
     */
    private String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
}

