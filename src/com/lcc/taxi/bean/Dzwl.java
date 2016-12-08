package com.lcc.taxi.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_DZWL")
public class Dzwl extends EntityDao<Dzwl> {
    private static final long serialVersionUID = -8221152959642908125L;
    private String id;
    private String name;
    private Date cjsj;//创建时间
    private String cjr;
    private Integer xswz;//显示位置
    private Integer gzzt;//工作状态
    private Integer state;//状态
    private Double lat;//中心点经度
    private Double lon;//中心点纬度
    private String latlon;//电子围栏范围
    private String zfjgid;//执法机构ID

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public Integer getXswz() {
        return xswz;
    }

    public void setXswz(Integer xswz) {
        this.xswz = xswz;
    }

    public Integer getGzzt() {
        return gzzt;
    }

    public void setGzzt(Integer gzzt) {
        this.gzzt = gzzt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLatlon() {
        return latlon;
    }

    public void setLatlon(String latlon) {
        this.latlon = latlon;
    }

    @Override
    public String toString() {
        return "Dzwl [id=" + id + ", name=" + name + ", cjsj=" + cjsj + ", cjr=" + cjr + ", xswz=" + xswz + ", gzzt=" + gzzt + ", state=" + state + ", lat=" + lat + ", lon=" + lon
                + ", latlon=" + latlon + "]";
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

    public String getZfjgid() {
        return zfjgid;
    }

    public void setZfjgid(String zfjgid) {
        this.zfjgid = zfjgid;
    }

}