package com.lcc.taxi.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

/**
 * Created by lcc on 2016/12/8.
 */
@Entity
@Table(name = "T_JC_EMP_NDKH")
public class EmpNdkh extends EntityDao<EmpNdkh> {

    private static final long serialVersionUID = 1130582404410420798L;
    private String id;
    private String year;
    private String ptidcard;
    private Integer sumkf;
    private Integer dqkf;
    private Integer lastkf;
    private Date lasttime;
    private String islock;
    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getPtidcard() {
        return ptidcard;
    }
    public void setPtidcard(String ptidcard) {
        this.ptidcard = ptidcard;
    }
    public Integer getSumkf() {
        return sumkf;
    }
    public void setSumkf(Integer sumkf) {
        this.sumkf = sumkf;
    }
    public Integer getDqkf() {
        return dqkf;
    }
    public void setDqkf(Integer dqkf) {
        this.dqkf = dqkf;
    }
    public Integer getLastkf() {
        return lastkf;
    }
    public void setLastkf(Integer lastkf) {
        this.lastkf = lastkf;
    }
    public Date getLasttime() {
        return lasttime;
    }
    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }
    public String getIslock() {
        return islock;
    }
    public void setIslock(String islock) {
        this.islock = islock;
    }

}
