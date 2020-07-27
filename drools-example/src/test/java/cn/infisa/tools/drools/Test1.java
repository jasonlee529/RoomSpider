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
}
