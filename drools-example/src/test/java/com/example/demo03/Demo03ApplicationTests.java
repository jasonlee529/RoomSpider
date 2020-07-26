package com.example.demo03;

import com.drools.core.KieTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo03ApplicationTests {

    @Autowired
    private KieTemplate kieTemplate;

    @Before
    public void before() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void test01() {
        KieSession kieSession = kieTemplate.getKieSession("a.drl");
        EntryPoint entryPoint = kieSession.getEntryPoint("gate1");
        entryPoint.insert(1d);
        entryPoint.insert(2d);
        entryPoint.insert(3d);
        entryPoint.insert(4d);
        entryPoint.insert(5d);
        entryPoint.insert(6d);
        kieSession.fireAllRules();
    }

}
