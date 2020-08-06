package cn.infisa.tools.km.ql;


import com.guahao.ai.aicdss.biz.util.ql.StringParserUtil;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestExpress {

    public static void main(String[] args) throws Exception {
        ExpressRunner runner = new ExpressRunner(false,true);
        runner.addOperator("any", new OperatorAnyN("any"));
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//        context.put("h", );
        context.put("v2", 2);
        context.put("v3", 3);
        context.put("v4", 4);
        context.put("v5", new Integer[]{1, 2});
        Object r = null;
//        r = runner.execute("v1 == (v2+v3+v4) && 2==1", context, null, true, false, 1000);
//        System.out.println(r);
        r = runner.execute("v5 any NewList(1,2,3) == 0 ", context, null, true, true, 1000);
        System.out.println(r);
        System.out.println(parseOption("{1,2,3}"));
        r = runner.execute("v5 any [1,3]  ", context, null, true, true, 1000);
        System.out.println(r);

    }

    private static String parseOption(String param) {
        StringParserUtil questionNameUtil = new StringParserUtil("{",new String[]{"}"});
        List<String> options = questionNameUtil.parse(param);
        options = options.stream().distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(options)){
            return param;
        }
        Map<String,String> optionReplaceMap = new HashMap<>();
        for (String option : options){
            optionReplaceMap.put(option,getNewOption(option));
        }
        for (String option : options){
            param = param.replaceAll(Pattern.quote(option),optionReplaceMap.get(option));
        }
        param = param.replaceAll("\\{","");
        param = param.replaceAll("\\}","");
        return param;
    }
    private static String getNewOption(String option) {
        String[] optionArray = option.split("\\|\\|");
        StringBuilder sb = new StringBuilder();
        sb.append(" [");
        for (String opt : optionArray){
            sb.append("\"").append(opt).append("\"").append(",");
        }
        return sb.substring(0,sb.length() - 1) + "] ";
    }
}
