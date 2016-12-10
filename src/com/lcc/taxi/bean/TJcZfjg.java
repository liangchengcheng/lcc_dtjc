package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_ZFJG")
public class TJcZfjg extends EntityDao<TJcZfjg> {

    private static final long serialVersionUID = -1438847536172137909L;
    private String id;
    private String jgmc;
    private String addr;
    private String jgfid;
    private Date cjsj;
    private Date xgsj;
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
    public String getJgmc() {
        return jgmc;
    }
    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getJgfid() {
        return jgfid;
    }
    public void setJgfid(String jgfid) {
        this.jgfid = jgfid;
    }
    public Date getCjsj() {
        return cjsj;
    }
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
    public Date getXgsj() {
        return xgsj;
    }
    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
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

}
