package com.lcc.taxi.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_LOGAPP")
public class LogApp extends EntityDao<LogApp> {
    private static final long serialVersionUID = 1365751747268811390L;
    private String id;
    private String cz;//操作
    private String czyid1;//操作员1
    private String czyid2;//操作员1
    private Date cjsj;//创建时间
    private Double lat;
    private Double lon;
    private String sbbs;
    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCz() {
        return cz;
    }
    public void setCz(String cz) {
        this.cz = cz;
    }
    public Date getCjsj() {
        return cjsj;
    }
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
    public String getCzyid1() {
        return czyid1;
    }
    public void setCzyid1(String czyid1) {
        this.czyid1 = czyid1;
    }
    public String getCzyid2() {
        return czyid2;
    }
    public void setCzyid2(String czyid2) {
        this.czyid2 = czyid2;
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
    public String getSbbs() {
        return sbbs;
    }
    public void setSbbs(String sbbs) {
        this.sbbs = sbbs;
    }


}
