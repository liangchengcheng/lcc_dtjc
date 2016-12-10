package com.lcc.taxi.dao;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.taxi.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * Created by lcc on 2016/12/7.
 */
public class AjclDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int ajclDel(String id) throws Exception {
        String sql = "delete from  T_JC_WZJL WHERE ID = ?";
        Object[] val = new Object[]{id};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public int ajclresult(String id, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_WZJL SET ZFZT = '1' WHERE ID = ?";
        Object[] val = new Object[]{id};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public int szlr(String id, String lrr, String lrsj, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_WZJL SET sflr = '1',lrr =?,lrsj =? WHERE ID = ?";
        Object[] val = new Object[]{lrr, PubUtils.str2Date(lrsj), id};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public List<Map<String, Object>> wzxwlist(String ajh) {
        String sql = "SELECT XW.WZNR,X.WFCDID,X.WZXWID FROM T_JC_WZJL W,T_JC_WZJL_XW X,T_JC_WZXW XW WHERE W.AJH = X.AJH(+) AND X.STATE='1' AND W.STATE='1' AND X.WZXWID=XW.ID AND W.AJH=?";
        Object[] value = new Object[]{ajh};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> ycxht(String wzjlid) {
        String sql = "SELECT ID,WZID,ZFPTSPR,ZFPTAJH,CXSM FROM T_JC_WZJL_YCX WHERE STATE ='1' AND WZID=?";
        Object[] value = new Object[]{wzjlid};
        return jdbcTemplate.queryForList(sql, value);
    }

    public Boolean addycx(Wzjlycx ycx, Log log) throws Exception {
        log.save();
        ycx.save();
        String sql = "UPDATE T_JC_WZJL SET ZFZT='1' WHERE ID = ?";
        Object[] val = new Object[]{ycx.getWzid()};
        int n = jdbcTemplate.update(sql, val);
        return true;
    }

    public int updateycx(Wzjlycx ycx, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_WZJL_YCX SET ZFPTSPR=?,ZFPTAJH=?,CXSM=?,LRR=?,LRSJ=? WHERE ID = ?";
        Object[] val = new Object[]{ycx.getZfptspr(), ycx.getZfptajh(), ycx.getCxsm(), ycx.getLrr(), ycx.getLrsj(), ycx.getId()};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public List<Map<String, Object>> yjawzxw(String ajh) {
        String sql = "select w.wznr,t.wzxwid,t.wfcdid,cd.wfcd from t_jc_wzjl_xw t,t_jc_wzxw w,t_jc_wfcd cd where w.state='1' and t.state='1' and t.wzxwid = w.id and t.wfcdid=cd.id and t.ajh=? ";
        Object[] value = new Object[]{ajh};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> wfcd(String id) {
        //应该查违章行为里面的
        String sql = "select t.wfxfid,d.wfcd,d.id from t_jc_wzxw_wfcd t,t_jc_wfcd d where t.wfcdid = d.id and t.state ='1' and t.wfxfid=? order by wfcdid";
        Object[] value = new Object[]{id};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> wzjlxwchax(String ajh, String wzxwid) {
        String sql = "select * from t_jc_wzjl_xw  where wzxwid = ? and  ajh=? ";
        Object[] value = new Object[]{wzxwid, ajh};
        return jdbcTemplate.queryForList(sql, value);
    }

    public int wzjlxwxg(String ajh, String wzxwid, String cd) throws Exception {
        String sql = "UPDATE T_JC_WZJL_XW SET WFCDID=? WHERE AJH = ? AND WZXWID=?";
        Object[] val = new Object[]{cd, ajh, wzxwid};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public Boolean wzjlxwadd(String ajh, String wzxwid, String cd) throws Exception {
        String sql = "insert into T_JC_WZJL_XW(ajh,wzxwid,wfcdid) values(?,?,?)";
        Object[] values = new Object[]{ajh, wzxwid, cd};
        jdbcTemplate.update(sql, values);
        return true;
    }

    public List<Map<String, Object>> yjawzxw2(String ajh) {

        String sql = "select t.wfcdid,w.id,w.wznr from t_jc_wzjl_xw t,t_jc_wzxw w where w.state='1' and t.wzxwid = w.id and t.ajh=? ";
        Object[] value = new Object[]{ajh};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> jsysfz(String jsyid) {

        String sql = "select t.ptidcard from t_base_emp t where t.id_employee=? ";
        Object[] value = new Object[]{jsyid};
        return jdbcTemplate.queryForList(sql, value);
    }

    public int yjadsr(List<Wzjlxw> list, Wzjl wzjl, JsySskf jsysskf, String sfzh, String ajh, String zfs) throws Exception {
        String sql2 = "UPDATE T_JC_WZJL_XW SET STATE = '0' WHERE AJH = ?";
        Object[] val2 = new Object[]{ajh};
        int nn = jdbcTemplate.update(sql2, val2);
        int m = list.size();
        for (int i = 0; i < m; i++) {
            Wzjlxw xw = list.get(i);
            String sql3 = "insert into T_JC_WZJL_XW(ajh,wzxwid,wfcdid,state) values(?,?,?,?)";
            Object[] values = new Object[]{xw.getAjh(), xw.getWzxwid(), xw.getWfcdid(), xw.getState()};
            jdbcTemplate.update(sql3, values);
        }


        String sql = "UPDATE T_JC_WZJL SET ZFZT=?,ZFPTAJH=?,FKJE=?,KF=?,JASJ=? WHERE ID = ?";
        Object[] val = new Object[]{wzjl.getZfzt(), wzjl.getZfptajh(), wzjl.getFkje(), wzjl.getKf(), wzjl.getJasj(), wzjl.getId()};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public int yjadsrupd(List<Wzjlxw> list, YjaDsr yjadsr) throws Exception {
        String sql2 = "UPDATE T_JC_WZJL_XW SET STATE = '0' WHERE AJH = ?";
        Object[] val2 = new Object[]{yjadsr.getWzid()};
        int nn = jdbcTemplate.update(sql2, val2);
        int m = list.size();
        for (int i = 0; i < m; i++) {
            Wzjlxw xw = list.get(i);
            String sql3 = "insert into T_JC_WZJL_XW(ajh,wzxwid,wfcdid,state) values(?,?,?,?)";
            Object[] values = new Object[]{xw.getAjh(), xw.getWzxwid(), xw.getWfcdid(), xw.getState()};
            jdbcTemplate.update(sql3, values);
        }

        String sql = "UPDATE T_JC_WZJL_YJA_DSR SET ZFPTAJH=?,FKJE=? WHERE ID = ?";
        Object[] val = new Object[]{yjadsr.getZfptajh(), yjadsr.getFkje(), yjadsr.getId()};

        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public List<Map<String, Object>> yjadsrhtfs(String dsrid) {
        String sql = "SELECT " + SystemConstants.KHZF + "-dqkf dqkf FROM t_jc_emp_ndkh WHERE  ptidcard=? and year=?";
        Calendar c = Calendar.getInstance();
        Object[] value = new Object[]{dsrid, c.get(Calendar.YEAR)};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> yjadsrhtfs_ck(String dsrid) {
        String sql = "select jl.zfptajh,TO_CHAR(jl.jasj,'YYYY-MM-DD HH24:MI:SS') jasj,jl.kf,jl.fkje,xw.wfcdid,wz.wznr from t_jc_wzjl jl right join t_jc_wzjl_xw xw on jl.ajh=xw.ajh left join t_jc_wzxw wz on xw.wzxwid=wz.id where xw.state='1' and jl.id=?";
        Object[] value = new Object[]{dsrid};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> yjafdsrhtfs_ck(String dsrid) {
        String sql = "select xw.wfcdid,wz.wznr from t_jc_wzjl jl right join t_jc_wzjl_xw xw on jl.ajh=xw.ajh left join t_jc_wzxw wz on xw.wzxwid=wz.id where xw.state='1' and jl.id=?";
        Object[] value = new Object[]{dsrid};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> yjawzxwfdsrhtfs(String dsrid) {
        String sql = "SELECT fs FROM t_kp_jsy_ss WHERE STATE ='1' AND sfz=?";
        Object[] value = new Object[]{dsrid};
        return jdbcTemplate.queryForList(sql, value);
    }

    public int yjafdsr(List<Wzjlxw> list, Wzjl wzjl, Wzjl jl, JsySskf jsysskf, List<Wzjlxw> list2) throws Exception {
        jl.save();
        int m2 = list2.size();
        for (int i = 0; i < m2; i++) {
            Wzjlxw xw2 = list2.get(i);
            String sql3 = "insert into T_JC_WZJL_XW(ajh,wzxwid,wfcdid,state) values(?,?,?,?)";
            Object[] values = new Object[]{xw2.getAjh(), xw2.getWzxwid(), xw2.getWfcdid(), xw2.getState()};
            jdbcTemplate.update(sql3, values);
        }

        String sql = "UPDATE T_JC_WZJL SET ZFZT=?,jasj＝? WHERE ID = ?";
        Object[] val = new Object[]{wzjl.getZfzt(), wzjl.getJasj(), wzjl.getId()};
        int n = jdbcTemplate.update(sql, val);

        return n;
    }

    public List<Map<String, Object>> yjafdsrht(String wzjlid) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT W.ID,W.AJH,W.WZRY,W.WZDD,W.CPH,W.WZSJ,ZR.NAME || ',' || R2.NAME ZFRY,ZR.NAME ZFRY1,ZR.ZGZ ZGZ1,R2.NAME ZFRY2,R2.ZGZ ZGZ2,ZR.JGMC,W.ZFZT,W.STATE,W.SFLR,W.CKWP,W.BZ,W.XCBL,W.WXBL,W.CID,W.JAZT,");
        sql.append("W.IDCARD EMPLOYEECODE,W.ADDRESS ADDR,W.TELEPHONE TEL,");
        sql.append("W.CARTYPE MYTYPE,W.FDJID ENGINEID,W.CJID CHASSISID,W.YYID CCERTID,W.SSQY SHORTNAME ");
        sql.append(" FROM T_JC_WZJL W,(SELECT R.ID,R.NAME,R.ZGZ,Z.JGMC FROM T_JC_RY R,T_JC_ZFJG Z WHERE R.JGID=Z.ID) ZR,");
        sql.append("(SELECT R.ID,R.NAME,R.ZGZ FROM T_JC_RY R) R2 ");
        sql.append("  WHERE W.STATE = 1 AND ZR.ID(+)=W.ZFRY1 AND R2.ID(+) = W.ZFRY2  and w.id=?");
        Object[] value = new Object[]{wzjlid};
        return jdbcTemplate.queryForList(sql.toString(), value);
    }

    public int yjafdsrupd(List<Wzjlxw> list, YjaFdsr f) throws Exception {
        String sql2 = "UPDATE T_JC_WZJL_XW SET STATE = '0' WHERE AJH = ?";
        Object[] val2 = new Object[]{f.getWzid()};
        int nn = jdbcTemplate.update(sql2, val2);
        int m = list.size();
        for (int i = 0; i < m; i++) {
            Wzjlxw xw = list.get(i);
            String sql3 = "insert into T_JC_WZJL_XW(ajh,wzxwid,wfcdid,state) values(?,?,?,?)";
            Object[] values = new Object[]{xw.getAjh(), xw.getWzxwid(), xw.getWfcdid(), xw.getState()};
            jdbcTemplate.update(sql3, values);
        }

        String sql = "UPDATE T_JC_WZJL_YJA_FDSR SET SFZ=?,ADDRESS=?,LXDH=?,CYZGZ=?,FWZGZ=?,CPH=?,CX=?,FDJ=?,CJH=?,COMID=?,YYZJ=?,WZSJ=?,WZDD=?,WFTM=?,ZFRY=?,ZFPTAJH=?,FKJE=? WHERE ID = ?";
        Object[] val = new Object[]{f.getSfz(), f.getAddress(), f.getLxdh(), f.getCyzgz(), f.getFwzgz(), f.getCph(), f.getCx(), f.getFdj(), f.getCjh(), f.getComid(), f.getYyzj(), f.getWzsj(), f.getWzdd(), f.getWftm(), f.getZfry(), f.getZfptajh(), f.getFkje(), f.getId()};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public List<Map<String, Object>> yjadsrht(String wzjlid) {
        String sql = "SELECT w.zfptajh,w.fkje,s.fs ssfs,w.kf  FROM t_jc_wzjl w,t_kp_jsy_ss s WHERE w.idcard = s.sfz(+) and w.STATE ='1' AND w.id=?";

        Object[] value = new Object[]{wzjlid};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> yjafdsrjbxxck(String wzjlid) {
        String sql = "SELECT F.*,R1.NAME || ',' || R2.NAME ZFRY FROM t_jc_wzjl F,(SELECT R.ID,R.NAME FROM T_JC_RY R) R1,(SELECT R.ID,R.NAME FROM T_JC_RY R) R2 WHERE R1.ID(+)=F.ZFRY1 AND R2.ID(+) = F.ZFRY2 and F.STATE ='1' AND F.ID=?";
        Object[] value = new Object[]{wzjlid};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> searchsfzh(String sfzh) {
        String sql = "SELECT EMP.NAME,EMP.ADDR,EMP.TEL,EMP.PTIDCARD,CO.SHORTNAME,EMP.ID_ECERT,EMP.ECERTTYPE,EMP.ECERTGRANTDATE,EMP.WORKSTATE,EMP.HONESTCHKLVL,CP.VEHICLEID "
                + ",CP.MTYPE,CP.ENGINEID,CP.CHASSISID,CP.CCERTID FROM "
                + "T_BASE_EMP EMP LEFT JOIN "
                + "T_BASE_COM CO ON EMP.ID_OWNER = CO.ID_OWNER LEFT JOIN "
                + "T_BASE_VEHICLE CP ON CP.ID_VEHICLE = EMP.ID_VEHICLE "
                + "WHERE " + " PTIDCARD = ? AND CO.ID_OWNER NOT IN ("
                + SystemConstants.IDS + ")";
        Object[] val = new Object[]{sfzh};
        return jdbcTemplate.queryForList(sql.toString(), val);
    }

    public List<Map<String, Object>> searchsfzh_fdsr(String sfzh) {
        String sql = "SELECT EMP.NAME,EMP.ADDR,EMP.TEL,EMP.PTIDCARD,EMP.ID_ECERT,EMP.ECERTTYPE,EMP.ECERTGRANTDATE,com.shortname,"
                + "(select (" + SystemConstants.KHZF + "-kh.dqkf) khfs from t_jc_emp_ndkh kh where emp.PTIDCARD=kh.PTIDCARD and kh.year=?) khfs"
                + " FROM "
                + "T_BASE_EMP EMP left join t_base_com com on emp.id_owner=com.id_owner "
                + "WHERE " + " emp.PTIDCARD = ? and com.id_owner not in (" + SystemConstants.IDS + ")and com.state='1'";
        Calendar c = Calendar.getInstance();
        Object[] val = new Object[]{c.get(Calendar.YEAR), sfzh};
        return jdbcTemplate.queryForList(sql.toString(), val);
    }

    public List<Map<String, Object>> searchsfzhfs(String sfzh) {
        String sql = "select fs from t_kp_jsy_ss where sfz=?";
        Object[] val = new Object[]{sfzh};
        return jdbcTemplate.queryForList(sql.toString(), val);
    }

    public List<Map<String, Object>> searchcph(String cph) {
        String sql = "select ve.id_vehicle ,ve.id_owner,ve.mtype,ve.chassisid,ve.ENGINEID," +//CP.ENGINEID,CP.CHASSISID,CP.CCERTID
                "ve.fueltype,ve.ccertid,to_char(ve.firstdate,'yyyy-MM-dd') firstdate,ve.vehicleid,com.ownername,com.shortname " +
                " from t_base_vehicle ve left join t_base_com com on com.id_owner = ve.id_owner where com.state<>'0'" +
                " and ve.vehicleid= ? AND COM.ID_OWNER NOT IN (" + SystemConstants.IDS + ") and CCERTSTATE='10'";
        Object[] val = new Object[]{cph.toUpperCase()};
        System.out.println(sql);
        return jdbcTemplate.queryForList(sql.toString(), val);
    }

    public List<Map<String, Object>> zfry_combobox(String jgid) {
        String sql = "select id,name from t_jc_ry where state=? and  jgid in (select id  from t_jc_zfjg where state='1' connect by prior id = jgfid  start with id = ?)";
        Object[] value = new Object[]{SystemConstants.STATE_YX, jgid};
        return jdbcTemplate.queryForList(sql, value);
    }

    public int add_aj(List<Wzjlxw> list, Wzjl wzjl) throws Exception {
        wzjl.save();

        int m = list.size();

        for (int i = 0; i < m; i++) {
            Wzjlxw xw = list.get(i);
            String sql3 = "insert into T_JC_WZJL_XW(ajh,wzxwid,wfcdid,state) values(?,?,?,?)";
            Object[] values = new Object[]{xw.getAjh(), xw.getWzxwid(), xw.getWfcdid(), xw.getState()};
            jdbcTemplate.update(sql3, values);
        }
        return m;
    }

    public List<Map<String, Object>> ajclsfzhauto(String sfzh) throws Exception {
        String sql = "select ptidcard,ptidcard || '('|| NAME || ')' nameCard   from t_base_emp  where ptidcard like ? and workstate='1'";
        Object[] value = new Object[]{"%" + sfzh + "%"};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Wzjl> fdsrja(String ajh) throws Exception {
        String sql = "select * from t_jc_wzjl where state='1' and id=?";
        Object[] val = new Object[]{ajh};
        return jdbcTemplate.query(sql.toString(), val, new BeanPropertyRowMapper(Wzjl.class));
    }

    public void insertNdkh(EmpNdkh ndkh) throws Exception {
        ndkh.save();
    }

    public void udateNdkh(EmpNdkh ndkh) throws Exception {
        ndkh.update();
    }

    public List<Map<String, Object>> getNfkhBySfz(String sfzh, String year) throws Exception {
        String sql = "select * from t_jc_emp_ndkh where ptidcard=? and year=?";
        Object[] val = new Object[]{sfzh, year};
        return jdbcTemplate.queryForList(sql, val);
    }

    public List<Map<String, Object>> getgl(String glid) throws Exception {
        String sql = "select id wzjlid,ajh,idcard sfzhm,kf from t_jc_wzjl where state='1' and zfzt='2' and glwzid=?";
        Object[] val = new Object[]{glid};
        return jdbcTemplate.queryForList(sql, val);
    }

    public List<Map<String, Object>> getglaj(String id) throws Exception {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT W.ID,w.lrr,w.glwzid,to_char(w.lrsj,'yyyy-MM-dd HH:mm:ss') lrsj,W.AJH,W.WZRY,W.WZDD,W.CPH,W.WZSJ,ZR.NAME || ',' || R2.NAME ZFRY,ZR.NAME ZFRY1,ZR.ZGZ ZGZ1,R2.NAME ZFRY2,R2.ZGZ ZGZ2,ZR.JGMC,W.ZFZT,W.STATE,W.SFLR,W.CKWP,W.BZ,W.XCBL,W.WXBL,W.CID,W.JAZT,");
        sql.append("W.IDCARD EMPLOYEECODE,W.ADDRESS ADDR,W.TELEPHONE TEL,");
        sql.append("W.CARTYPE MYTYPE,W.FDJID ENGINEID,W.CJID CHASSISID,W.YYID CCERTID,W.SSQY SHORTNAME ");
        sql.append(" FROM T_JC_WZJL W,(SELECT R.ID,R.NAME,R.ZGZ,Z.JGMC FROM T_JC_RY R,T_JC_ZFJG Z WHERE R.JGID=Z.ID) ZR,");
        sql.append("(SELECT R.ID,R.NAME,R.ZGZ FROM T_JC_RY R) R2 ");
        sql.append("  WHERE W.STATE = 1 AND ZR.ID(+)=W.ZFRY1 AND R2.ID(+) = W.ZFRY2 ");
        sql.append(" and w.glwzid=?");
        Object[] val = new Object[]{id};
        return jdbcTemplate.queryForList(sql.toString(), val);
    }

    public boolean jskp(String id, String jsid) throws Exception {
        boolean result = false;
        String sql = "update t_jc_wzjl set cardstate='0',jsid=?,jstime=? where id=?";
        Object[] val = new Object[]{jsid, new Date(), id};
        int i = jdbcTemplate.update(sql, val);
        if (i > 0) {
            result = true;
        }
        return result;
    }
}
