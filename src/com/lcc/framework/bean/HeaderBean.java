package com.lcc.framework.bean;

import java.io.Serializable;

/**
 * Created by lcc on 2016/12/6.
 * 头部信息
 */
public class HeaderBean implements Serializable {

    private static final long serialVersionUID = -8440603529992927102L;
    private Double lon;
    private Double lat;
    private Double ts;//时间戳
    private String di;//唯一标识

    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }
    public Double getTs() {
        return ts;
    }
    public void setTs(Double ts) {
        this.ts = ts;
    }
    public String getDi() {
        return di;
    }
    public void setDi(String di) {
        this.di = di;
    }
    public Double getLon() {
        return lon;
    }
    public void setLon(Double lon) {
        this.lon = lon;
    }

}
