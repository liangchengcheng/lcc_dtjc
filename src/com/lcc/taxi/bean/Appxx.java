package com.lcc.taxi.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;
/**
 * Created by lcc on 2016/12/7.
 */

@Entity
@Table(name = "T_JC_APPXX")
public class Appxx extends EntityDao<Appxx> {

    private static final long serialVersionUID = 1130582404410420798L;
    private String id;
    private String version;
    private String bbms;
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
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getBbms() {
        return bbms;
    }
    public void setBbms(String bbms) {
        this.bbms = bbms;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }


}
