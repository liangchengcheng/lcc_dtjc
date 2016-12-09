package com.lcc.taxi.bean;

import com.lcc.framework.dao.EntityDao;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_BASE_EMP_FWBG")
public class TBaseEmpFwbg extends EntityDao<TBaseEmpFwbg> {

    private static final long serialVersionUID = 823632324177774505L;

    private Integer id;

    private Long idEmployee;

    private Long idOwner;

    private String idEcert;

    private String fwdwmc;

    private String fwdwdz;

    private Date fwkssj;

    private Date fwjssj;

    private String bgzt;

    private Integer fwpj;

    private String yxbz;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long id) {
        this.idEmployee = id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getIdEcert() {
        return idEcert;
    }

    public void setIdEcert(String idEcert) {
        this.idEcert = idEcert;
    }

    public String getFwdwmc() {
        return fwdwmc;
    }

    public void setFwdwmc(String fwdwmc) {
        this.fwdwmc = fwdwmc;
    }

    public String getFwdwdz() {
        return fwdwdz;
    }

    public void setFwdwdz(String fwdwdz) {
        this.fwdwdz = fwdwdz;
    }

    public Date getFwkssj() {
        return fwkssj;
    }

    public void setFwkssj(Date fwkssj) {
        this.fwkssj = fwkssj;
    }

    public Date getFwjssj() {
        return fwjssj;
    }

    public void setFwjssj(Date fwjssj) {
        this.fwjssj = fwjssj;
    }

    public String getBgzt() {
        return bgzt;
    }

    public void setBgzt(String bgzt) {
        this.bgzt = bgzt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYxbz() {
        return yxbz;
    }

    public void setYxbz(String yxbz) {
        this.yxbz = yxbz;
    }

    public Integer getFwpj() {
        return fwpj;
    }

    public void setFwpj(Integer fwpj) {
        this.fwpj = fwpj;
    }

}