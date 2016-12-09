package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_EMP_HMD")
public class Sjhmd extends EntityDao<Sjhmd> {
    private String id;
    private String sjxm;
    private String sfzh;
    private String jryy;
    private Date jrsj;
    private String jrr;
    private String qxr;
    private Date qxsj;
    private String qxsm;
    private String state;

    private String xgr;
    private Date xgsj;
    private Date lasttime;

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    public String getId() {
        return id;
    }

    public String getSjxm() {
        return sjxm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public String getJryy() {
        return jryy;
    }

    public Date getJrsj() {
        return jrsj;
    }

    public String getJrr() {
        return jrr;
    }

    public String getQxr() {
        return qxr;
    }

    public Date getQxsj() {
        return qxsj;
    }

    public String getQxsm() {
        return qxsm;
    }

    public String getState() {
        return state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSjxm(String sjxm) {
        this.sjxm = sjxm;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public void setJryy(String jryy) {
        this.jryy = jryy;
    }

    public void setJrsj(Date jrsj) {
        this.jrsj = jrsj;
    }

    public void setJrr(String jrr) {
        this.jrr = jrr;
    }

    public void setQxr(String qxr) {
        this.qxr = qxr;
    }

    public void setQxsj(Date qxsj) {
        this.qxsj = qxsj;
    }

    public void setQxsm(String qxsm) {
        this.qxsm = qxsm;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getXgr() {
        return xgr;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setXgr(String xgr) {
        this.xgr = xgr;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

}
