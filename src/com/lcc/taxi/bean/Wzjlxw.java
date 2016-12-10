package com.lcc.taxi.bean;


import com.lcc.framework.dao.EntityDao;

public class Wzjlxw extends EntityDao<Wzjlxw> {
    private String ajh;
    private String wzxwid;
    private String wfcdid;
    private String state;

    public String getAjh() {
        return ajh;
    }

    public void setAjh(String ajh) {
        this.ajh = ajh;
    }

    public String getWzxwid() {
        return wzxwid;
    }

    public void setWzxwid(String wzxwid) {
        this.wzxwid = wzxwid;
    }

    public String getWfcdid() {
        return wfcdid;
    }

    public void setWfcdid(String wfcdid) {
        this.wfcdid = wfcdid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
