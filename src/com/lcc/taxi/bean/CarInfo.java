package com.lcc.taxi.bean;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class CarInfo {
    // 是否在线，0不在1在
    private byte online;
    // 是否营运，0停运1营运
    private byte onbusi;
    // 是否重车，0空车1重车
    private byte onbusy;

    private int id_vehicle;// 车辆ID
    private int id_dev;// 设备ID
    private int tid;
    private int id_group;// 车组ID
    private String sim = "";// SIM卡号
    private String vehicle = "";// 车牌
    private String cph;
    public byte getOnline() {
        return online;
    }

    public void setOnline(byte online) {
        this.online = online;
    }

    public byte getOnbusi() {
        return onbusi;
    }

    public void setOnbusi(byte onbusi) {
        this.onbusi = onbusi;
    }

    public byte getOnbusy() {
        return onbusy;
    }

    public void setOnbusy(byte onbusy) {
        this.onbusy = onbusy;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }


    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private Date gpstime;// GPS时间
    private String date;
    private long state;// 运行状态
    private String carState = "离线";// 运行状态的文字描述
    private int color = 8;// 用于选择显示的车辆图片
    private long alarmFlag;// 报警标志
    private double speed;// 速度
    private int direction;// 方向
    private String driver = "";// 当班司机
    private String sfzh = "";//当班司机身份证号
    private String driverid = "";// 司机代码
    private String tel = "";// 联系电话
    private double lon;//经度
    private double lat;//纬度
    private String gpsState = "定位";// GPS定位
    private AtomicBoolean isShowMB = new AtomicBoolean(false); //是否已显示密标
    private AtomicBoolean isInQY = new AtomicBoolean(false); //是否是已进入区域状态
    private byte islocation;//1不定位 0定位
    private ConcurrentHashMap<Integer, TaskRunBean> areaMap = new ConcurrentHashMap<Integer, TaskRunBean>();
    private String taskrunlogid; //任务执行详细ID
    private Date srsj;
    private Date scsj;



    public String getTaskrunlogid() {
        return taskrunlogid;
    }

    public void setTaskrunlogid(String taskrunlogid) {
        this.taskrunlogid = taskrunlogid;
    }

    public int getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(int id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

    public int getId_dev() {
        return id_dev;
    }

    public void setId_dev(int id_dev) {
        this.id_dev = id_dev;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public Date getGpstime() {
        return gpstime;
    }

    public void setGpstime(Date gpstime) {
        this.gpstime = gpstime;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public long getAlarmFlag() {
        return alarmFlag;
    }

    public void setAlarmFlag(long alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getSim() {
        return sim;
    }

    public void setSim(String sim) {
        this.sim = sim;
    }

    public String getGpsState() {
        return gpsState;
    }

    public void setGpsState(String gpsState) {
        this.gpsState = gpsState;
    }

    public boolean isShowMB() {
        return this.isShowMB.get();
    }

    public void setShowMB(boolean isShowMB) {
        this.isShowMB.set(isShowMB);
    }

    public boolean getIsInQY() {
        return isInQY.get();
    }

    public void setIsInQY(boolean isInQY) {
        this.isInQY.set(isInQY);
    }

    public byte getIslocation() {
        return islocation;
    }

    public void setIslocation(byte islocation) {
        this.islocation = islocation;
    }

    public ConcurrentHashMap<Integer, TaskRunBean> getAreaMap() {
        return areaMap;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public Date getSrsj()
    {
        return srsj;
    }

    public Date getScsj() {
        return scsj;
    }

    public void setSrsj(Date srsj) {
        this.srsj = srsj;
    }
    public void setScsj(Date scsj)
    {
        this.scsj = scsj;
    }

}
