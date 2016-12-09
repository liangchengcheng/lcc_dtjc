package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_DEV")
public class Tjcdev extends EntityDao<Tjcdev> {

    private static final long serialVersionUID = 6501750417096574963L;
    private String id;
    private String sbbm;
    private String sbbs;
    private Date cjsj;
    private Date gxsj;
    private String czyid;
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
    public String getSbbm() {
        return sbbm;
    }
    public void setSbbm(String sbbm) {
        this.sbbm = sbbm;
    }
    public Date getCjsj() {
        return cjsj;
    }
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
    public Date getGxsj() {
        return gxsj;
    }
    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }
    public String getCzyid() {
        return czyid;
    }
    public void setCzyid(String czyid) {
        this.czyid = czyid;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getSbbs() {
        return sbbs;
    }
    public void setSbbs(String sbbs) {
        this.sbbs = sbbs;
    }

}
