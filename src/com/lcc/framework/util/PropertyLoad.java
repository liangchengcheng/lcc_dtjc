package com.lcc.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Set;

/**
 * Created by lcc on 2016/11/29.
 */
public class PropertyLoad {
    /**
     * 私有构造方法
     */
    private PropertyLoad(){}

    /**
     * 单例PropertyLoad对象
     */
    private static final PropertyLoad INSTANCE =new PropertyLoad();

    /**
     *properties 对象
     */
    private Properties pro=new Properties();

    /**
     * 加载计数器
     */
    private static int num=0;


    /**
     * 返回单例对象
     */
    public static PropertyLoad getInstance(String filename) {
        //加载配置文件
        if(num==0) {
            try {
                INSTANCE.setProperties(filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    /**
     * 加载一个properties属性文件,是通过文件的路径来指定。
     * 属性文件可以创建在任何一个位置。
     * @throws IOException 输入输出异常。
     */
    private void setProperties(String filename) throws Exception {
        if(num==0) {
            num=1;
        }
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
        pro.load(in);
        in.close();
    }

    /**
     * 获取配置文件的所有key
     * @throws IOException 输入输出异常。
     */
    public Set getKey() throws Exception {
        return pro.keySet();
    }

    /**
     *  返回属性文件中指定 键 的值。
     */
    public String getValue(String key) {
        try {
            if(pro.getProperty(key)!=null){
                String value=new String(pro.getProperty(key).getBytes("ISO-8859-1"),"utf-8");
                return value;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
