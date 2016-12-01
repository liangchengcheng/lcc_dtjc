package com.lcc.framework.web;

import com.lcc.framework.exception.ServiceException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.HashMap;

/**
 * Created by lcc on 2016/12/1.
 */
public class ServiceExceptionInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        String ret = "error";
        try{
            ret = ai.invoke();
        }catch(ServiceException ex){
            ActionContext ctx = ActionContext.getContext();
            HashMap<String, String> map = (HashMap<String, String>) ctx.get("messageMap");
            if(map == null || map.keySet().size() == 0){
                map = new HashMap<String, String>();
            }
            map.put("message", ex.getErrorCode());
            ctx.put("messageMap", map);
        }
        return ret;
    }
}
