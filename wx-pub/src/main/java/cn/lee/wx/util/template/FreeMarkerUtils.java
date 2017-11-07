package cn.lee.wx.util.template;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.core.util.StringBuilderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 使用freemarker 解析模板
 * Created by jason on 17-11-6.
 */
public class FreeMarkerUtils {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 读取velocity的模板的帮助类
     */
    private Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 根据文件路径加载模板
     *
     * @param templateLocation ，相对veloctity resource loader的路径
     * @return
     */
    public String mergeTemplate(String templateLocation) {
        return mergeTemplate(templateLocation, new HashMap<String, Object>());
    }

    /**
     * 根据文件路径加载模板,默认utf-8编码
     *
     * @param templateLocation 模板路径，相对veloctity resource loader的路径
     * @param model            数据上下文
     * @return
     */
    public String mergeTemplate(String templateLocation, Map model) {
        return mergeTemplate(templateLocation, "utf-8", model);
    }

    /**
     * 根据文件路径加载模板
     *
     * @param templateLocation ，相对veloctity resource loader的路径
     * @param encoding         编码格式
     * @param model
     * @return
     */
    public String mergeTemplate(String templateLocation, String encoding, Map<String, Object> model) {
        return doMergeTemplate(templateLocation, encoding, model);
    }

    protected String doMergeTemplate(String templateLocation, String encoding, Map<String, Object> model) {
        String result = "";
        Template tpl = null;
        try {
            tpl = configuration.getTemplate(templateLocation);
            Writer writer = new StringBuilderWriter();
            tpl.process(model, writer);
            result = writer.toString();
        } catch (IOException e) {
            logger.error("模板解析失败：" + e.getMessage());
            e.printStackTrace();
        } catch (TemplateException e) {
            logger.error("模板解析失败：" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}

