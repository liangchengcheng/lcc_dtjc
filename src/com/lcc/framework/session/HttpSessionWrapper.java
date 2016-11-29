package com.lcc.framework.session;

import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
/**
 * Created by lcc on 2016/11/29.
 */
public class HttpSessionWrapper implements HttpSession {

    private HashMap<String,Object> map = null;

    private String sessionID = "";

    private HttpSession session;

    public HttpSessionWrapper(String sid, HttpSession session) {
        this.session = session;
        this.sessionID = sid;
    }

    public Object getAttribute(String key) {
        return map.get(key);
    }

    public Enumeration getAttributeNames() {
        return session.getAttributeNames();
    }

    public long getCreationTime() {
        return session.getCreationTime();
    }

    public String getId() {
        return sessionID;
    }

    public long getLastAccessedTime() {
        return session.getLastAccessedTime();
    }

    public int getMaxInactiveInterval() {
        return session.getMaxInactiveInterval();
    }

    public ServletContext getServletContext() {
        return session.getServletContext();
    }

    public HttpSessionContext getSessionContext() {
        return session.getSessionContext();
    }

    public Object getValue(String key) {
        return map.get(key);
    }

    public String[] getValueNames() {
        return session.getValueNames();
    }

    public void invalidate() {
        this.map.clear();
    }

    public boolean isNew() {
        return session.isNew();
    }

    public void putValue(String key, Object value) {
        map.put(key, value);
    }

    public void removeAttribute(String key) {
        map.remove(key);
    }

    public void removeValue(String key) {
        map.remove(key);
    }

    public void setAttribute(String key, Object value) {
        map.put(key, value);
    }

    public void setMaxInactiveInterval(int arg0) {
        session.setMaxInactiveInterval(arg0);
    }

}

