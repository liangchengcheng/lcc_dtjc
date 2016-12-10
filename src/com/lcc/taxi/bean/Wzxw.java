package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_WZXW")
public class Wzxw extends EntityDao<Wzxw> {

    private static final long serialVersionUID = 7124839160129625198L;
    private String id;
    private String wznr;
    private String wgyj;
    private Date cjsj;
    private String state;
    private String czyid;
    private Integer kf;

    @Id
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @GeneratedValue(generator = "hibernate-uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWznr() {
        return wznr;
    }

    public void setWznr(String wznr) {
        this.wznr = wznr;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWgyj() {
        return wgyj;
    }

    public void setWgyj(String wgyj) {
        this.wgyj = wgyj;
    }

    public String getCzyid() {
        return czyid;
    }

    public void setCzyid(String czyid) {
        this.czyid = czyid;
    }

    public Integer getKf() {
        return kf;
    }

    public void setKf(Integer kf) {
        this.kf = kf;
    }

}
