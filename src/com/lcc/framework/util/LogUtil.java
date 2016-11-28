package com.lcc.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录与打印类
 * <p>
 * 修改记录:
 * <p>
 * <p>
 * 20040102:添加了ConsoleAppender（对于没有设置配置文件的情况下)
 */
public class LogUtil {

    private static Logger log;

    /**
     * Constructor for the LogUtil object
     */
    private LogUtil() {
        log = LoggerFactory.getLogger("");
    }

    /**
     * Gets the Logger attribute of the LogUtil class
     *
     * @return The Logger value
     */
    public static Logger getLogger() {
        if (log == null) {
            log = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        }
        return log;
    }

}