import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSSDB {

    private static Logger logger = LoggerFactory.getLogger(TestSSDB.class);

    public static void main(String[] args) {
        SSDB ssdb = SSDBs.simple("localhost",6379,1000);
        ssdb.set("name", "wendal").check(); // call check() to make sure resp is ok
        Response resp = ssdb.get("name");
        if (!resp.ok()) {
            // ...
        } else {
            logger.info("name=" + resp.asString());
        }
    }
}
