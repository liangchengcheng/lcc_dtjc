package com.lcc.taxi.dao;

import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Wfcd;
import com.lcc.taxi.bean.Wzxw;
import com.lcc.taxi.bean.Wzxwbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WzxwDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void wzxwAdd(Wzxw wzxw, List<Wfcd> list, List<Wzxwbl> listwfxw, Log log) throws Exception {
        wzxw.save();
        log.save();
        int n = list.size();
        for (int i = 0; i < n; i++) {
            Wfcd wfcd = list.get(i);
            String sql = "insert into T_JC_WZXW_WFCD(wfxfid,wfcdid,cfbz,hg,state) values(?,?,?,?,?)";
            Object[] value = new Object[]{wzxw.getId(), wfcd.getWfcdid(), wfcd.getCfbz(), wfcd.getHg(), wfcd.getState()};
            jdbcTemplate.update(sql, value);
        }
        int m = listwfxw.size();
        for (int j = 0; j < m; j++) {
            Wzxwbl wzxwbl = listwfxw.get(j);
            String sqls = "insert into t_jc_wzxw_bl(wfxwid,wtnr,type,state) values(?,?,?,?)";
            Object[] values = new Object[]{wzxw.getId(), wzxwbl.getWtnr(), wzxwbl.getType(), wzxwbl.getState()};
            jdbcTemplate.update(sqls, values);
        }
    }

    public void wzxwUpd(Wzxw wzxw, List<Wfcd> list, List<Wzxwbl> listwfxw, Log log) throws Exception {
        log.save();
        String sql = "update t_jc_wzxw set wznr = ?,wgyj=?,kf=? where id = ?";
        Object[] val = new Object[]{wzxw.getWznr(), wzxw.getWgyj(), wzxw.getKf(), wzxw.getId()};
        jdbcTemplate.update(sql, val);

        String sql2 = "update t_jc_wzxw_wfcd set state = '0' where wfxfid = ?";
        Object[] val2 = new Object[]{wzxw.getId()};
        jdbcTemplate.update(sql2, val2);

        int n = list.size();
        for (int i = 0; i < n; i++) {
            Wfcd wfcd = list.get(i);
            String sql3 = "insert into T_JC_WZXW_WFCD(wfxfid,wfcdid,cfbz,hg,state) values(?,?,?,?,?)";
            Object[] value3 = new Object[]{wzxw.getId(), wfcd.getWfcdid(), wfcd.getCfbz(), wfcd.getHg(), wfcd.getState()};
            jdbcTemplate.update(sql3, value3);
        }

        String sql4 = "update t_jc_wzxw_bl set state = '0' where wfxwid = ?";
        Object[] val4 = new Object[]{wzxw.getId()};
        jdbcTemplate.update(sql4, val4);
    }

    public int wzxwDel(String id, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_WZXW SET STATE = '0' WHERE ID = ?";
        Object[] val = new Object[]{id};
        int n = jdbcTemplate.update(sql, val);

        String sql2 = "UPDATE T_JC_WZXW_WFCD SET STATE = '0' WHERE WFXFID = ?";
        Object[] val2 = new Object[]{id};
        int n2 = jdbcTemplate.update(sql2, val2);

        String sql3 = "UPDATE t_jc_wzxw_bl SET STATE = '0' WHERE WFXWID = ?";
        Object[] val3 = new Object[]{id};
        int n3 = jdbcTemplate.update(sql3, val3);
        return n;
    }

    public List wzxwlist() throws Exception {
        String sql = "select wznr from T_JC_WZXW where state = 1";
        return jdbcTemplate.queryForList(sql);
    }

    public List wtlist(String mbid) {
        String sql = "select id,wtnr from t_jc_blwt t,t_jc_wt_mb m where t.id=m.wtid and t.state='1' and m.mbid=?";
        Object[] val = new Object[]{mbid};
        return jdbcTemplate.queryForList(sql, val);
    }

    public List<Map<String, Object>> wzxwcdlist() {
        String sql = "select id,wfcd from t_jc_wfcd ";
        Object[] value = new Object[]{};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> wzxwcdlist_upd(String id) {
        String sql = "select w.wfxfid,w.wfcdid,w.cfbz,w.hg,w.state,c.wfcd from t_jc_wzxw_wfcd w,t_jc_wfcd c where w.state='1' and c.id=w.wfcdid and w.wfxfid=?";
        Object[] value = new Object[]{id};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> wzxwwtqdlx(String id) {
        String sql = "select type from t_jc_wzxw_bl where state='1' and wfxwid=?";
        Object[] value = new Object[]{id};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List wtlistApp(String type) {
        String sql = "select wtnr from t_jc_blwt t,t_jc_wt_mb m,t_jc_blmb mb where t.id=m.wtid and m.mbid=mb.id and t.state='1' and mb.state='2' and m.state='1' and mb.type=? order by m.sx";
        Object[] val = new Object[]{type};
        return jdbcTemplate.queryForList(sql, val);
    }

    public List wzxwlistApp() throws Exception {
        String sql = "select XW.id,XW.wznr,XW.wgyj,CD.CFBZ,CD.HG,wf.wfcd,wf.id CDID from T_JC_WZXW XW,T_JC_WZXW_WFCD CD,T_JC_WFCD WF where XW.state = '1'  AND CD.STATE='1' AND XW.ID=CD.WFXFID AND CD.WFCDID=WF.ID ORDER BY XW.WZNR,WF.ID";
        return jdbcTemplate.queryForList(sql);
    }

    public List checkWzxw(Wzxw wzxw) throws Exception {
        String sql = "select id from t_jc_wzxw where state='1' and wznr =?";
        Object[] val = new Object[]{wzxw.getWznr().trim()};
        if (wzxw.getId() != null) {
            sql = "select id from t_jc_wzxw where state='1' and wznr =? and id<>?";
            val = new Object[]{wzxw.getWznr().trim(), wzxw.getId().trim()};
        }

        return jdbcTemplate.queryForList(sql, val);
    }

}