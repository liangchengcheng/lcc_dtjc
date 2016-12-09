package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_KP_JSY_SSKF")
public class JsySskf extends EntityDao<Jctp> {

    private static final long serialVersionUID = 1L;
    private String id;
    private String sfz;
    private String kf;
    private int year;
    private Date kfsj;
    private String zbmc;

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    public String getId() {
        return id;
    }

    public String getSfz() {
        return sfz;
    }

    public String getKf() {
        return kf;
    }

    public Date getKfsj() {
        return kfsj;
    }

    public String getZbmc() {
        return zbmc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public void setKf(String kf) {
        this.kf = kf;
    }

    public void setKfsj(Date kfsj) {
        this.kfsj = kfsj;
    }

    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }


}
