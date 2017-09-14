package cn.lee.housing.spider.lianjia.spider;

/**
 * Created by jason on 17-9-14.
 */
public class PageProcessException extends RuntimeException {
    public PageProcessException() {
        super();
    }


    public PageProcessException(String s) {
        super(s);
    }


    public PageProcessException(String message, Throwable cause) {
        super(message, cause);
    }


    public PageProcessException(Throwable cause) {
        super(cause);
    }

}
