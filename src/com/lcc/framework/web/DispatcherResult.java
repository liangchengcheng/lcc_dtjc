package com.lcc.framework.web;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;
import org.apache.struts2.views.util.DefaultUrlHelper;
import org.apache.struts2.views.util.UrlHelper;

import javax.servlet.jsp.PageContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

/**
 * Created by lcc on 2016/12/2.
 */
public class DispatcherResult implements Result {
    private static final long serialVersionUID = -1970659272360685627L;

    @SuppressWarnings("unchecked")
    @Override
    public void execute(ActionInvocation invocation) throws Exception {
        PageContext pageContext = ServletActionContext.getPageContext();
        //获取参数
        String finalLocation = (String) ActionContext.getContext().get("location");
        if (pageContext != null) {
            //都是在页面之前就已经对页面进行编译处理
            pageContext.include(finalLocation);
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher(finalLocation);

            if (invocation != null && finalLocation != null
                    && finalLocation.length() > 0 && finalLocation.indexOf("?") > 0) {
                String queryString = finalLocation.substring(finalLocation.indexOf("?") + 1);
                Map parameters = (Map) invocation.getInvocationContext().getContextMap().get("parameters");
                UrlHelper urlHelper = new DefaultUrlHelper();

                Map queryParams = urlHelper.parseQueryString(queryString,true);
                if (queryParams != null && queryParams.isEmpty()){
                    parameters.putAll(queryParams);
                }
            }

            if (dispatcher == null){
                response.sendError(404,"result '" + finalLocation + "' not found");
                return;
            }

            if (!response.isCommitted() && (request.getAttribute("javax.servlet.include.servlet_path")== null)){
                request.setAttribute("structs.view_uri",finalLocation);
                request.setAttribute("structs.request_uri",request.getRequestURI());

                dispatcher.forward(request,response);
            } else {
              dispatcher.include(request,response);
            }
        }
    }
}
