package com.lcc.taxi.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lcc on 2016/12/11.
 */
public class TreeBean {
    private String id;
    private String pid;
    private String text;
    private List<TreeBean> children = new ArrayList<TreeBean>();
    private List<Map<String, Object>> vehicles;
    private String isleaf;
    private String iconCls;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TreeBean> getChildren() {
        return children;
    }

    public void setChildren(List<TreeBean> children) {
        this.children = children;
    }

    public String getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<Map<String, Object>> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Map<String, Object>> vehicles) {
        this.vehicles = vehicles;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
