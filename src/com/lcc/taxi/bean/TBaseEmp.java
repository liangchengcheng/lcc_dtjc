package com.lcc.taxi.bean;

import com.lcc.framework.dao.EntityDao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_BASE_EMP")
public class TBaseEmp extends EntityDao<TBaseEmp> {

    private static final long serialVersionUID = 823632324177774503L;

    private Long idEmployee;

    private Long idOwner;

    private Long idVehicle;

    private String name;

    private String employeecode;

    private String ptsex;

    private Date birthday;

    private String ptidcard;

    private byte[] picEmp;

    private String emppicpath;

    private String folk;

    private String tel;

    private String addr;

    private String ptaddr;

    private String zip;

    private String title;

    private String diploma;

    private String email;

    private String ecerttype;

    private String idEcert;

    private Date firstdatee;

    private Date ecertgrantdate;

    private Date certendate;

    private String grantorgan;

    private String drliencestat;

    private String driverlicense;

    private String drivertype;

    private Date drliencedate;

    private String honestchklvl;

    private Date honestchkdate;

    private String honestchkorgan;

    private String workstate;

    private Date addtime;

    private String addoperator;

    private Date lasttime;

    private String operator;

    private String archivesno;

    private Integer zfl;

    private String remarks;

    private String jiguan;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long id) {
        this.idEmployee = id;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeecode() {
        return employeecode;
    }

    public void setEmployeecode(String employeecode) {
        this.employeecode = employeecode;
    }

    public String getPtsex() {
        return ptsex;
    }

    public void setPtsex(String ptsex) {
        this.ptsex = ptsex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPtidcard() {
        return ptidcard;
    }

    public void setPtidcard(String ptidcard) {
        this.ptidcard = ptidcard;
    }

    public byte[] getPicEmp() {
        return picEmp;
    }

    public void setPicEmp(byte[] picEmp) {
        this.picEmp = picEmp;
    }

    public String getFolk() {
        return folk;
    }

    public void setFolk(String folk) {
        this.folk = folk;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPtaddr() {
        return ptaddr;
    }

    public void setPtaddr(String ptaddr) {
        this.ptaddr = ptaddr;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEcerttype() {
        return ecerttype;
    }

    public void setEcerttype(String ecerttype) {
        this.ecerttype = ecerttype;
    }

    public String getIdEcert() {
        return idEcert;
    }

    public void setIdEcert(String idEcert) {
        this.idEcert = idEcert;
    }

    public Date getFirstdatee() {
        return firstdatee;
    }

    public void setFirstdatee(Date firstdatee) {
        this.firstdatee = firstdatee;
    }

    public Date getEcertgrantdate() {
        return ecertgrantdate;
    }

    public void setEcertgrantdate(Date ecertgrantdate) {
        this.ecertgrantdate = ecertgrantdate;
    }

    public Date getCertendate() {
        return certendate;
    }

    public void setCertendate(Date certendate) {
        this.certendate = certendate;
    }

    public String getGrantorgan() {
        return grantorgan;
    }

    public void setGrantorgan(String grantorgan) {
        this.grantorgan = grantorgan;
    }

    public String getDrliencestat() {
        return drliencestat;
    }

    public void setDrliencestat(String drliencestat) {
        this.drliencestat = drliencestat;
    }

    public String getDriverlicense() {
        return driverlicense;
    }

    public void setDriverlicense(String driverlicense) {
        this.driverlicense = driverlicense;
    }

    public String getDrivertype() {
        return drivertype;
    }

    public void setDrivertype(String drivertype) {
        this.drivertype = drivertype;
    }

    public Date getDrliencedate() {
        return drliencedate;
    }

    public void setDrliencedate(Date drliencedate) {
        this.drliencedate = drliencedate;
    }

    public String getHonestchklvl() {
        return honestchklvl;
    }

    public void setHonestchklvl(String honestchklvl) {
        this.honestchklvl = honestchklvl;
    }

    public Date getHonestchkdate() {
        return honestchkdate;
    }

    public void setHonestchkdate(Date honestchkdate) {
        this.honestchkdate = honestchkdate;
    }

    public String getHonestchkorgan() {
        return honestchkorgan;
    }

    public void setHonestchkorgan(String honestchkorgan) {
        this.honestchkorgan = honestchkorgan;
    }

    public String getWorkstate() {
        return workstate;
    }

    public void setWorkstate(String workstate) {
        this.workstate = workstate;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAddoperator() {
        return addoperator;
    }

    public void setAddoperator(String addoperator) {
        this.addoperator = addoperator;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getArchivesno() {
        return archivesno;
    }

    public void setArchivesno(String archivesno) {
        this.archivesno = archivesno;
    }

    public Integer getZfl() {
        return zfl;
    }

    public void setZfl(Integer zfl) {
        this.zfl = zfl;
    }

    public String getEmppicpath() {
        return emppicpath;
    }

    public void setEmppicpath(String emppicpath) {
        this.emppicpath = emppicpath;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getJiguan() {
        return jiguan;
    }

    public void setJiguan(String jiguan) {
        this.jiguan = jiguan;
    }
}