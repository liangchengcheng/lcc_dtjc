package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_WZJL_YCX")
public class Wzjlycx extends EntityDao<Wzjlycx> {

    private static final long serialVersionUID = 1L;
    private String id;
    private String wzid;
    private String zfptspr;
    private String zfptajh;
    private String cxsm;
    private String lrr;
    private Date lrsj;
    private String state;

    @Id
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @GeneratedValue(generator = "hibernate-uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWzid() {
        return wzid;
    }

    public void setWzid(String wzid) {
        this.wzid = wzid;
    }

    public String getZfptspr() {
        return zfptspr;
    }

    public void setZfptspr(String zfptspr) {
        this.zfptspr = zfptspr;
    }

    public String getZfptajh() {
        return zfptajh;
    }

    public void setZfptajh(String zfptajh) {
        this.zfptajh = zfptajh;
    }

    public String getCxsm() {
        return cxsm;
    }

    public void setCxsm(String cxsm) {
        this.cxsm = cxsm;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}