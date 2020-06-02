package cn.lee.housing.spider.lianjia.spider.processor.room;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author libo
 * @Title: RoomIdProvider
 * @Description:
 * @date 2020/5/21 19:39
 * @Version 1.0
 */
public class RoomIdProvider {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RoomIdProvider() {
    }

    public static String parseRoomId(String url) {
        if (StringUtils.isBlank(url)) {
            return "";
        }
        //https://bj.lianjia.com/chengjiao/BJDC89691903.html
        String[] urls = StringUtils.split(url, "/");
        ArrayUtils.reverse(urls);
        String roomId = urls[0].replace(".html", "");
        return roomId;
    }

    public static void main(String[] args) {
        System.out.println(parseRoomId("/https://bj.lianjia.com/chengjiao/BJDC89691903.html"));
    }
}
