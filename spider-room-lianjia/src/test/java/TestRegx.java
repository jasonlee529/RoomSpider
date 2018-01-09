import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by jason on 17-8-27.
 */

public class TestRegx {
    @Test
    public void testReg() {
        String str = "https://bj.lianjia.com/chengjiao/101101582869.html";
        String pattern = "[1-9]\\d*";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        System.out.println(m.find());
        System.out.println(m.group());
    }


    @Test
    public void test() {
        String area = "100.24㎡          ";
        area = StringUtils.trim(area);
        area = StringUtils.replace(area, "㎡", " ");
        System.out.println(area);

    }
}
