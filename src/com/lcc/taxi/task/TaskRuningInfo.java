package com.lcc.taxi.task;

/**
 * Created by lcc on 2016/12/7.
 */
public class TaskRuningInfo {
    private TaskRunInfo taskruninfo;
    private String revInfoType;

    public TaskRuningInfo() {}

    public TaskRunInfo getTaskruninfo() {
        return taskruninfo;
    }

    public void setTaskruninfo(TaskRunInfo taskruninfo) {
        this.taskruninfo = taskruninfo;
    }

    public String getRevInfoType() {
        return revInfoType;
    }

    public void setRevInfoType(String revInfoType) {
        this.revInfoType = revInfoType;
    }
}
