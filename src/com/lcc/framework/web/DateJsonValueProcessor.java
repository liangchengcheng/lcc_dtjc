package com.lcc.framework.web;

import java.util.Date;

import com.lcc.framework.util.DateUtil;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * Created by lcc on 2016/12/1.
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

    public DateJsonValueProcessor() {
    }

    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        String[] obj = {};
        if (value != null) {
            if (value instanceof Date[]) {
                Date[] dates = (Date[]) value;
                obj = new String[dates.length];
                for (int i = 0; i < dates.length; i++) {
                    obj[i] = DateUtil.dateToStr(dates[i]);
                }
            }
        }
        return obj;
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value != null) {
            if (value instanceof Date) {
                String str = DateUtil.dateToStr((Date)value);
                return str;
            }
        }
        return value;
    }

}

