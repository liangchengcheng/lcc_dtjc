package com.lcc.framework.bean;

import java.io.Serializable;

/**
 * Created by lcc on 2016/12/6.
 */

public class Dbsj implements Serializable {

    private static final long serialVersionUID = -4935965890244014062L;

    private String name;
    private String ptidcard;
    private String signtime;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPtidcard() {
        return ptidcard;
    }
    public void setPtidcard(String ptidcard) {
        this.ptidcard = ptidcard;
    }
    public String getSigntime() {
        return signtime;
    }
    public void setSigntime(String signtime) {
        this.signtime = signtime;
    }


}
