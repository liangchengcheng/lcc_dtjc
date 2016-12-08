package com.lcc.taxi.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_DZWL_LOG")
public class DzwlLog extends EntityDao<DzwlLog> {
    private static final long serialVersionUID = 3000641462608510145L;
    private String id;
    private String wlid;//围栏id
    private Date cjsj;//operate time
    private String czyid1;//user id 1
    private String czyid2;//user id 2
    private Date cczsj;
    private String cczyid1;//user id 1
    private String cczyid2;//user id 2
    private Integer cz;//0:close;1:open
    private Date kssj;
    private Date jssj;//end time
    private Integer sfxfmb;
    private Integer mblx;
    private String mbwznr;
    private byte[] fwtp;
    private byte[] fwtpxfwz;

    public String getWlid() {
        return wlid;
    }
    public void setWlid(String wlid) {
        this.wlid = wlid;
    }
    public Date getCjsj() {
        return cjsj;
    }
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
    public String getCzyid1() {
        return czyid1;
    }
    public void setCzyid1(String czyid1) {
        this.czyid1 = czyid1;
    }
    public String getCzyid2() {
        return czyid2;
    }
    public void setCzyid2(String czyid2) {
        this.czyid2 = czyid2;
    }
    public Integer getCz() {
        return cz;
    }
    public void setCz(Integer cz) {
        this.cz = cz;
    }
    public Date getJssj() {
        return jssj;
    }
    public void setJssj(Date jssj) {
        this.jssj = jssj;
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
    public Date getKssj() {
        return kssj;
    }
    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }
    public Integer getSfxfmb() {
        return sfxfmb;
    }
    public void setSfxfmb(Integer sfxfmb) {
        this.sfxfmb = sfxfmb;
    }
    public Integer getMblx() {
        return mblx;
    }
    public void setMblx(Integer mblx) {
        this.mblx = mblx;
    }
    public String getMbwznr() {
        return mbwznr;
    }
    public void setMbwznr(String mbwznr) {
        this.mbwznr = mbwznr;
    }
    public byte[] getFwtp() {
        return fwtp;
    }
    public void setFwtp(byte[] fwtp) {
        this.fwtp = fwtp;
    }
    public byte[] getFwtpxfwz() {
        return fwtpxfwz;
    }
    public void setFwtpxfwz(byte[] fwtpxfwz) {
        this.fwtpxfwz = fwtpxfwz;
    }
    public Date getCczsj() {
        return cczsj;
    }
    public void setCczsj(Date cczsj) {
        this.cczsj = cczsj;
    }
    public String getCczyid1() {
        return cczyid1;
    }
    public void setCczyid1(String cczyid1) {
        this.cczyid1 = cczyid1;
    }
    public String getCczyid2() {
        return cczyid2;
    }
    public void setCczyid2(String cczyid2) {
        this.cczyid2 = cczyid2;
    }

}