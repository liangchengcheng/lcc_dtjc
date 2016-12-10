package com.lcc.taxi.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_FWQCS")
public class TjcFwqcs extends EntityDao<TjcFwqcs> {

    private static final long serialVersionUID = 5881533796663809511L;

    private String id;
    private String fwqdz;
    private String dkh;
    private String ywdz;
    private String cs;

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFwqdz() {
        return fwqdz;
    }

    public void setFwqdz(String fwqdz) {
        this.fwqdz = fwqdz;
    }

    public String getDkh() {
        return dkh;
    }

    public void setDkh(String dkh) {
        this.dkh = dkh;
    }

    public String getYwdz() {
        return ywdz;
    }

    public void setYwdz(String ywdz) {
        this.ywdz = ywdz;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }


}