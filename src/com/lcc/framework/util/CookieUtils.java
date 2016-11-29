package com.lcc.framework.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by lcc on 2016/11/29.
 */
public class CookieUtils {
    public static String cookieDomain = "";

    public static String cookiePath = "/";

    /**
     * 从cookie中获得指定key的value
     */
    public static String getValueFromCookie(HttpServletRequest request,
                                            String cookieKey) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieKey)) {
                    cookieValue = cookies[i].getValue();
                }
            }
        }
        return cookieValue;
    }

    /**
     * 将该key和value加到Cookie中
     */

    public static void addCookie(HttpServletRequest request,
                                 HttpServletResponse response, String key, String value,
                                 Integer cookieMaxAge, String path, String domain) {
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);
        if (cookieMaxAge != null)
            cookie.setMaxAge(cookieMaxAge);
        if (path != null && !path.trim().equals("")) {
            cookie.setPath(path);
        }
        response.addCookie(cookie);
    }

    /**
     * 取得cookie对象
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                return cookies[i];
            }
        }
        return null;
    }

    /**
     * 设置COOKIE
     */
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        if (cookieDomain != null && cookieDomain.indexOf('.') != -1) {
            cookie.setDomain('.' + cookieDomain);
        }
        cookie.setPath(cookiePath);
        response.addCookie(cookie);
    }


}
