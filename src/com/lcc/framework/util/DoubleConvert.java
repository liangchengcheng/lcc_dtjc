package com.lcc.framework.util;

import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;
/**
 * Created by lcc on 2016/11/28.
 */
public class DoubleConvert extends StrutsTypeConverter {

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (Double.class == toClass) {
            String doubleStr = values[0];
            Double d = Double.parseDouble(doubleStr);
            return d;
        }
        return 0;
    }

    @Override
    public String convertToString(Map context, Object o) {
        return o.toString();
    }

}

