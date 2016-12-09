package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_LOG")
public class Log extends EntityDao<Log> {
    private static final long serialVersionUID = 1365751747268811390L;
    private String id;
    private String cz;//操作
    private String czyid;//操作员id
    private String czyxm;//操作员xm
    private Date cjsj;//创建时间
    private String detail;//操作详情
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
    public String getCzyid() {
        return czyid;
    }
    public void setCzyid(String czyid) {
        this.czyid = czyid;
    }
    public String getCzyxm() {
        return czyxm;
    }
    public void setCzyxm(String czyxm) {
        this.czyxm = czyxm;
    }
    public Date getCjsj() {
        return cjsj;
    }
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

}
