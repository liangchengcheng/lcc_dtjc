package com.lcc.framework.web;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lcc on 2016/12/1.
 */
public class StringResult implements Result{

    private static final long serialVersionUID = 2156796872012750183L;

    @Override
    public void execute(ActionInvocation actionInvocation) throws Exception {
        String contentType = "text/json;charset=UTF-8";
        String result = "nothing string render";
        Object resultObj = ActionContext.getContext().get("result");
        if (resultObj != null && resultObj instanceof String){
            result = resultObj.toString();
        }
        Object contentTypeObj = ActionContext.getContext().get("contentType");
        if(contentTypeObj != null && contentTypeObj instanceof String){
            contentType = contentTypeObj.toString();
        }

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType(contentType);
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        System.out.println(result);
        response.getWriter().write(result);
        response.getWriter().close();
    }
}
