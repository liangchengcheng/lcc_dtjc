package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_VERSION")
public class Version extends EntityDao<Version> {
    private static final long serialVersionUID = 6740444325784523397L;
    private String id;
    private String durl;//下载地址/
    private Date fbsj;//发布时间
    private String bbms;//版本描述
    private String version;//版本号
    private String sfsj;//是否强制
    private Integer code;//版本code
    private String czyid;
    private Date xgsj;
    private double apkdx;//apk大小
    private String state;//状态

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

    public String getDurl() {
        return durl;
    }
    public void setDurl(String durl) {
        this.durl = durl;
    }
    public String getBbms() {
        return bbms;
    }
    public void setBbms(String bbms) {
        this.bbms = bbms;
    }
    public Date getFbsj() {
        return fbsj;
    }
    public void setFbsj(Date fbsj) {
        this.fbsj = fbsj;
    }
    public String getSfsj() {
        return sfsj;
    }
    public void setSfsj(String sfsj) {
        this.sfsj = sfsj;
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getCzyid() {
        return czyid;
    }
    public void setCzyid(String czyid) {
        this.czyid = czyid;
    }
    public Date getXgsj() {
        return xgsj;
    }
    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }
    public double getApkdx() {
        return apkdx;
    }
    public void setApkdx(double apkdx) {
        this.apkdx = apkdx;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

}
