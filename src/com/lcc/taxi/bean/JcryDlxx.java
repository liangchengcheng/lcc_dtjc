package com.lcc.taxi.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_RY_DLXX")
public class JcryDlxx extends EntityDao<JcryDlxx> {

    private static final long serialVersionUID = -6548790098687507154L;
    private String id;
    private String zh;//人员id
    private Double lat;//纬度
    private Double lon;//经度
    private Date dlsj;//登陆时间
    private String sbbs;//设备标识
    private String token;
    private String state;
    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getZh() {
        return zh;
    }
    public void setZh(String zh) {
        this.zh = zh;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Date getDlsj() {
        return dlsj;
    }
    public void setDlsj(Date dlsj) {
        this.dlsj = dlsj;
    }
    public String getSbbs() {
        return sbbs;
    }
    public void setSbbs(String sbbs) {
        this.sbbs = sbbs;
    }
    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }
    public Double getLon() {
        return lon;
    }
    public void setLon(Double lon) {
        this.lon = lon;
    }

}
