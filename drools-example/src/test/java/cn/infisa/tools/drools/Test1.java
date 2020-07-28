package cn.infisa.tools.drools;

import com.drools.core.KieTemplate;
import com.example.demo03.Demo03Application;
import com.supermy.rules.pojo.Vip;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo03Application.class)
public class Test1 {
    @Autowired
    private KieTemplate kieTemplate;

    @Before
    public void before() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void test1() {
        KieSession kieSession = kieTemplate.getKieSession("Login.drl", "a.drl", "b.drl", "c.drl", "Message.drl", "MyMarket.xls", "RuleInExcel.xls", "age1.drl", "age2.drl");
        kieSession.insert(new Vip());
        kieSession.insert(new Vip());
        kieSession.insert(new Vip());
        kieSession.fireAllRules();
    }

    @Test
    public void test2() {
        KieSession kieSession = kieTemplate.getKieSession("Login.drl", "a.drl", "b.drl", "c.drl", "Message.drl", "MyMarket.xls", "RuleInExcel.xls", "age1.drl", "age2.drl");
        EntryPoint entryPoint = kieSession.getEntryPoint("gate1");
        entryPoint.insert(1d);
        entryPoint.insert(2d);
        entryPoint.insert(3d);
        entryPoint.insert(4d);
        entryPoint.insert(5d);
        entryPoint.insert(6d);
        entryPoint = kieSession.getEntryPoint("age-gate1");
        entryPoint.insert(1d);
        entryPoint.insert(2d);
        entryPoint.insert(3d);
        entryPoint.insert(4d);
        entryPoint.insert(5d);
        entryPoint.insert(6d);
        kieSession.fireAllRules();
    }

    @Test
    public void test3() {
        KieSession kieSession = kieTemplate.getKieSession("t1k.drl", "RuleInExcel.xls");
        for (int i = 1; i <= 10000; i++) {
            kieSession.insert((double) i);
        }
        kieSession.fireAllRules();
    }

    @Test
    public void test4() {
        KieSession kieSession = kieTemplate.getKieSession("t5k.drl", "RuleInExcel.xls");
        for (int i = 1; i <= 10000; i++) {
            kieSession.insert((double) i);
        }
        kieSession.fireAllRules();
    }

    @Test
    public void test5() {
        KieSession kieSession = kieTemplate.getKieSession("t1w.drl", "RuleInExcel.xls");
        for (int i = 1; i <= 10000; i++) {
            kieSession.insert((double) i);
        }
        kieSession.fireAllRules();
    }

    @Test
    public void test6() {
        List<String> files = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            files.add("ttt" + i + ".drl");
        }
        KieSession kieSession = kieTemplate.getKieSession(files.toArray(new String[]{}));
        for (int i = 1; i <= 10000; i++) {
            kieSession.insert((double) i);
        }
        kieSession.fireAllRules();
    }
    @Test
    public void test7() {
        List<String> files = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            files.add("ttt" + i + ".drl");
        }
        KieSession kieSession = kieTemplate.getKieSession(files.toArray(new String[]{}));
        for (int i = 1; i <= 10000; i++) {
            kieSession.insert((double) i);
        }
        kieSession.fireAllRules();
    }
    @Test
    public void test8() {
        List<String> files = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            files.add("ttt" + i + ".drl");
        }
        KieSession kieSession = kieTemplate.getKieSession(files.toArray(new String[]{}));
        for (int i = 1; i <= 10000; i++) {
            kieSession.insert((double) i);
        }
        kieSession.fireAllRules();
    }
}
