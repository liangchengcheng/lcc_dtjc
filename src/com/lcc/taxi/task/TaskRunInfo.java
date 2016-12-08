package com.lcc.taxi.task;

import java.util.Date;
/**
 * Created by lcc on 2016/12/7.
 */
public class TaskRunInfo {

    private Integer taskid;
    private Integer taskrunid;
    private String tasktype;
    private Date kssj;
    private Date jssj;
    private Integer operid;

    public TaskRunInfo() {}

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getTasktype() {
        return tasktype;
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public Date getKssj() {
        return kssj;
    }

    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }

    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    public Integer getTaskrunid() {
        return taskrunid;
    }

    public void setTaskrunid(Integer taskrunid) {
        this.taskrunid = taskrunid;
    }

    public Integer getOperid() {
        return operid;
    }

    public void setOperid(Integer operid) {
        this.operid = operid;
    }

}
