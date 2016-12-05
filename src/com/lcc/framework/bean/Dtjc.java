package com.lcc.framework.bean;

import java.io.Serializable;

/**
 * Created by lcc on 2016/12/6.
 */
public class Dtjc implements Serializable {

    /**
     * 周边车辆
     */
    private static final long serialVersionUID = -8675942107873526373L;

    private String cph;//车牌号
    private String sj;//GPS时间
    private String sp;//速度
    private Double jd;//经度
    private Double wd;//维度
    private String fx;//方向
    private String bjbz;//报警标志
    private String yxzt;//运行状态
    private String name;//当班司机
    private String com;//企业
    private String srsj;//驶入时间
    private String scsj;//驶出时间
    public String getCph() {
        return cph;
    }
    public void setCph(String cph) {
        this.cph = cph;
    }
    public String getSj() {
        return sj;
    }
    public void setSj(String sj) {
        this.sj = sj;
    }
    public String getSp() {
        return sp;
    }
    public void setSp(String sp) {
        this.sp = sp;
    }
    public Double getJd() {
        return jd;
    }
    public void setJd(Double jd) {
        this.jd = jd;
    }
    public Double getWd() {
        return wd;
    }
    public void setWd(Double wd) {
        this.wd = wd;
    }
    public String getFx() {
        return fx;
    }
    public void setFx(String fx) {
        this.fx = fx;
    }
    public String getBjbz() {
        return bjbz;
    }
    public void setBjbz(String bjbz) {
        this.bjbz = bjbz;
    }
    public String getYxzt() {
        return yxzt;
    }
    public void setYxzt(String yxzt) {
        this.yxzt = yxzt;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCom() {
        return com;
    }
    public void setCom(String com) {
        this.com = com;
    }
    public String getSrsj() {
        return srsj;
    }
    public void setSrsj(String srsj) {
        this.srsj = srsj;
    }
    public String getScsj() {
        return scsj;
    }
    public void setScsj(String scsj) {
        this.scsj = scsj;
    }

}

