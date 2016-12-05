package com.lcc.framework.util;

import com.lcc.framework.bean.HeaderBean;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lcc on 2016/12/6.
 * 获取头部基本信息的工具类
 */
public class HeaderUtil {
    private HttpServletRequest request;

    public HeaderUtil(HttpServletRequest req){
        this.request = req;
    }

    public HeaderBean  getHeader(){
        HeaderBean bean = new HeaderBean();

        bean.setDi(request.getHeader("id"));
        bean.setLat(request.getHeader("lat")!=null?Double.valueOf(request.getHeader("lat")):0);
        bean.setLon(request.getHeader("lon")!=null?Double.valueOf(request.getHeader("lon")):0);
        bean.setTs(request.getHeader("ts")!=null?Double.valueOf(request.getHeader("ts")):0);
        return  bean;
    }

    public HttpServletRequest getRequest(){
        return  request;
    }

    public void setRequest(HttpServletRequest request){
        this.request = request;
    }
}
