package com.lcc.framework.session;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.framework.util.CookieUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lcc on 2016/11/29.
 */
public class SessionCookieFilter extends HttpServlet implements Filter {

    private static final long serialVersionUID = -6516046520244652987L;

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie cookie = CookieUtils.getCookie(request, SystemConstants.COOKIE_SESSION_KEY);
        if (cookie != null) {
            String sid = cookie.getValue();
            CookieUtils.setCookie(request, response,
                    SystemConstants.COOKIE_SESSION_KEY, sid, request
                            .getSession().getMaxInactiveInterval());
        }
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
