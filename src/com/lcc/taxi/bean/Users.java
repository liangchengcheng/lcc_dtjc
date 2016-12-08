package com.lcc.taxi.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.Columns;


@Entity
@Table(name = "T_USERS")
public class Users extends EntityDao<Users> {

    private static final long serialVersionUID = -3067445423013166183L;

    private Integer id;
    private String username;

    private String passwd;

    private String name;

    private String sex;

    private String phone;

    private String systmid;

    private Integer role_id;

    private Integer idowner;

    private String user_stat;

    private String memo;

    //	private String cjczy;
    private String cjczyzh;
    private String cjczyxm;

    //	private Date zhdlsj;
    private Date lastlogintime;//最后一次登录时间

    //	private String zhdlip;
    private String loginip;//最后一次登录ip
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSystmid() {
        return systmid;
    }

    public void setSystmid(String systmid) {
        this.systmid = systmid;
    }
    @Column(name = "role_id")
    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }
    @Column(name = "id_owner")
    public Integer getIdowner() {
        return idowner;
    }

    public void setidowner(Integer idowner) {
        this.idowner = idowner;
    }
    @Column(name = "user_stat")
    public String getUser_stat() {
        return user_stat;
    }

    public void setUser_stat(String user_stat) {
        this.user_stat = user_stat;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

/*	public String getCjczy() {
		return cjczy;
	}

	public void setCjczy(String cjczy) {
		this.cjczy = cjczy;
	}*/

	/*public Date getZhdlsj() {
		return zhdlsj;
	}

	public void setZhdlsj(Date zhdlsj) {
		this.zhdlsj = zhdlsj;
	}*/



	/*public String getZhdlip() {
		return zhdlip;
	}

	public void setZhdlip(String zhdlip) {
		this.zhdlip = zhdlip;
	}*/



    public String getCjczyzh() {
        return cjczyzh;
    }

    public void setCjczyzh(String cjczyzh) {
        this.cjczyzh = cjczyzh;
    }

    public String getCjczyxm() {
        return cjczyxm;
    }

    public void setCjczyxm(String cjczyxm) {
        this.cjczyxm = cjczyxm;
    }



    /**
     * 根据passport获取用户
     */
    public static Users getUser(String passport,Integer role_id) {
        String hql = "from Users where username = ? and role_id=?";
        Object[] values = new Object[] { passport,role_id};
        return (Users) findUnique(hql, values);
    }

    /**
     * 修改企业考核状态
     */
    public static void updateUser(String state,String username) {
        String hql = "update Users users set users.user_stat=? where users.username=?";
        Object[] values = new Object[] { state, username };
        Users.execute(hql, values);
    }

    /**
     * 重置企业密码
     */
    public static void changepwd(String password,String username) {
        String hql = "update Users users set users.passwd=? where users.username=?";
        Object[] values = new Object[] { password, username };
        Users.execute(hql, values);
    }

    /**
     * 根据操作员账号和角色类型查询操作员
     */
    public static Users getOperByRoleid(String passport) {
        String hql = "from Users where username = ? and systmid='" + UserConstants.SYSTEMID +"'";
        Object[] values = new Object[] { passport};
        return (Users) findUnique(hql, values);
    }

    /**
     * 修改操作员登录信息
     */
    public static void updateOper(String ip,Users oper) {
        String hql = "update Users set LOGINDATE=?,LOGINIP=? where id=?";
        Object[] values = new Object[] { new Date(),ip,oper.getId()};
        Users.execute(hql, values);
    }

}

