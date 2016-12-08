package com.lcc.taxi.task;

import java.util.Date;

/**
 * Created by lcc on 2016/12/7.
 */
public class TaskDetail {
    private Long id;
    private String taskrunid;
    private String taskid;
    private String cphm;
    private Date jrsj;
    private Date lksj;
    private String isydcl;
    private Date stime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public Date getJrsj() {
        return jrsj;
    }

    public void setJrsj(Date jrsj) {
        this.jrsj = jrsj;
    }

    public Date getLksj() {
        return lksj;
    }

    public void setLksj(Date lksj) {
        this.lksj = lksj;
    }

    public String getIsydcl() {
        return isydcl;
    }

    public void setIsydcl(String isydcl) {
        this.isydcl = isydcl;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public String getTaskrunid() {
        return taskrunid;
    }

    public void setTaskrunid(String taskrunid) {
        this.taskrunid = taskrunid;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }
}
