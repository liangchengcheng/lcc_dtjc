package com.lcc.taxi.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_PIC")
public class Jctp  extends EntityDao<Jctp> {

    private static final long serialVersionUID = 2880623525283173202L;
    private String id;
    private byte[] pic;
    private Date cjsj;
    private String czyid;
    private String name;
    private String path;

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public byte[] getPic() {
        return pic;
    }
    public void setPic(byte[] pic) {
        this.pic = pic;
    }
    public Date getCjsj() {
        return cjsj;
    }
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
    public String getCzyid() {
        return czyid;
    }
    public void setCzyid(String czyid) {
        this.czyid = czyid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

}
