package com.lcc.taxi.bean;

import com.lcc.framework.dao.EntityDao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_BASE_COM")
public class TBaseCom extends EntityDao<TBaseCom> implements Serializable{

    private static final long serialVersionUID = 5881533796663809519L;

    private Long idOwner;

    private String ownername;

    private String srcownername;

    private String shortname;

    private Long pid;

    private String parentname;

    private String compcode;

    private String compid;

    private String pmcertid;

    private String mcertword;

    private String mcertid;

    private String mgrarea;

    private String owneraddr;

    private String state;

    private String legalp;

    private String pttel;

    private String dealprincipal;

    private Date firstdate;

    private Date expiredate;

    private String checkgrantorgan;

    private Date checkgrantdate;

    private Date addtime;

    private String addoperator;

    private Date lasttime;

    private String operator;

    private String fax;

    private String econtype;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getCompcode() {
        return compcode;
    }

    public void setCompcode(String compcode) {
        this.compcode = compcode;
    }

    public String getCompid() {
        return compid;
    }

    public void setCompid(String compid) {
        this.compid = compid;
    }

    public String getPmcertid() {
        return pmcertid;
    }

    public void setPmcertid(String pmcertid) {
        this.pmcertid = pmcertid;
    }

    public String getMcertword() {
        return mcertword;
    }

    public void setMcertword(String mcertword) {
        this.mcertword = mcertword;
    }

    public String getMcertid() {
        return mcertid;
    }

    public void setMcertid(String mcertid) {
        this.mcertid = mcertid;
    }

    public String getMgrarea() {
        return mgrarea;
    }

    public void setMgrarea(String mgrarea) {
        this.mgrarea = mgrarea;
    }

    public String getOwneraddr() {
        return owneraddr;
    }

    public void setOwneraddr(String owneraddr) {
        this.owneraddr = owneraddr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLegalp() {
        return legalp;
    }

    public void setLegalp(String legalp) {
        this.legalp = legalp;
    }

    public String getPttel() {
        return pttel;
    }

    public void setPttel(String pttel) {
        this.pttel = pttel;
    }

    public String getDealprincipal() {
        return dealprincipal;
    }

    public void setDealprincipal(String dealprincipal) {
        this.dealprincipal = dealprincipal;
    }

    public Date getFirstdate() {
        return firstdate;
    }

    public void setFirstdate(Date firstdate) {
        this.firstdate = firstdate;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public String getCheckgrantorgan() {
        return checkgrantorgan;
    }

    public void setCheckgrantorgan(String checkgrantorgan) {
        this.checkgrantorgan = checkgrantorgan;
    }

    public Date getCheckgrantdate() {
        return checkgrantdate;
    }

    public void setCheckgrantdate(Date checkgrantdate) {
        this.checkgrantdate = checkgrantdate;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAddoperator() {
        return addoperator;
    }

    public void setAddoperator(String addoperator) {
        this.addoperator = addoperator;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEcontype() {
        return econtype;
    }

    public void setEcontype(String econtype) {
        this.econtype = econtype;
    }

    @Transient
    public String getSrcownername() {
        return srcownername;
    }

    public void setSrcownername(String srcownername) {
        this.srcownername = srcownername;
    }

}
