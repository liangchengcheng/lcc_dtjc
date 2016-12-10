package com.lcc.taxi.bean;

import com.lcc.framework.dao.EntityDao;

public class Wtmb extends EntityDao<Wtmb> {

    private static final long serialVersionUID = 1L;
    private String mbid;
    private String wtid;
    private String state;
    private Integer sx;

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getWtid() {
        return wtid;
    }

    public void setWtid(String wtid) {
        this.wtid = wtid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getSx() {
        return sx;
    }

    public void setSx(Integer sx) {
        this.sx = sx;
    }


}