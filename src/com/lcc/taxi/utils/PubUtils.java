package com.lcc.taxi.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lcc on 2016/12/10.
 */
public class PubUtils {

    /**
     * 字符串转日期
     */
    public static Date str2Date(String str) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(str);
    }

    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(0);
        Date currentTime_2 = formatter.parse(dateString,pos);
        return currentTime_2;
    }

    public static String getUUID32Util() {
        String s = UUID.randomUUID().toString();
        String uuid = s.substring(0, 8) + s.substring(9, 13)
                + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
        return uuid;
    }
}
