package cn.infisa.tools.km.ql;


import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

public class TestExpress {

    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        runner.addOperator("any",new OperatorAnyN("any"));
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//        context.put("h", );
        context.put("v2", 2);
        context.put("v3", 3);
        context.put("v4", 4);
        Object r = runner.execute("v1 == (v2+v3+v4) && 2==1", context, null, true, false, 1000);
        System.out.println(r);
        r = runner.execute("any {} == 0", context, null, true, false, 1000);
        System.out.println(r);

    }
}
