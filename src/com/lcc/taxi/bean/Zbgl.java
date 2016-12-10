package com.lcc.taxi.bean;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

/**
 * 模型中没有被映射的字段都是只是做显示用
 */
@Entity
@Table(name = "T_KP_ZBGL")
public class Zbgl extends EntityDao<Zbgl> {

    private static final long serialVersionUID = -2635119728355506789L;
    private String id;

    private String zbmc;

    private String fid;

    private String zbms;

    private String zblx;

    private String sfzjd;

    private String cjlx;

    private Integer khfz;

    private String sjlx;
    private String jjf;

    private String fzlx;

    private String khxz;

    private String sszbbbid;

    private String czyxm;

    private String czyid;
    private String state;

    private Date cjsj;
    private Date gxsj;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getSjlx() {
        return sjlx;
    }

    public void setSjlx(String sjlx) {
        this.sjlx = sjlx;
    }

    @Id
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    @GeneratedValue(generator = "hibernate-uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getZbmc() {
        return zbmc;
    }

    public void setZbmc(String zbmc) {
        this.zbmc = zbmc;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getSszbbbid() {
        return sszbbbid;
    }

    public void setSszbbbid(String sszbbbid) {
        this.sszbbbid = sszbbbid;
    }

    public String getZbms() {
        return zbms;
    }

    public void setZbms(String zbms) {
        this.zbms = zbms;
    }

    public String getZblx() {
        return zblx;
    }

    public void setZblx(String zblx) {
        this.zblx = zblx;
    }

    public String getSfzjd() {
        return sfzjd;
    }

    public void setSfzjd(String sfzjd) {
        this.sfzjd = sfzjd;
    }

    public String getCjlx() {
        return cjlx;
    }

    public void setCjlx(String cjlx) {
        this.cjlx = cjlx;
    }

    public Integer getKhfz() {
        return khfz;
    }

    public void setKhfz(Integer khfz) {
        this.khfz = khfz;
    }

    public String getJjf() {
        return jjf;
    }

    public void setJjf(String jjf) {
        this.jjf = jjf;
    }

    public String getFzlx() {
        return fzlx;
    }

    public void setFzlx(String fzlx) {
        this.fzlx = fzlx;
    }


    public String getKhxz() {
        return khxz;
    }

    public void setKhxz(String khxz) {
        this.khxz = khxz;
    }

    public String getCzyxm() {
        return czyxm;
    }

    public void setCzyxm(String czyxm) {
        this.czyxm = czyxm;
    }

    public String getCzyid() {
        return czyid;
    }


    public void setCzyid(String czyid) {
        this.czyid = czyid;
    }

    /**
     * 根据父id查找下一级的数据
     */
    public static List<Zbgl> getList(Integer fid) {
        String hql = "from Zbgl zbgl where zbgl.fid=? ";
        Object[] values = new Object[]{fid};
        List<Zbgl> list = find(hql, values);
        return list;
    }

    /**
     * 根据指标版本id和节点类型查找该版本下的所有的顶级父类的数据
     */
    public List<Zbgl> getTopList(Integer bbid, String sfzjd) {
        String hql = "from Zbgl zbgl where zbgl.sszbbbid=? and zbgl.sfzjd = ?";
        Object[] values = new Object[]{bbid, sfzjd};
        List<Zbgl> list = find(hql, values);
        return list;
    }

    /**
     * 根据指标版本id查找该版本下指定分类的所有的顶级父类的数据
     */
    public static List<Zbgl> getBbList(Integer bbid, String zbfl) {
        String hql = "from Zbgl zbgl where zbgl.sszbbbid=? and zbgl.zblx=? and fid=? order by zbgl.zbid asc";
        Object[] values = new Object[]{bbid, zbfl, 0};
        List<Zbgl> list = find(hql, values);
        return list;
    }

    /**
     * 根据指标版本id查找该版本下的所有的顶级父类的数据
     */
    public static List<Zbgl> getBbListAll(Integer bbid) {
        String hql = "from Zbgl zbgl where zbgl.sszbbbid=? order by zbgl.fid asc";
        Object[] values = new Object[]{bbid};
        List<Zbgl> list = find(hql, values);
        return list;
    }

    /**
     * 根据指标版本id查找该版本下的所有的顶级父类的数据
     */
    public List<Zbgl> getZbListBybbnNme(Integer bbid, String zbmc) {
        String hql = "from Zbgl zbgl where zbgl.sszbbbid=? and zbgl.zbmc=? order by zbgl.zbid asc";
        Object[] values = new Object[]{bbid, zbmc};
        List<Zbgl> list = find(hql, values);
        return list;
    }

    /**
     * 根据指标名称模糊查询指标信息
     *
     * @param zbmc 指标名称
     * @return 指标信息集合
     */
    public static List<Zbgl> getZbglByZbmc(String sszbbbid, String fid, String sfzjd) {
        String hql = "from Zbgl where  sszbbbid = ? and fid=? and sfzjd = ?";
        Object[] values = new Object[]{sszbbbid, fid, sfzjd};
        return find(hql, values);
    }

    /**
     * 获得版本下某个指标指标的子指标详细信息
     */
    public static List<Zbgl> getZzbxx(String fid, String sfzjd) {
        String hql = "from Zbgl where fid=? and sfzjd = ?";
        Object[] values = new Object[]{fid, sfzjd};
        return find(hql, values);
    }

    /**
     * 根据指标id查指标
     */
    public static List<Zbgl> getZb(String id) {
        String hql = "from Zbgl where id = ?";
        Object[] values = new Object[]{id};
        return find(hql, values);
    }

    public static List<Zbgl> getZBByZbmc(String zbmc) {
        String hql = "from Zbgl where zbmc = ?";
        Object[] values = new Object[]{zbmc};
        return find(hql, values);
    }

    /**
     * 企业根据指标名称模糊查询指标信息
     *
     * @param zbmc 指标名称
     * @return 指标信息集合
     */
    public List<Zbgl> getZb(Integer sszbbbid, String dycjlx) {
        String hql = "from Zbgl where  sszbbbid like ? and dycjlx like ? and fid!=0";
        Object[] values = new Object[]{sszbbbid, dycjlx};
        return find(hql, values);
    }

    public List<Zbgl> getZbglByZbmcJsy(String zbmc) {
        //zbmc = zbmc + "%";
        String hql = "from Zbgl where zbmc = ?";
        Object[] values = new Object[]{zbmc};
        return find(hql, values);
    }

    /**
     * 通过版本id查找采集类型的指标项
     */
    public static List<Zbgl> zbbycjlx(Integer bbid, String cjlx) {
        String hql = "from Zbgl zbgl where zbgl.sszbbbid=? and zbgl.cjlx=? and zbgl.jjf=1";
        Object[] values = new Object[]{bbid, cjlx};
        List<Zbgl> list = find(hql, values);
        return list;
    }

    public static Zbgl getZbglById(Integer id, Integer fid) {
        String hql = "from Zbgl where  sszbbbid=? and id=?";
        Object[] values = new Object[]{id, fid};
        return (Zbgl) findUnique(hql, values);
    }

    /**
     * 新增指标
     */
    public boolean addZbList(Zbgl zbgl) {
        getSession().save(zbgl);
        return true;
    }

    /**
     * 根据指标管理  id 删除指标
     *
     * @param id ：指标管理表中的id
     */
    public static int delzb(String id) {
        int i = 0;
        String hql = "from Zbgl where fid = ?";
        Object[] values = new Object[]{id};
        List<Zbgl> list = find(hql, values);
        int s = 0;
        if (list.size() > 0) {
            for (Zbgl zb : list) {
                String hqlzb = "delete from Zbgl where fid=?";
                Object[] val = new Object[]{zb.getId()};
                execute(hqlzb, val);
                s++;
            }
        }

        if (s == list.size()) {
            String hql2 = "delete from Zbgl where id=?";
            execute(hql2, values);
            i = 1;
        }

        return i;
    }
}
