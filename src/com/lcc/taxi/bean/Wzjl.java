package com.lcc.taxi.bean;


import java.sql.Clob;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_JC_WZJL")
public class Wzjl extends EntityDao<Wzjl> {

    private static final long serialVersionUID = 8312312368831323267L;
    private String id;
    private String ajh;//案件号
    private String wzry;//违章人员
    private String cph;//车牌号
    private String wzdd;//违章地点
    private String wzsj;//违章时间
    //private String zfry;//执法人员
    private String zfzt;//执法状态
    private String state;
    //private String data;//整个JSON
    private Clob data;
    private String idcard;
    private String telephone;
    private String cid;
    private String address;
    private String cartype;
    private String fdjid;
    private String cjid;
    private String ssqy;
    private String yyid;
    private String zfry1;
    private String zfry2;
    private String bz;
    private String ckwp;
    //private String wxbl;
    //private String xcbl;
    private Clob wxbl;
    private Clob xcbl;
    private Date cjsj;
    private String type;
    private String sflr;
    private Integer wxblgs;
    private Integer xcblgs;
    private String jazt;
    private String lrr;
    private Date lrsj;
    private String fjpath;
    private String fkje;
    private String kf;
    private String zfptajh;
    private String sfsd;
    private String kpzh;
    private String kpname;
    private Date sdsj;
    private Date sdqx;

    private Date jasj;
    private String glwzid;
    private String jsid;
    private Date jstime;
    private String cardstate;

    public String getGlwzid() {
        return glwzid;
    }

    public void setGlwzid(String glwzid) {
        this.glwzid = glwzid;
    }

    public String getJazt() {
        return jazt;
    }

    public void setJazt(String jazt) {
        this.jazt = jazt;
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

    public String getState() {
        return state;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAjh() {
        return ajh;
    }

    public void setAjh(String ajh) {
        this.ajh = ajh;
    }

    public String getWzry() {
        return wzry;
    }

    public void setWzry(String wzry) {
        this.wzry = wzry;
    }

    public String getCph() {
        return cph;
    }

    public void setCph(String cph) {
        this.cph = cph;
    }

    public String getWzdd() {
        return wzdd;
    }

    public void setWzdd(String wzdd) {
        this.wzdd = wzdd;
    }

    public String getWzsj() {
        return wzsj;
    }
    public void setWzsj(String wzsj) {
        this.wzsj = wzsj;
    }
    public String getZfzt() {
        return zfzt;
    }
    public void setZfzt(String zfzt) {
        this.zfzt = zfzt;
    }
    //	public String getData() {
    //      return data;
    //	}
    //	public void setData(String data) {
    //		this.data = data;
    //	}
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCid() {
        return cid;
    }
    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCartype() {
        return cartype;
    }
    public void setCartype(String cartype) {
        this.cartype = cartype;
    }
    public String getFdjid() {
        return fdjid;
    }
    public void setFdjid(String fdjid) {
        this.fdjid = fdjid;
    }
    public String getCjid() {
        return cjid;
    }
    public void setCjid(String cjid) {
        this.cjid = cjid;
    }
    public String getSsqy() {
        return ssqy;
    }
    public void setSsqy(String ssqy) {
        this.ssqy = ssqy;
    }
    public String getYyid() {
        return yyid;
    }
    public void setYyid(String yyid) {
        this.yyid = yyid;
    }
    public String getZfry1() {
        return zfry1;
    }
    public void setZfry1(String zfry1) {
        this.zfry1 = zfry1;
    }
    public String getZfry2() {
        return zfry2;
    }
    public void setZfry2(String zfry2) {
        this.zfry2 = zfry2;
    }
    public String getBz() {
        return bz;
    }
    public void setBz(String bz) {
        this.bz = bz;
    }
    public String getCkwp() {
        return ckwp;
    }
    public void setCkwp(String ckwp) {
        this.ckwp = ckwp;
    }
    //	public String getWxbl() {
//		return wxbl;
//	}
//	public void setWxbl(String wxbl) {
//		this.wxbl = wxbl;
//	}
//	public String getXcbl() {
//		return xcbl;
//	}
//	public void setXcbl(String xcbl) {
//		this.xcbl = xcbl;
//	}
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSflr() {
        return sflr;
    }
    public void setSflr(String sflr) {
        this.sflr = sflr;
    }

    public Integer getWxblgs() {
        return wxblgs;
    }

    public void setWxblgs(Integer wxblgs) {
        this.wxblgs = wxblgs;
    }

    public Integer getXcblgs() {
        return xcblgs;
    }

    public void setXcblgs(Integer xcblgs) {
        this.xcblgs = xcblgs;
    }

    public Clob getData() {
        return data;
    }

    public void setData(Clob data) {
        this.data = data;
    }

    public Clob getWxbl() {
        return wxbl;
    }

    public void setWxbl(Clob wxbl) {
        this.wxbl = wxbl;
    }

    public Clob getXcbl() {
        return xcbl;
    }

    public void setXcbl(Clob xcbl) {
        this.xcbl = xcbl;
    }

    public String getLrr() {
        return lrr;
    }
    public Date getLrsj()
    {
        return lrsj;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getFjpath() {
        return fjpath;
    }

    public void setFjpath(String fjpath) {
        this.fjpath = fjpath;
    }

    public String getFkje() {
        return fkje;
    }

    public String getKf() {
        return kf;
    }

    public String getZfptajh() {
        return zfptajh;
    }

    public void setFkje(String fkje) {
        this.fkje = fkje;
    }

    public void setKf(String kf) {
        this.kf = kf;
    }

    public void setZfptajh(String zfptajh) {
        this.zfptajh = zfptajh;
    }

    public String getSfsd() {
        return sfsd;
    }

    public void setSfsd(String sfsd) {
        this.sfsd = sfsd;
    }

    public String getKpzh() {
        return kpzh;
    }

    public void setKpzh(String kpzh) {
        this.kpzh = kpzh;
    }

    public String getKpname() {
        return kpname;
    }

    public void setKpname(String kpname) {
        this.kpname = kpname;
    }

    public Date getSdsj() {
        return sdsj;
    }

    public void setSdsj(Date sdsj) {
        this.sdsj = sdsj;
    }

    public Date getSdqx() {
        return sdqx;
    }

    public void setSdqx(Date sdqx) {
        this.sdqx = sdqx;
    }

    public Date getJasj() {
        return jasj;
    }

    public void setJasj(Date jasj) {
        this.jasj = jasj;
    }

    public String getJsid() {
        return jsid;
    }

    public void setJsid(String jsid) {
        this.jsid = jsid;
    }

    public Date getJstime() {
        return jstime;
    }

    public void setJstime(Date jstime) {
        this.jstime = jstime;
    }

    public String getCardstate() {
        return cardstate;
    }

    public void setCardstate(String cardstate) {
        this.cardstate = cardstate;
    }


}
