package cn.lee.wx.util.template;

/**
 * Created by jason on 17-11-6.
 */
public class VelocityUtils {
//
//    /**
//     * 读取velocity的模板的帮助类
//     */
//    private VelocityEngine velocityEngine;
//
//    public void setVelocityEngine(VelocityEngine velocityEngine) {
//        this.velocityEngine = velocityEngine;
//    }
//
//    /**
//     * 根据文件路径加载模板
//     *
//     * @param templateLocation ，相对veloctity resource loader的路径
//     * @return
//     */
//    public String mergeTemplate(String templateLocation) {
//        return mergeTemplate(templateLocation, new HashMap<String, Object>());
//    }
//
//    /**
//     * 根据文件路径加载模板,默认utf-8编码
//     *
//     * @param templateLocation 模板路径，相对veloctity resource loader的路径
//     * @param model            数据上下文
//     * @return
//     * @throws VelocityException
//     */
//    public String mergeTemplate(String templateLocation, Map model) {
//        return mergeTemplate(templateLocation, "utf-8", model);
//    }
//
//    /**
//     * 根据文件路径加载模板
//     *
//     * @param templateLocation ，相对veloctity resource loader的路径
//     * @param encoding         编码格式
//     * @param model
//     * @return
//     */
//    public String mergeTemplate(String templateLocation, String encoding, Map<String, Object> model) {
//        return doMergeTemplate(templateLocation, encoding, model);
//    }
//
//    protected String doMergeTemplate(String templateLocation, String encoding, Map<String, Object> model) {
//        String result = "";
//        try {
//            result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, encoding, model);
//        } catch (VelocityException e) {
//            e.printStackTrace();
//        } finally {
//            return result;
//        }
//    }
//
//    /**
//     * 合并模板
//     *
//     * @param tpl
//     * @param params
//     * @return
//     */
//    public String merge(String tpl, Map<String, Object> params) {
//        StringWriter out = new StringWriter();
//        VelocityContext context = new VelocityContext(params);
//        velocityEngine.evaluate(context, out, "", tpl);
//        return out.toString();
//    }
}

