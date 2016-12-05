package com.lcc.framework.bean;

import java.io.Serializable;

/**
 * Created by lcc on 2016/12/6.
 * 周边车辆
 */
public class Ssgj implements Serializable {

    private static final long serialVersionUID = -8675942107873526373L;

    private String cph="";//车牌号
    private String sj="";//GPS时间
    private String sp="";//速度
    private Double jd=0d;//经度
    private Double wd=0d;//维度
    private String fx="";//方向
    private String bjbz="";//报警标志
    private String yxzt="";//运行状态
    private String state="1";//0:无数据1：正常2：超过6分钟不在线
    private Double dis=0d;//离中心点距离
    private String name="";//当班司机
    private String com="";//企业
    private String sfz="";//当班司机身份证
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
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Double getDis() {
        return dis;
    }
    public void setDis(Double dis) {
        this.dis = dis;
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
    public String getSfz() {
        return sfz;
    }
    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

}

