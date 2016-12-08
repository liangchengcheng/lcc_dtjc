package com.lcc.taxi.task;

/**
 * Created by lcc on 2016/12/7.
 */
public class UserTaskRunBean {
    /**
     * 接收信息类型（1：进入区域车辆 2：进入区域异动车辆）
     */
    private String revInfoType;

    /**
     * 用户信息
     */
    private Users userinfo;

    /**
     * taskrunbean
     */
    private TaskRunBean taskRunBean;

    public UserTaskRunBean() {}

    public String getRevInfoType() {
        return revInfoType;
    }

    public void setRevInfoType(String revInfoType) {
        this.revInfoType = revInfoType;
    }

    public TaskRunBean getTaskRunBean() {
        return taskRunBean;
    }

    public void setTaskRunBean(TaskRunBean taskRunBean) {
        this.taskRunBean = taskRunBean;
    }

    public Users getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Users userinfo) {
        this.userinfo = userinfo;
    }

}
