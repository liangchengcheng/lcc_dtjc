package com.lcc.taxi.bean;

/**
 * Created by lcc on 2016/12/9.
 */
public class TBaseRun {
    private String scsj;//上车时间
    private  String xcsj;//下车时间
    private String cphm;//车牌号码
    private Float yylc;//运营里程
    private Integer dhsj;//等候时间
    private Float yyje;//运营金额
    private String sjdm;//司机代码
    private Float fjf;//附加费用

    private String name;//司机名称

    public String getScsj() {
        return scsj;
    }

    public void setScsj(String scsj) {
        this.scsj = scsj;
    }

    public String getXcsj() {
        return xcsj;
    }

    public void setXcsj(String xcsj) {
        this.xcsj = xcsj;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public Float getYylc() {
        return yylc;
    }

    public void setYylc(Float yylc) {
        this.yylc = yylc;
    }

    public Integer getDhsj() {
        return dhsj;
    }

    public void setDhsj(Integer dhsj) {
        this.dhsj = dhsj;
    }

    public Float getYyje() {
        return yyje;
    }

    public void setYyje(Float yyje) {
        this.yyje = yyje;
    }

    public String getSjdm() {
        return sjdm;
    }

    public void setSjdm(String sjdm) {
        this.sjdm = sjdm;
    }

    public Float getFjf() {
        return fjf;
    }

    public void setFjf(Float fjf) {
        this.fjf = fjf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
