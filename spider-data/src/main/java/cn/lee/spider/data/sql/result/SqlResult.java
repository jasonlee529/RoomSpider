package cn.lee.spider.data.sql.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sql执行结果
 * Created by jason on 17-12-29.
 */
public class SqlResult {

    public static final int SUCCESS = 1;
    public static final int ERROR = 1;

    private int code = SUCCESS;
    private List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();


    private String msg;

    public SqlResult() {

    }

    public SqlResult(List<Map<String, Object>> result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }
}
