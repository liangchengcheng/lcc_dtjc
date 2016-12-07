package com.lcc.taxi.task;

import java.util.Date;

/**
 * Created by lcc on 2016/12/7.
 */
public class SendTaskBean {

    /**
     * 用户ID
     */
    private Integer userid;

    /**
     * 任务ID
     */
    private Integer taskid;

    /**
     * 车牌号码
     */
    private String cphm;

    /**
     * 当班司机姓名
     */
    private String sjxm;

    /**
     * 司机身份证号
     */
    private String sfzh;

    /**
     * 空重车
     */
    private Integer kzc;

    /**
     * 进入时间
     */
    private Date jrsj;

    /**
     * 是否进入区域
     */
    private boolean isin;

    /**
     * 设备ID
     */
    private Integer devid;

    /**
     * 经度
     */
    private Double jd;

    /**
     * 纬度
     */
    private Double wd;

    public SendTaskBean() {}

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public String getSjxm() {
        return sjxm;
    }

    public void setSjxm(String sjxm) {
        this.sjxm = sjxm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public Integer getKzc() {
        return kzc;
    }

    public void setKzc(Integer kzc) {
        this.kzc = kzc;
    }

    public Date getJrsj() {
        return jrsj;
    }

    public void setJrsj(Date jrsj) {
        this.jrsj = jrsj;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public boolean isIsin() {
        return isin;
    }

    public void setIsin(boolean isin) {
        this.isin = isin;
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

    public Integer getDevid() {
        return devid;
    }

    public void setDevid(Integer devid) {
        this.devid = devid;
    }

}
