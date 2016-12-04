package com.lcc.framework.web;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.framework.dao.EntityDao;
import com.lcc.framework.freeMarker.FreeMarkerEngine;
import com.lcc.framework.session.HttpSessionWrapper;
import com.lcc.framework.util.DataTypeUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.mapper.ActionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

/**
 * Created by lcc on 2016/12/4.
 */
public class BaseActionSupport extends ActionSupport {

    private static final long serialVersionUID = 5208748057591198755L;

    protected ActionMapper actionMapper;

    @Inject
    public void setActionMapper(ActionMapper mapper) {
        this.actionMapper = mapper;
    }

    @Autowired
    private FreeMarkerEngine freeMarkerEngine;

    /**
     * 获取servletContext
     */
    protected ServletContext getServletContext() {
        return ServletActionContext.getServletContext();
    }

    /**
     * 获取request
     */
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * 获取response
     */
    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    /**
     * 获取session
     */
    protected HttpSession getSession() {
        HttpSession session = getRequest().getSession();
        if (SystemConstants.SESSION_CLUSTERID) {
            return new HttpSessionWrapper(session.getId(), session);
        } else {
            return session;
        }
    }

    /**
     * 将对象存入ActionContext中，供页面显示
     *
     * @param key   页面调用的值 ${key}
     * @param value 具体显示的信息
     */
    protected void putObjToContext(String key, Object value) {
        ActionContext.getContext().put(key, value);
    }

    protected Object getObjFromContext(String key) {
        return ActionContext.getContext().get(key);
    }

    /**
     * 将action中的消息显示到页面
     *
     * @param value 要显示的字符串
     */
    @SuppressWarnings("unchecked")
    protected void displayMsg(String value) {
        HashMap<String, String> map = (HashMap<String, String>) getObjFromContext("messageMap");
        if (map == null || map.keySet().size() == 0) {
            map = new HashMap<String, String>();
        }
        map.put("message", value);
        putObjToContext("messageMap", map);
    }

    /**
     * 将信息存入session中，当redirect后，供页面显示
     *
     * @param key   页面调用的值 ${key}
     * @param value 具体显示的文字信息
     */
    protected void putObjToSession(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    protected Object getObjFromSession(String key) {
        HttpSession session = getSession();
        if (key == null || session == null) {
            return null;
        } else {
            return session.getAttribute(key);
        }
    }

    /**
     * 从request中取得数据
     *
     * @param key 页面传递的参数名
     * @return 参数值，如果传递的为null返回空字符串
     */
    protected String getParameterFromRequest(String key) {
        String parameter = getRequest().getParameter(key);
        if (parameter == null) {
            parameter = "";
        }
        return parameter;
    }

    /**
     * 输出xml到页面中
     *
     * @param root xml的根节点
     * @param root data xml数据
     */
    protected String renderXml(String filename, String root, String data) {
        if (filename != null && !filename.equalsIgnoreCase("")) {
            putObjToContext("filename", filename);
        }
        if (root != null && !root.equalsIgnoreCase("")) {
            putObjToContext("xmlroot", root);
        }
        if (data != null && !data.equalsIgnoreCase("")) {
            putObjToContext("result", data);
        }
        return "renderXml";
    }

    /**
     * 通过ftl模板，返回html代码
     *
     * @param data     ftl中所需要的数据
     * @param fileName ftl的文件名
     */
    protected String renderHtml(Map<String, Object> data, String fileName) {
        if (fileName != null && !fileName.equalsIgnoreCase(""))
            putObjToContext("result", freeMarkerEngine.getHtml(data,
                    getServletContext(), fileName));
        return "renderString";
    }

    /**
     * 输出字符串到页面中
     *
     * @param text 要输出的字符串
     */
    protected String renderText(String text) {
        if (text != null && !text.equalsIgnoreCase("")) {
            putObjToContext("result", text);
        }
        return "renderString";
    }

    /**
     * 将action中得到的数据以json格式进行输出，如果对象不存在则输出空字符串
     *
     * @param obj 要转换成json的对象
     */
    protected String renderJson(Object obj) {
        if (obj != null) {
            String json = "";
            if (isArray(obj)) {
                JSONArray jsonArray = JSONArray.fromObject(obj);
                json = jsonArray.toString();
            } else {
                JSONObject jsonObject = JSONObject.fromObject(obj);
                json = jsonObject.toString();
            }
            putObjToContext("result", json);
        }
        return "renderString";
    }

    protected String renderJson(Object obj, boolean includeConfig) {
        return renderJson(obj, includeConfig, null);
    }

    protected String renderJson(Object obj, boolean includeConfig,
                                String[] excludes_ary) {
        JsonConfig config = new JsonConfig();
        if (includeConfig) {
            config.registerJsonValueProcessor(java.util.Date.class,
                    new DateJsonValueProcessor());
            if (excludes_ary != null) {
                config.setExcludes(excludes_ary);
            }
        }
        if (obj != null) {
            String json = "";
            if (isArray(obj)) {
                JSONArray jsonArray = null;
                if (includeConfig) {
                    jsonArray = JSONArray.fromObject(obj, config);
                } else {
                    jsonArray = JSONArray.fromObject(obj);
                }
                json = jsonArray.toString();
            } else {
                JSONObject jsonObject = null;
                if (includeConfig) {
                    jsonObject = JSONObject.fromObject(obj, config);
                } else {
                    jsonObject = JSONObject.fromObject(obj);
                }
                json = jsonObject.toString();
            }
            // System.out.println("json:"+json);
            putObjToContext("result", json);
        }
        return "renderString";
    }

    /**
     * 将action中得到的数据以json格式进行输出，如果对象不存在则输出空字符串(主要是针对有关联的模型转换)
     *
     * @param obj 要转换成json的对象
     */
    protected String renderJsonRefer(Object obj, final String referObj) {
        JsonConfig config = new JsonConfig();
        config.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (name.equals(referObj)) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        if (obj != null) {
            String json = "";
            if (isArray(obj)) {
                JSONArray jsonArray = JSONArray.fromObject(obj);
                json = jsonArray.toString();
            } else {
                JSONObject jsonObject = JSONObject.fromObject(obj, config);
                json = jsonObject.toString();
            }
            putObjToContext("result", json);
        }
        return "renderString";
    }

    /**
     * 将接收到的json格式的字符串转换成指定的对象
     *
     * @param json   json格式的字符串
     * @param target 目标对象
     * @throws IOException
     */
    public void getObjFromJson(String json, Object target) throws IOException {
        if (target != null && json.length() > 0 && json.startsWith("[")
                && json.endsWith("]")) {
            JSONArray jsonArray = JSONArray.fromObject(json);
            if (isArray(target)) {
                JSONArray.toArray(jsonArray, target, new JsonConfig());
            } else {
                JSONArray.toList(jsonArray, target, new JsonConfig());
            }
        } else {
            JSONObject jsonObject = JSONObject.fromObject(json);
            JSONObject.toBean(jsonObject, target, new JsonConfig());
        }
    }

    /**
     * 判断对象是否是集合类型
     *
     * @param obj
     * @return
     */
    private boolean isArray(Object obj) {
        return obj instanceof Collection || obj.getClass().isArray();
    }

    protected String redirect(String file) {
        String location = "/";
        if (file != null && !file.equalsIgnoreCase("")) {
            location = file;
        }
        putObjToContext("location", location);
        return "jspRedirect";
    }

    protected String dispatcher(String file) {
        String location = "/";
        if (file != null && !file.equalsIgnoreCase("")) {
            location = file;
        }
        putObjToContext("location", location);
        return "jspDiapatcher";
    }

    protected String chain(String file) {
        String location = "/";
        if (file != null && !file.equalsIgnoreCase("")) {
            location = file;
        }
        putObjToContext("location", location);
        return "actionChain";
    }

    @SuppressWarnings("unchecked")
    protected boolean pojoValidate(EntityDao obj) {
        if (obj == null) {
            return false;
        }
        return handleMsg(obj.validate());
    }

    @SuppressWarnings("unchecked")
    protected boolean pojoValidate(EntityDao obj, String filed) {
        if (obj == null) {
            return false;
        }
        return handleMsg(obj.validate(filed));
    }

    @SuppressWarnings("unchecked")
    private boolean handleMsg(Map<String, String> map) {
        ActionContext ctx = ActionContext.getContext();
        HashMap<String, String> msg = (HashMap<String, String>) ctx
                .get("messageMap");
        if (msg == null || msg.keySet().size() == 0) {
            msg = (HashMap<String, String>) map;
        } else {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                msg.put(key, map.get(key));
            }
        }
        ctx.put("messageMap", msg);
        return map.keySet().size() > 0;
    }

    /**
     * 根据map中的数据来拼接查询的链接
     *
     * @param actionName action的名称
     * @param map        链接中的数据和参数名
     */
    public void pageLinkToContext(String actionName, Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        sb.append(actionName).append("?");
        sb.append(DataTypeUtil.buildSearchLink(map));
        putObjToContext("pageLink", sb.toString());
    }

    /**
     * JSON字符串转String数组
     *
     * @param s JSON字符串
     * @return String[]
     */
    protected String[] jsonToStrArray(String s) {
        JSONArray jsonArray = JSONArray.fromObject(s);
        Object[] StrArray = jsonArray.toArray();
        String[] slabels = new String[StrArray.length];
        for (int i = 0; i < StrArray.length; i++) {
            slabels[i] = StrArray[i].toString();
        }
        return slabels;
    }

    /**
     * JSON字符串转一维double数组
     *
     * @param s JSON字符串
     * @return double[]
     */
    protected double[] jsonToDoubleArray(String s) {
        JSONArray jsonArray = JSONArray.fromObject(s);
        Object[] StrArray = jsonArray.toArray();
        double[] sdata = new double[StrArray.length];
        for (int i = 0; i < StrArray.length; i++) {
            sdata[i] = Double.parseDouble(StrArray[i].toString());
        }
        return sdata;
    }

    /**
     * JSON字符串转Map集合
     *
     * @param s JSON字符串
     * @return Map<String,String>
     */
    protected Map<String, String> jsonToMap(String s) {
        JSONObject jsonObject = JSONObject.fromObject(s);
        Map<String, String> maps = new HashMap<String, String>();
        for (Iterator iter = jsonObject.keys(); iter.hasNext(); ) {
            String key = iter.next().toString();
            maps.put(key, jsonObject.get(key).toString());
        }
        return maps;
    }

    /**
     * ajax提交判断session是否失效
     */
    public boolean validateSession() {
        Integer userID = (Integer) getSession().getAttribute(SystemConstants.USER_SESSION_KEY);
        boolean validate = false;
        // 如果session失效了，返回true供action层去判断，然后action层返回json串给客户端
        if (userID == null) {
            validate = true;
        }
        return validate;
    }

    // 获取session中的passport对象
//	protected Oper getPassportBySession() {
//		Oper userSession = (Oper) ActionContext.getContext().getSession().get(
//				SystemConstants.USER_SESSION_KEY);
//		return userSession;
//	}
//
//
//	// 获取session中的passport对象
//	protected TBaseCom getComBySession() {
//		TBaseCom com = (TBaseCom) ActionContext.getContext().getSession().get(
//				SystemConstants.USER_SESSION_COM_KEY);
//		return com;
//	}


}
