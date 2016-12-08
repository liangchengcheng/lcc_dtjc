package com.lcc.taxi.task;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
/**
 * Created by lcc on 2016/12/7.
 */
public class TaskInfo implements Serializable {

    private static final long serialVersionUID = -4709432172202447750L;

    private int taskid;
    private String taskname;
    private Date ctime;
    private String isxfmb ="";
    private String mblx = "";
    private String mbwznr;
    private byte[] fwtp;
    private byte[] fwtpxfwt;
    private String fwqysz;
    private String curstate="0";
    private String yxbz;
    private Date zhkssj;
    private Date zhjssj;
    private Date deltime;
    private Integer cjoperid;
    private Integer operid;

    public TaskInfo(){}

    public byte[] getFwtpxfwt() {
        return fwtpxfwt;
    }

    public void setFwtpxfwt(byte[] fwtpxfwt) {
        this.fwtpxfwt = fwtpxfwt;
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getIsxfmb() {
        return isxfmb;
    }

    public void setIsxfmb(String isxfmb) {
        this.isxfmb = isxfmb;
    }

    public String getMblx() {
        return mblx;
    }

    public void setMblx(String mblx) {
        this.mblx = mblx;
    }

    public String getMbwznr() {
        return mbwznr;
    }

    public void setMbwznr(String mbwznr) {
        this.mbwznr = mbwznr;
    }

    public byte[] getFwtp() {
        return fwtp;
    }

    public void setFwtp(byte[] fwtp) {
        this.fwtp = fwtp;
    }

    public String getFwqysz() {
        return fwqysz;
    }

    public void setFwqysz(String fwqysz) {
        this.fwqysz = fwqysz;
    }

    public String getCurstate() {
        return curstate;
    }

    public void setCurstate(String curstate) {
        this.curstate = curstate;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public Date getZhkssj() {
        return zhkssj;
    }

    public void setZhkssj(Date zhkssj) {
        this.zhkssj = zhkssj;
    }

    public Date getZhjssj() {
        return zhjssj;
    }

    public void setZhjssj(Date zhjssj) {
        this.zhjssj = zhjssj;
    }

    public Date getDeltime() {
        return deltime;
    }

    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    public Integer getCjoperid() {
        return cjoperid;
    }

    public void setCjoperid(Integer cjoperid) {
        this.cjoperid = cjoperid;
    }

    public Integer getOperid() {
        return operid;
    }

    public void setOperid(Integer operid) {
        this.operid = operid;
    }

    @Override
    public String toString() {
        return "TaskInfo [taskid=" + taskid + ", taskname=" + taskname
                + ", ctime=" + ctime + ", isxfmb=" + isxfmb + ", mblx=" + mblx
                + ", mbwznr=" + mbwznr + ", fwtp=" + Arrays.toString(fwtp)
                + ", fwqysz=" + fwqysz + ", curstate=" + curstate + ", yxbz="
                + yxbz + ", zhkssj=" + zhkssj + ", zhjssj=" + zhjssj
                + ", deltime=" + deltime + ", cjoperid=" + cjoperid
                + ", operid=" + operid + "]";
    }

}
