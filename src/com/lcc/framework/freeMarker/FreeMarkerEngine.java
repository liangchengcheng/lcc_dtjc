package com.lcc.framework.freeMarker;

import java.io.StringWriter;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * Created by lcc on 2016/12/4.
 */
@Component
public class FreeMarkerEngine {

    private static Configuration cfg = null;

    /**
     * 对freemarker进行配置,配置ftl文件所在目录和默认编码
     * @param ctx	ServletContext用来获得模板目录
     */
    private Configuration getConfig(ServletContext ctx) {
        if (null == cfg) {
            cfg = new Configuration();
        }

        try {
            //cfg.setDirectoryForTemplateLoading(new File(ftlDir));
            cfg.setServletContextForTemplateLoading(ctx, "templates");
        } catch (Exception e) {
            e.printStackTrace();
        }
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        return cfg;
    }

    /**
     * 通过数据和模板名称得到渲染后的局部页面字符串
     * @param data		ftl文件中要使用到的数据
     * @param ctx       servletContext 用来获得模板目录
     * @param filename  ftl文件名
     */
    @SuppressWarnings("unchecked")
    public String getHtml(Map data, ServletContext ctx, String filename) {
        Template t = null;
        StringWriter result = new StringWriter();
        try {
            t = getConfig(ctx).getTemplate(filename);
            t.setEncoding("UTF-8");
            t.process(data, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
