package com.lcc.taxi.bean;

/**
 * Created by lcc on 2016/12/8.
 */
public class Clxx {
    private String mtype;//厂牌型号
    private String chassisid;//车架号
    private String ccertid;//道路运输证号
    private String vehicleid;//车牌号
    private String shortname;//简称
    private String engineid;//发动机号

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        if(mtype==null){
            this.mtype="";
        }else{
            this.mtype = mtype;
        }
    }

    public String getChassisid() {
        return chassisid;
    }

    public void setChassisid(String chassisid) {
        if(chassisid==null){
            this.chassisid="";
        }else{
            this.chassisid = chassisid;
        }
    }

    public String getCcertid() {
        return ccertid;
    }

    public void setCcertid(String ccertid) {
        if(ccertid==null){
            this.ccertid="";
        }else{
            this.ccertid = ccertid;
        }
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        if(vehicleid==null){
            this.vehicleid="";
        }else{
            this.vehicleid = vehicleid;
        }
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        if(shortname==null){
            this.shortname ="";
        }else{
            this.shortname = shortname;
        }

    }

    public String getEngineid() {
        return engineid;
    }

    public void setEngineid(String engineid) {
        if(engineid==null){
            this.engineid ="";
        }else{
            this.engineid = engineid;
        }
    }

}
