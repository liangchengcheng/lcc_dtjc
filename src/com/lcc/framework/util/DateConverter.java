package com.lcc.framework.util;

import org.apache.struts2.util.StrutsTypeConverter;
import java.util.Date;
import java.util.Map;
import java.util.Map;

/**
 * Created by lcc on 2016/11/29.
 */
public class DateConverter extends StrutsTypeConverter {

    @Override
    public Object convertFromString(Map map, String[] values, Class toClass) {
        if (String.class == toClass) {
            String dateStr = values[0];
            if (DataTypeUtil.isEmptyStr(dateStr))return null;
            Date d = DateUtil.strToDate(dateStr);
            return d;
        } else if (Date.class == toClass) {
            String dateStr = values[0];
            if (DataTypeUtil.isEmptyStr(dateStr))return null;
            Date d = DateUtil.strToDate(dateStr);
            return d;
        }
        return null;
    }

    @Override
    public String convertToString(Map map, Object o) {
        return o.toString();
    }
}
