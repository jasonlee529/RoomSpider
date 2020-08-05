package cn.infisa.tools.km.ql;

import com.ql.util.express.Operator;
import com.ql.util.express.exception.QLException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class OperatorAnyN extends Operator {
    public OperatorAnyN(String name) {
        this.name = name;
    }
    public OperatorAnyN(String aAliasName, String aName, String aErrorInfo) {
        this.name = aName;
        this.aliasName = aAliasName;
        this.errorInfo = aErrorInfo;
    }

    @Override
    public Object executeInner(Object[] list) throws Exception {
        return executeInner(list[0], list[1]);
    }

    public Object executeInner(Object small, Object big) throws Exception {
        int result = 0;
        String msg = "anyN ";
        if(!(small.getClass().isArray() || small instanceof List)){
            throw new QLException(msg + small.getClass().getName());
        }else if (!(big.getClass().isArray() || big instanceof List)){
            throw new QLException(msg + big.getClass().getName());
        }
        List<Object> small_temp = null;
        List<Object> big_temp = null;
        if(small.getClass().isArray()){
            small_temp = new ArrayList<>();
            for(int i=0; i<Array.getLength(small); i++){
                small_temp.add(Array.get(small, i));
            }
        }
        if(big.getClass().isArray()){
            big_temp = new ArrayList<>();
            for(int i=0; i<Array.getLength(big); i++){
                big_temp.add(Array.get(big, i));
            }
        }
        if(small instanceof List){
            small_temp = (List<Object>)(small);
        }
        if(big instanceof List){
            big_temp = (List<Object>)(big);
        }
        if(small_temp != null && big_temp != null) {
            for (Object i : small_temp) {
                if (big_temp.contains(i)) {
                    result++;
                }
            }
        }
        return result;
    }
}