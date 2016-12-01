package com.lcc.framework.web;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

/**
 * Created by lcc on 2016/12/1.
 */
public class XmlResult implements Result {

    private static final long serialVersionUID = 533673269855599709L;

    public void execute(ActionInvocation invocation) throws Exception {
        StringBuffer xmlHead = new StringBuffer(
                "<?xml version='1.0' encoding='UTF-8' ?>");
        String result = ""; // 要提供的数据
        String end = ""; // 根结点关闭的字符串
        Object filename = ActionContext.getContext().get("filename");	//文件名称
        Object xmlroot = ActionContext.getContext().get("xmlroot");
        Object resultObj = ActionContext.getContext().get("result");
        if (xmlroot != null && xmlroot instanceof String) {
            String xmlStr = xmlroot.toString();
            xmlroot = xmlHead.toString() + xmlStr;
            int i = xmlStr.indexOf("<");
            if (i != -1) {
                end = xmlStr.substring(0, i+1) + "/"
                        + xmlStr.substring(i+1, xmlStr.length());
            }
        }
        if (resultObj != null && resultObj instanceof String) {
            result = resultObj.toString();
        }
        HttpServletResponse response = ServletActionContext.getResponse();
//		response.setContentType(contentType);
//		response.setHeader("Pragma", "No-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires", 0);
//		response.getWriter().write(xmlroot + result.toString() + end);
//		response.getWriter().close();
        if(xmlroot!=null){
            xmlroot = xmlroot + "\n";
        }else{
            xmlroot = "";
        }
        InputStream xlsStream = new ByteArrayInputStream((xmlroot + result.toString() + end).getBytes());
        int i = xlsStream.available();
        response.reset();
        response.setContentType("application/voicexml+xml");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("Accept-Length", (new Integer(i)).toString());
        response.setHeader("Content-disposition", "attachment;  filename="
                + new String(filename.toString().getBytes("GB2312"), "8859_1") + ".xml");
        byte[] bytes = new byte[4096];
        int length = -1;
        while ((length = xlsStream.read(bytes)) != -1) {
            response.getOutputStream().write(bytes, 0, length);
            response.flushBuffer();
        }
        xlsStream.close();
    }
}

