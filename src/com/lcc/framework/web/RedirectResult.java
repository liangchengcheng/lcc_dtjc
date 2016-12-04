package com.lcc.framework.web;

import static javax.servlet.http.HttpServletResponse.SC_FOUND;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.apache.struts2.dispatcher.mapper.ActionMapping;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * Created by lcc on 2016/12/4.
 */
public class RedirectResult implements Result {

    private static final long serialVersionUID = 6316947346435301270L;

    private static final Logger LOG = LoggerFactory.getLogger(RedirectResult.class);

    protected boolean prependServletContext = true;

    protected ActionMapper actionMapper;

    private int statusCode = SC_FOUND;

    public void setActionMapper(ActionMapper actionMapper) {
        this.actionMapper = actionMapper;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setPrependServletContext(boolean prependServletContext) {
        this.prependServletContext = prependServletContext;
    }

    @Override
    public void execute(ActionInvocation actionInvocation) throws Exception {
        String finalLocation = (String) ActionContext.getContext().get("location");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        if (isPathUrl(finalLocation)){
            //判断url是否是/开头的
            if (!finalLocation.startsWith("/")){
                ActionMapping mapping = actionMapper.getMapping(request,Dispatcher.getInstance().getConfigurationManager());
                String namespace  = mapping.getNamespace();

                //获取命名空间
                if (mapping != null){
                    namespace  = mapping.getNamespace();
                }

                // 命令空间不是空的话 而且不包是斜杠的话则相加
                if ((namespace != null) && (namespace.length() > 0) && (!"/".equals(namespace))) {
                    finalLocation = namespace + "/" + finalLocation;
                } else {
                    finalLocation = "/" + finalLocation;
                }
            }

            // if the URL's are relative to the servlet context, append the servlet context path
            if (prependServletContext && (request.getContextPath() != null) && (request.getContextPath().length() > 0)) {
                finalLocation = request.getContextPath() + finalLocation;
            }

            finalLocation = response.encodeRedirectURL(finalLocation);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Redirecting to finalLocation " + finalLocation);
        }

        sendRedirect(response, finalLocation);
    }

    /**
     * 重定向
     */
    protected void sendRedirect(HttpServletResponse response,String finalLocation) throws  Exception{
        if (SC_FOUND == statusCode){
            response.sendRedirect(finalLocation);
        }else {
            response.setStatus(statusCode);
            response.setHeader("location",finalLocation);
            response.getWriter().write(finalLocation);
            response.getWriter().close();
        }
    }

    /**
     * 判断是否是url
     */
    private static boolean isPathUrl(String url){
        return (url.indexOf(":") == -1);
    }
}
