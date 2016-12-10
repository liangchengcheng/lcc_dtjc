package com.lcc.taxi.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_WZJL_YJA_DSR")
public class YjaDsr extends EntityDao<YjaDsr> {

    private static final long serialVersionUID = 1L;
    private String id;
    private String wzid;
    private String zfptajh;
    private String fkje;
    private String lrr;
    private Date lrsj;
    private String state;
    private String ssfs;
    private String kf;

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

    public String getZfptajh() {
        return zfptajh;
    }

    public void setZfptajh(String zfptajh) {
        this.zfptajh = zfptajh;
    }

    public String getFkje() {
        return fkje;
    }

    public void setFkje(String fkje) {
        this.fkje = fkje;
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

    public String getSsfs() {
        return ssfs;
    }

    public String getKf() {
        return kf;
    }

    public void setSsfs(String ssfs) {
        this.ssfs = ssfs;
    }

    public void setKf(String kf) {
        this.kf = kf;
    }


}
