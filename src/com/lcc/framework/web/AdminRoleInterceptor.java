package com.lcc.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * Created by lcc on 2016/12/1.
 */
public class AdminRoleInterceptor implements Interceptor {

    private String path;

    private static final long serialVersionUID = -4911122707518868115L;

    public void destroy() {
    }

    public void init() {
    }

    public String intercept(ActionInvocation actionInvocation) throws Exception {
        HttpServletRequest req = ServletActionContext.getRequest();
        HttpServletResponse res = ServletActionContext.getResponse();
        HttpSession session = req.getSession();
//		Passport passport = (Passport) session
//				.getAttribute(Constants.USER_IN_SESSION);
//		if (passport == null) {
//			String url = req.getContextPath() + path;
//			res.setHeader("Refresh", "0; URL=" + url);
//			return null;
//		}
        return actionInvocation.invoke();
    }

    public void setPath(String path) {
        this.path = path;
    }
}