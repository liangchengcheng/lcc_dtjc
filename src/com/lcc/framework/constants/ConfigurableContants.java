package com.lcc.framework.constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lcc on 2016/11/29.
 */
public class ConfigurableContants {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurableContants.class);

    protected static Properties props = new Properties();

    protected static void init(String propertyFileName) {
        InputStream in = null;
        try {
            in = ConfigurableContants.class.getResourceAsStream(propertyFileName);
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            logger.error("load " + propertyFileName + " into Contants error");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
    }

    protected static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }
    protected static String getProperty(String key) {
        return props.getProperty(key);
    }
}
