package com.lcc.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Created by lcc on 2016/11/29.
 */
public class BeanUtils {
    protected static Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    private BeanUtils() {
    }

    /**
     * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
     */
    public static Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException {
        Field field = getDeclaredField(object, fieldName);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常{}", e.getMessage());
        }
        return result;
    }

    /**
     * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
     */
    public static void setFieldValue(Object object, String fieldName, Object value) throws NoSuchFieldException {
        Field field = getDeclaredField(object, fieldName);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }
    }

    /**
     * 循环向上转型,获取对象的DeclaredField.
     */
    public static Field getDeclaredField(Object object, String fieldName) throws NoSuchFieldException {
        Assert.notNull(object);
        return getDeclaredField(object.getClass(), fieldName);
    }

    /**
     * 循环向上转型,获取类的DeclaredField.
     */
    @SuppressWarnings("unchecked")
    public static Field getDeclaredField(Class clazz, String fieldName) throws NoSuchFieldException {
        Assert.notNull(clazz);
        Assert.hasText(fieldName);
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
            }
        }
        throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + fieldName);
    }


    /**
     * bean属性拷贝
     * @param dest
     * @param orig
     */
    public static void copyPropertiesWithoutNull(Object dest,Object orig){
        PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(orig);
        String origClass = orig.getClass().getName();
        for(PropertyDescriptor pd:pds){
            try {
                Object oValue = PropertyUtils.getProperty(orig, pd.getName());
                if (origClass.equals(pd.getClass().getName())) continue;
                if(oValue!=null){
                    PropertyUtils.setProperty(dest, pd.getName(), oValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
