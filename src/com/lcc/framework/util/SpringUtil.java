package com.lcc.framework.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Created by lcc on 2016/11/28.
 */
public class SpringUtil {
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext(
            "applicationContext.xml");

    public static Object getBean(String beanName) {
        return ctx.getBean(beanName);
    }

    public static Object getBean(Class<?> beanName) {
        return ctx.getBean(beanName);
    }
}
