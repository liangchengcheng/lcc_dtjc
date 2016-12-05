package com.lcc.framework.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lcc on 2016/12/6.
 */
public class UserLogin implements Serializable {

    private static final long serialVersionUID = -6319741763576430975L;
    private String id;
    private String un;// 用户姓名
    private String ui;// 证件号码
    private String dc;// 部门编号
    private String dd;// 部门
    private String uf;// TOKEN
    private String url;// 头像地址
    private String ml;//手机号
    private Map pz;//登录后返回一些常亮配置
    public String getUn() {
        return un;
    }
    public void setUn(String un) {
        this.un = un;
    }
    public String getUi() {
        return ui;
    }
    public void setUi(String ui) {
        this.ui = ui;
    }
    public String getDc() {
        return dc;
    }
    public void setDc(String dc) {
        this.dc = dc;
    }
    public String getDd() {
        return dd;
    }
    public void setDd(String dd) {
        this.dd = dd;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMl() {
        return ml;
    }
    public void setMl(String ml) {
        this.ml = ml;
    }
    public Map getPz() {
        return pz;
    }
    public void setPz(Map pz) {
        this.pz = pz;
    }

}

