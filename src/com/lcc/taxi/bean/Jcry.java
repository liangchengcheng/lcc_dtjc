package com.lcc.taxi.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcc.framework.constants.UserConstants;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_RY")
public class Jcry extends EntityDao<Jcry> {
    private static final long serialVersionUID = -4532032300844688783L;
    private String id;
    private String zh;
    private String passwd;
    private String name;
    private String mobile;
    private String zgz;
    private Date Cjsj;
    private Date xgsj;
    private Date dlsj;
    private String sbbs;
    private String state;
    private String jgid;
    private String czyid;
    private String txid;//头像id
    private String systmid;
    private String role_id;
    private Date logindate;
    private String loginip;
    private String type;

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    @Id
    @GenericGenerator(name="hibernate-uuid",strategy="uuid")
    @GeneratedValue(generator="hibernate-uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getZgz() {
        return zgz;
    }

    public void setZgz(String zgz) {
        this.zgz = zgz;
    }

    public Date getCjsj() {
        return Cjsj;
    }

    public void setCjsj(Date cjsj) {
        Cjsj = cjsj;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDlsj() {
        return dlsj;
    }
    public void setDlsj(Date dlsj) {
        this.dlsj = dlsj;
    }
    public String getSbbs() {
        return sbbs;
    }

    public void setSbbs(String sbbs) {
        this.sbbs = sbbs;
    }

    public String getCzyid() {
        return czyid;
    }

    public void setCzyid(String czyid) {
        this.czyid = czyid;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    @Override
    public String toString() {
        return "Jcry [id=" + id + ", zh=" + zh + ", passwd=" + passwd + ", name=" + name + ", mobile=" + mobile
                + ", zgz=" + zgz + ", Cjsj=" + Cjsj + ", xgsj=" + xgsj + ", dlsj=" + dlsj + ", sbbs=" + sbbs
                + ", state=" + state + ", jgid=" + jgid + ", czyid=" + czyid + ", txid=" + txid + "]";
    }

    public String getSystmid() {
        return systmid;
    }

    public void setSystmid(String systmid) {
        this.systmid = systmid;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 根据操作员账号和角色类型查询操作员
     */
    public static Jcry getOperByRoleid(String passport) {
        String hql = "from Jcry where state='1' and zgz = ? and systmid='"
                + UserConstants.SYSTEMID +"' and type like '1%'";
        Object[] values = new Object[] { passport};
        return (Jcry) findUnique(hql, values);
    }

    public static void updateOper(String ip,Jcry oper) {
        //String hql = "update Users set zhdlsj=?,zhdlip=? where id=?";
        String hql = "update Jcry set LOGINDATE=?,LOGINIP=? where id=?";
        Object[] values = new Object[] { new Date(),ip,oper.getId()};
        Jcry.execute(hql, values);
    }
}
