package com.lcc.taxi.bean;


import com.lcc.framework.dao.EntityDao;

public class Wfcd extends EntityDao<Wfcd> {

    private static final long serialVersionUID = 1L;
    private String wfxwid;
    private String wfcdid;
    private String cfbz;
    private String hg;
    private String state;

    public String getWfxwid() {
        return wfxwid;
    }

    public void setWfxwid(String wfxwid) {
        this.wfxwid = wfxwid;
    }

    public String getWfcdid() {
        return wfcdid;
    }

    public void setWfcdid(String wfcdid) {
        this.wfcdid = wfcdid;
    }

    public String getCfbz() {
        return cfbz;
    }

    public void setCfbz(String cfbz) {
        this.cfbz = cfbz;
    }

    public String getHg() {
        return hg;
    }

    public void setHg(String hg) {
        this.hg = hg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
