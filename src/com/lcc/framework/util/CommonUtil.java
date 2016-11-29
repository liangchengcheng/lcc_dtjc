package com.lcc.framework.util;

import java.util.List;
import java.util.Map;
/**
 * Created by lcc on 2016/11/29.
 */
public class CommonUtil {
    @SuppressWarnings({ "rawtypes" })
    public static boolean isNotEmpty(Object... objs){
        for (Object obj : objs) {
            if (null==obj) {
                return false;
            }
            if (obj instanceof String) {
                if ("".equals(obj.toString().trim())||"null".equals(obj.toString().trim().toLowerCase())) {
                    return false;
                }
            }else if(obj instanceof List){
                List list = (List)obj;
                if(list.size()==0){
                    return false;
                }
            }else if(obj instanceof Map){
                Map map = (Map)obj;
                if(map.size()==0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isNotEmpty("ccc"));
    }
}
