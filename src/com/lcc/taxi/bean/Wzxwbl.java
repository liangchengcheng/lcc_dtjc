package com.lcc.taxi.bean;

import com.lcc.framework.dao.EntityDao;

/**
 * Created by lcc on 2016/12/10.
 */

public class Wzxwbl extends EntityDao<Wzxwbl> {

    private static final long serialVersionUID = 1L;
    private String wfxwid;
    private String wtnr;
    private String type;
    private String state;

    public String getWfxwid() {
        return wfxwid;
    }

    public void setWfxwid(String wfxwid) {
        this.wfxwid = wfxwid;
    }

    public String getWtnr() {
        return wtnr;
    }

    public void setWtnr(String wtnr) {
        this.wtnr = wtnr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}

