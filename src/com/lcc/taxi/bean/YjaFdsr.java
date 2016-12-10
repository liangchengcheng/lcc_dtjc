package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_WZJL_YJA_FDSR")
public class YjaFdsr extends EntityDao<YjaFdsr> {

    private static final long serialVersionUID = 1L;
    private String id;
    private String wzid;
    private String sfz;
    private String address;
    private String lxdh;
    private String cyzgz;
    private String fwzgz;
    private String cph;
    private String cx;
    private String fdj;
    private String cjh;
    private String comid;
    private String yyzj;
    private Date wzsj;
    private String wzdd;
    private String wftm;
    private String zfry;
    private String zfptajh;
    private String fkje;
    private String lrr;
    private Date lrsj;
    private String glaj;
    private String state;
    private String ssfs;
    private String kf;
    private String jasj;

    @Id
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @GeneratedValue(generator = "hibernate-uuid")
    public String getId() {
        return id;
    }

    public String getWzid() {
        return wzid;
    }

    public String getSfz() {
        return sfz;
    }

    public String getAddress() {
        return address;
    }

    public String getLxdh() {
        return lxdh;
    }

    public String getCyzgz() {
        return cyzgz;
    }

    public String getFwzgz() {
        return fwzgz;
    }

    public String getCph() {
        return cph;
    }

    public String getCx() {
        return cx;
    }

    public String getFdj() {
        return fdj;
    }

    public String getComid() {
        return comid;
    }

    public String getYyzj() {
        return yyzj;
    }

    public Date getWzsj() {
        return wzsj;
    }

    public String getWzdd() {
        return wzdd;
    }

    public String getWftm() {
        return wftm;
    }

    public String getZfry() {
        return zfry;
    }

    public String getZfptajh() {
        return zfptajh;
    }

    public String getFkje() {
        return fkje;
    }

    public String getLrr() {
        return lrr;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public String getGlaj() {
        return glaj;
    }

    public String getState() {
        return state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWzid(String wzid) {
        this.wzid = wzid;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public void setCyzgz(String cyzgz) {
        this.cyzgz = cyzgz;
    }

    public void setFwzgz(String fwzgz) {
        this.fwzgz = fwzgz;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public void setFdj(String fdj) {
        this.fdj = fdj;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }

    public void setYyzj(String yyzj) {
        this.yyzj = yyzj;
    }

    public void setWzsj(Date wzsj) {
        this.wzsj = wzsj;
    }

    public void setWzdd(String wzdd) {
        this.wzdd = wzdd;
    }

    public void setWftm(String wftm) {
        this.wftm = wftm;
    }

    public void setZfry(String zfry) {
        this.zfry = zfry;
    }

    public void setZfptajh(String zfptajh) {
        this.zfptajh = zfptajh;
    }

    public void setFkje(String fkje) {
        this.fkje = fkje;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public void setGlaj(String glaj) {
        this.glaj = glaj;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
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

    public String getJasj() {
        return jasj;
    }

    public void setJasj(String jasj) {
        this.jasj = jasj;
    }


}
