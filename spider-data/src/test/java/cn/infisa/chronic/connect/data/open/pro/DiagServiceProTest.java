package cn.infisa.chronic.connect.data.open.pro;

import cn.infisa.chronic.connect.data.open.SignUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: a
 * @Description:
 * @author libo
 * @date 2020/4/3 20:13
 * @Version 1.0
 */

/**
 * Created by yinwei on 2017/5/3.
 *
 * @desc API接口调用demo
 */
public class DiagServiceProTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Charset CHARSET = Charset.forName("UTF-8");
    private Map<String, Object> map = new HashMap<String, Object>();

    private String appkey = "23846105";                              //开放平台分配的appkey
    private String appsecret = "A7C23E7577DDDBFAC334C41D27C9453E";                //开放平台分配的appsecret
    private String method = "guahao.stdpubhospital.chronic.uploaddataserv";              //API接口
    private String accessToken = "XXXXXXXXXXXX";                 //令牌access_token,用户授权接口必传
    private String timestamp = "20200403210809";                 //时间戳
    //    JsonMapper mapper = JsonMapper.nonEmptyMapper();
//    HospDataDTO dto = new HospDataDTO();
//    Map data = new HashMap();
    String dto = "{\"hospStdId\":\"123\",\"visitId\":\"diag\",\"patientId\":\"diag\",\"doctorId\":\"diag\",\"type\":\"diag\"}";
    //    String data = "{\"patient\":{\"visitId\":\"vistId\",\"hospStdId\":\"hospStdId\",\"patientId\":\"patientId\",\"doctorId\":\"doctorId\",\"name\":\"name\",\"age\":\"66\",\"sex\":\"男\",\"idNo\":\"12345\"},\"diags\":[{\"diseaseCode\":\"1\",\"diseaseName\":\"高血压\"},{\"diseaseCode\":\"2\",\"diseaseName\":\"糖尿病\"}]}";
    String data = "{\"patient\":{\"visitId\":\"874e4ce0-2b48-4977-8b4e-cc075be84d9f\",\"doctorId\":\"1001\",\"patientId\":\"00009659\",\"sex\":\"男\",\"clinicNo\":\"1912023400\",\"name\":\"于                新\",\"idNo\":\"110101199003077491\",\"age\":\"30\"},\"diags\":[{\"diseaseName\":\"慢性骨髓增殖性肿瘤\",\"diseaseCode\":\"D47.100x004\"},{\"diseaseName\":\"肝硬化\",\"diseaseCode\":\"K74.100\"}]}";
    /**
     * 测试环境：
     * http://t.gw.api.guahao-inc.com/router/rest  对应测试环境分配appkey/appsecret
     * <p>
     * 线上环境：
     * http://gw.api.guahao.com/router/rest        对应线上环境分配appkey/appsecret
     */
    private String host = "https://openapi.guahao.com/router/rest";


    @Before
    public void setUp() {
        data = "{ \"patient\": {\n" +
                "        \"sex\": \"女\",\n" +
                "        \"idNo\": \"120101194603204027\",\n" +
                "        \"age\": \"74\",\n" +
                "        \"name\": \"贾淑霞\"\n" +
                "    },\"diags\":[{\"diseaseName\":\"习惯性便秘\",\"diseaseCode\":\"K59.001\"},{\"diseaseName\":\"（新）原发性高血压\",\"diseaseCode\":\"I10.x09\"}]}";
        dto = "{\"visitId\":\"61422FCC-7AB4-443E-B922-520318B4F4B9\",\"doctorId\":\"183686CC-F04D-44D8-904B-4978F99FC80C\",\"patientId\":\"406E338B-4223-43FB-AF44-ACA1434920A3\",\"hospStdId\":\"40121578-2\",\"type\":\"diag\"}";
        timestamp = "20200714092123";
        data = "{\"patient\":{\"sex\":\"女\",\"idNo\":\"35454545415\",\"age\":\"3\",\"Name\":\"545\"},\"diags\":[{\"diseaseName\":\"上呼吸道感染\",\"diseaseCode\":\"J06.900x003\"}]}";
        dto = "{\"visitId\":\"9E55BB4E-32AB-4602-84AE-7CF80F479870\",\"doctorId\":\"\",\"patientId\":\"15c1602a-a0f0-4208-9c99-71dcbe85a1bf\",\"hospStdId\":\"40121578-2\",\"type\":\"diag\"}";
        timestamp = "20200719025458";
        /**
         * 所有的非空系统参数和请求参数（签名sign和图片参数除外）放入Map中
         */
        map.put("method", method);
        map.put("appkey", appkey);
        map.put("timestamp", timestamp);
        //map.put("accessToken", accessToken);

        map.put("hospDataDTO", dto);   //业务参数
        map.put("data", data);   //业务参数

    }

    @Test
    public void test() throws NoSuchAlgorithmException, IOException {
        HttpPost httpPost = new HttpPost(host);

        //计算签名：所有的非空系统参数和请求参数
        String sign = SignUtil.getInstance().getSign(map, appsecret);
        /**
         * 头消息参数
         */
        httpPost.addHeader("appkey", appkey);
        httpPost.addHeader("method", method);
        httpPost.addHeader("timestamp", timestamp);

        httpPost.addHeader("sign", sign);
        //httpPost.addHeader("accessToken", accessToken);

        /**
         * 请求(应用)参数
         */
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

        urlParameters.add(new BasicNameValuePair("hospDataDTO", dto));
        urlParameters.add(new BasicNameValuePair("data", data));
        HttpEntity postParams = new UrlEncodedFormEntity(urlParameters, CHARSET);
        httpPost.setEntity(postParams);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        String result = EntityUtils.toString(httpResponse.getEntity(), CHARSET);
        System.out.println(result);
    }
}

