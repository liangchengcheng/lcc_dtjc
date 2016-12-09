package com.lcc.taxi.bean;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_FK_BASE_EMP_EXT")
public class TBaseEmpExt extends EntityDao<TBaseEmpExt> {

    private static final long serialVersionUID = 8229625284734360508L;

    private Long idEmployee;

    private String proxzcl;

    private String shjg;

    private String shzt;

    private Integer shryzh;

    private String shrymc;

    private Date shsj;

    private String sfyfk;

    private Date fksj;

    private String sfdbjsy;

    private String kpsfjh;

    private String fwzglb;

    private String fwzgzh;

    private Date fwzgzyxq;

    private String yktsjdm;

    private String khyh;

    private String khzh;

    private String zfbzh;

    private Date pxjssj;

    private Date kpjhsj;

    private String ickh;

    private String kplx;

    private String pxxxid;

    private Date zhscsj;

    @Id
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    @GeneratedValue(generator = "paymentableGenerator")
    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long id) {
        this.idEmployee = id;
    }

    public String getProxzcl() {
        return proxzcl;
    }

    public void setProxzcl(String proxzcl) {
        this.proxzcl = proxzcl;
    }

    public String getShjg() {
        return shjg;
    }

    public void setShjg(String shjg) {
        this.shjg = shjg;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public Integer getShryzh() {
        return shryzh;
    }

    public void setShryzh(Integer shryzh) {
        this.shryzh = shryzh;
    }

    public String getShrymc() {
        return shrymc;
    }

    public void setShrymc(String shrymc) {
        this.shrymc = shrymc;
    }

    public Date getShsj() {
        return shsj;
    }

    public void setShsj(Date shsj) {
        this.shsj = shsj;
    }

    public String getSfyfk() {
        return sfyfk;
    }

    public void setSfyfk(String sfyfk) {
        this.sfyfk = sfyfk;
    }

    public Date getFksj() {
        return fksj;
    }

    public void setFksj(Date fksj) {
        this.fksj = fksj;
    }

    public String getSfdbjsy() {
        return sfdbjsy;
    }

    public void setSfdbjsy(String sfdbjsy) {
        this.sfdbjsy = sfdbjsy;
    }

    public String getKpsfjh() {
        return kpsfjh;
    }

    public void setKpsfjh(String kpsfjh) {
        this.kpsfjh = kpsfjh;
    }

    public String getFwzglb() {
        return fwzglb;
    }

    public void setFwzglb(String fwzglb) {
        this.fwzglb = fwzglb;
    }

    public String getFwzgzh() {
        return fwzgzh;
    }

    public void setFwzgzh(String fwzgzh) {
        this.fwzgzh = fwzgzh;
    }

    public Date getFwzgzyxq() {
        return fwzgzyxq;
    }

    public void setFwzgzyxq(Date fwzgzyxq) {
        this.fwzgzyxq = fwzgzyxq;
    }

    public String getYktsjdm() {
        return yktsjdm;
    }

    public void setYktsjdm(String yktsjdm) {
        this.yktsjdm = yktsjdm;
    }

    public String getKhyh() {
        return khyh;
    }

    public void setKhyh(String khyh) {
        this.khyh = khyh;
    }

    public String getKhzh() {
        return khzh;
    }

    public void setKhzh(String khzh) {
        this.khzh = khzh;
    }

    public String getZfbzh() {
        return zfbzh;
    }

    public void setZfbzh(String zfbzh) {
        this.zfbzh = zfbzh;
    }

    public Date getPxjssj() {
        return pxjssj;
    }

    public void setPxjssj(Date pxjssj) {
        this.pxjssj = pxjssj;
    }

    public Date getKpjhsj() {
        return kpjhsj;
    }

    public void setKpjhsj(Date kpjhsj) {
        this.kpjhsj = kpjhsj;
    }

    public String getIckh() {
        return ickh;
    }

    public void setIckh(String ickh) {
        this.ickh = ickh;
    }

    public String getPxxxid() {
        return pxxxid;
    }

    public void setPxxxid(String pxxxid) {
        this.pxxxid = pxxxid;
    }

    public Date getZhscsj() {
        return zhscsj;
    }

    public void setZhscsj(Date zhscsj) {
        this.zhscsj = zhscsj;
    }

    public String getKplx() {
        return kplx;
    }

    public void setKplx(String kplx) {
        this.kplx = kplx;
    }

}
