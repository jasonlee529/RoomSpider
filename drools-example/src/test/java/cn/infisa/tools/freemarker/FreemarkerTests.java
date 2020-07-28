package cn.infisa.tools.freemarker;

import com.example.demo03.Demo03Application;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author libo
 * @Title: FreemarkerTestS
 * @Description:
 * @date 2020/7/28 10:07
 * @Version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo03Application.class)
public class FreemarkerTests {
    @Autowired
    private freemarker.template.Configuration configuration;

    @Before
    public void before() {
        configuration.setNumberFormat("#");
    }

    @Test
    public void test1() {
        String result = "";
        Template tpl = null;
        String templateLocation = "drl.ftl";
        Map model = new HashMap();
        try {
            tpl = this.configuration.getTemplate(templateLocation);
            Writer writer = new StringWriter();
            tpl.process(model, writer);
            result = writer.toString();
            FileWriter fw = new FileWriter("ttt.drl");
            fw.write(result);
            fw.close();
        } catch (IOException var7) {
            log.error(var7.getMessage(), var7);
        } catch (TemplateException var8) {
            log.error(var8.getMessage(), var8);
        }

    }

    @Test
    public void test2() {
        String result = "";
        Template tpl = null;
        String templateLocation = "drl2.ftl";
        Map model = new HashMap();
        for (int i = 1; i < 1000; i++) {
            model.put("nums", i);
            model.put("prefix", "bath_rule_" + i);
            try {
                tpl = this.configuration.getTemplate(templateLocation);
                Writer writer = new StringWriter();
                tpl.process(model, writer);
                result = writer.toString();
                FileWriter fw = new FileWriter("src/main/resources/rules/generate/batch_rule/ttt" + i + ".drl");
                fw.write(result);
                fw.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (TemplateException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
