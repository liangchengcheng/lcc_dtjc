package com.lcc.taxi.bean;

import java.util.List;
import java.util.Map;

/**
 * Created by lcc on 2016/12/8.
 */
public class Clda {
//	private Integer id_employee;//人员id
//	private Integer id_owner;//业户id
//	private Integer id_vehicle;//车辆id
//	private String employeecode;//司机代码
//	private String ptsex;//性别
//	private String ptidcard;//身份证号码
//	private String tel;//联系电话
//	private String id_ecert;//从业资格证号
//	private String driverlcense;//驾驶证号

    private List<Map<String, Object>> driver;
    private Integer id_vehicle;//车牌号
    private Integer id_owner;//企业id
    private String mtype;//厂牌型号
    private String chassisid;//车架号
    private String fueltype;//燃料类型
    private String ccertid;//道路运输证号
    private String firstdate;//有效期起
    private String vehicleid;//车牌号
    //车身型号（暂无）
    //附加费年度审验状态（暂无）
    private String ownername;//企业名称
    private String shortname;//简称
    private String engineid;//发动机号
    private String jryy;
    private String jrsj;
    private Map dr;

    public Integer getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(Integer id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

    public List<Map<String, Object>> getDriver() {
        return driver;
    }

    public void setDriver(List<Map<String, Object>> driver) {
        this.driver = driver;
    }

    public Integer getId_owner() {
        return id_owner;
    }

    public void setId_owner(Integer id_owner) {
        this.id_owner = id_owner;
    }

    public String getMtype() {
        return mtype;
    }

    public void setMtype(String mtype) {
        if (mtype == null) {
            this.mtype = "";
        } else {
            this.mtype = mtype;
        }
    }

    public String getChassisid() {
        return chassisid;
    }

    public void setChassisid(String chassisid) {
        if (chassisid == null) {
            this.chassisid = "";
        } else {
            this.chassisid = chassisid;
        }
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        if (fueltype == null) {
            this.fueltype = "";
        } else {
            this.fueltype = fueltype;
        }
    }

    public String getCcertid() {
        return ccertid;
    }

    public void setCcertid(String ccertid) {
        if (ccertid == null) {
            this.ccertid = "";
        } else {
            this.ccertid = ccertid;
        }
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public String getFirstdate() {
        return firstdate;
    }

    public void setFirstdate(String firstdate) {
        if (firstdate == null) {
            this.firstdate = "";
        } else {
            this.firstdate = firstdate;
        }
    }

    public void setVehicleid(String vehicleid) {
        if (vehicleid == null) {
            this.vehicleid = "";
        } else {
            this.vehicleid = vehicleid;
        }
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

    public Map getDr() {
        return dr;
    }

    public void setDr(Map dr) {
        this.dr = dr;
    }

    public String getEngineid() {
        return engineid;
    }

    public void setEngineid(String engineid) {

        if (engineid == null) {
            this.engineid = "";
        } else {
            this.engineid = engineid;
        }
    }

    public String getJryy() {
        return jryy;
    }

    public void setJryy(String jryy) {
        if (jryy == null) {
            this.jryy = "";
        } else {
            this.jryy = jryy;
        }
    }

    public String getJrsj() {
        return jrsj;
    }

    public void setJrsj(String jrsj) {
        if (jrsj == null) {
            this.jrsj = "";
        } else {
            this.jrsj = jrsj;
        }
    }
}
