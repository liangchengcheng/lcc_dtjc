package com.lcc.taxi.bean;

import com.lcc.framework.dao.EntityDao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_BASE_VEHICLE")
public class TBaseVehicle extends EntityDao<TBaseVehicle> {

    private static final long serialVersionUID = 722336815200653614L;

    private Long idVehicle;

    private Long idOwner;

    private String vehicleid;

    private String srcvehicleid;

    private String vclidcolor;

    private String mtype;

    private String vcltype;

    private Integer seat;

    private String bodycolor;

    private String engineid;

    private String chassisid;

    private String fueltype;

    private Date firstdate;

    private Date expiredate;

    private String ccertstate;

    private String ccertword;

    private String ccertid;

    private Date ibdate;

    private String mgrarea;

    private String pfbz;

    private Double fdjpl;

    private Date addtime;

    private String addoperator;

    private Date lasttime;

    private String operator;

    private String grantorgan;

    private Date outmilldate;

    private String ownername;

    private String ownerphone;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

    public String getVclidcolor() {
        return vclidcolor;
    }

    public void setVclidcolor(String vclidcolor) {
        this.vclidcolor = vclidcolor;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        this.mtype = mtype;
    }

    public String getVcltype() {
        return vcltype;
    }

    public void setVcltype(String vcltype) {
        this.vcltype = vcltype;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getBodycolor() {
        return bodycolor;
    }

    public void setBodycolor(String bodycolor) {
        this.bodycolor = bodycolor;
    }

    public String getEngineid() {
        return engineid;
    }

    public void setEngineid(String engineid) {
        this.engineid = engineid;
    }

    public String getChassisid() {
        return chassisid;
    }

    public void setChassisid(String chassisid) {
        this.chassisid = chassisid;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public Date getFirstdate() {
        return firstdate;
    }

    public void setFirstdate(Date firstdate) {
        this.firstdate = firstdate;
    }

    public String getCcertstate() {
        return ccertstate;
    }

    public void setCcertstate(String ccertstate) {
        this.ccertstate = ccertstate;
    }

    public String getCcertword() {
        return ccertword;
    }

    public void setCcertword(String ccertword) {
        this.ccertword = ccertword;
    }

    public String getCcertid() {
        return ccertid;
    }

    public void setCcertid(String ccertid) {
        this.ccertid = ccertid;
    }

    public Date getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(Date expiredate) {
        this.expiredate = expiredate;
    }

    public Date getIbdate() {
        return ibdate;
    }

    public void setIbdate(Date ibdate) {
        this.ibdate = ibdate;
    }

    public String getMgrarea() {
        return mgrarea;
    }

    public void setMgrarea(String mgrarea) {
        this.mgrarea = mgrarea;
    }

    public String getPfbz() {
        return pfbz;
    }

    public void setPfbz(String pfbz) {
        this.pfbz = pfbz;
    }

    public Double getFdjpl() {
        return fdjpl;
    }

    public void setFdjpl(Double fdjpl) {
        this.fdjpl = fdjpl;
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

    public String getGrantorgan() {
        return grantorgan;
    }

    public void setGrantorgan(String grantorgan) {
        this.grantorgan = grantorgan;
    }

    public Date getOutmilldate() {
        return outmilldate;
    }

    public void setOutmilldate(Date outmilldate) {
        this.outmilldate = outmilldate;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getOwnerphone() {
        return ownerphone;
    }

    public void setOwnerphone(String ownerphone) {
        this.ownerphone = ownerphone;
    }

    @Transient
    public String getSrcvehicleid() {
        return srcvehicleid;
    }

    public void setSrcvehicleid(String srcvehicleid) {
        this.srcvehicleid = srcvehicleid;
    }

}
