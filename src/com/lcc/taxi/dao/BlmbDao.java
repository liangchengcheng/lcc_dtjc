package com.lcc.taxi.dao;

import java.util.List;
import java.util.Map;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.taxi.bean.Blmb;
import com.lcc.taxi.bean.Blwt;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Wtmb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BlmbDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void blwtAdd(Blwt blwt, Log log) throws Exception {
        blwt.save();
        log.save();
    }

    public void blwtUpd(String wtnr, String id, Log log) throws Exception {
        log.save();
        String sql = "update t_jc_blwt set wtnr = ? where id = ?";
        Object[] val = new Object[]{wtnr, id};
        jdbcTemplate.update(sql, val);
    }

    public int blwtDel(String id, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_BLWT SET STATE = '0' WHERE ID = ?";
        Object[] val = new Object[]{id};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public List<Map<String, Object>> blwt_combobox() {
        String sql = "select id,wtnr from t_jc_blwt where state=?";
        Object[] value = new Object[]{SystemConstants.STATE_YX};
        return jdbcTemplate.queryForList(sql, value);
    }


    public void blmbAdd(Blmb blmb, List<Wtmb> list, Log log) throws Exception {
        log.save();
        blmb.save();
        int n = list.size();
        for (int i = 0; i < n; i++) {
            Wtmb wtmb = list.get(i);
            String sql = "insert into T_JC_WT_MB(mbid,wtid,state,sx) values(?,?,?,?)";
            Object[] values = new Object[]{blmb.getId(), wtmb.getWtid(), wtmb.getState(), wtmb.getSx()};
            jdbcTemplate.update(sql, values);
        }
    }

    public List<Map<String, Object>> boxupd(String id) {
        String sql = "select mbid,wtid,sx from T_JC_WT_MB where state=? and mbid=? order by sx";
        Object[] value = new Object[]{"1", id};
        return jdbcTemplate.queryForList(sql, value);
    }

    public void blmbUpd(String id, String mbmc, List<Wtmb> list, String radioid) throws Exception {
        String sql2 = "UPDATE T_JC_WT_MB SET STATE = '0' WHERE MBID = ?";
        Object[] val2 = new Object[]{id};
        int nn = jdbcTemplate.update(sql2, val2);

        String sql = "update t_jc_blmb set mbmc =?,type=? where id =?";
        Object[] val = new Object[]{mbmc, radioid, id};
        jdbcTemplate.update(sql, val);


        int n = list.size();
        for (int i = 0; i < n; i++) {
            Wtmb wtmb = list.get(i);
            String sql3 = "insert into T_JC_WT_MB(mbid,wtid,state,sx) values(?,?,?,?)";
            Object[] values = new Object[]{id, wtmb.getWtid(), wtmb.getState(), wtmb.getSx()};
            jdbcTemplate.update(sql3, values);
        }
    }

    public int blmbDel(String id, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_BLMB SET STATE = '0' WHERE ID = ?";
        Object[] val = new Object[]{id};
        int n = jdbcTemplate.update(sql, val);

        String sql2 = "UPDATE T_JC_WT_MB SET STATE = '0' WHERE MBID = ?";
        Object[] val2 = new Object[]{id};
        int nn = jdbcTemplate.update(sql2, val2);

        return n;
    }

    public int szmrlx(String id, String type) throws Exception {

        String sql1 = "UPDATE T_JC_BLMB SET STATE = '1' WHERE TYPE = ? AND STATE <> '0' ";
        Object[] val1 = new Object[]{type};
        int n1 = jdbcTemplate.update(sql1, val1);

        return n1;
    }

    public int szmrlx2(String id, String type, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_BLMB SET STATE = '2' WHERE ID = ?";
        Object[] val = new Object[]{id};
        int n = jdbcTemplate.update(sql, val);

        return n;
    }

    public List checkMb(String mbmc, String type, String id) throws Exception {
        String sql = "select id from t_jc_blmb where mbmc=? and type=? and state<>'0' ";
        Object[] val = new Object[]{mbmc, type};
        if (id != null && !id.equals("")) {
            sql = "select id from t_jc_blmb where mbmc=? and type=? and state<>'0' and id<>?";
            val = new Object[]{mbmc, type, id};
        }
        return jdbcTemplate.queryForList(sql, val);
    }

    public List mbmccz(String mbmc) throws Exception {
        String sql = "select mbmc from t_jc_blmb where mbmc=? and state<>'0' ";
        Object[] val = new Object[]{mbmc};
        return jdbcTemplate.queryForList(sql, val);
    }

    public List blwtnr(String wtnr) throws Exception {
        String sql = "select wtnr from t_jc_blwt where wtnr=? and state='1' ";
        Object[] val = new Object[]{wtnr};
        return jdbcTemplate.queryForList(sql, val);
    }

    public List checkWt(String id, String wtnr) throws Exception {
        String sql = "select id from t_jc_blwt where wtnr=? and state='1' ";
        Object[] val = new Object[]{wtnr};
        if (id != null && !id.equals("")) {
            sql = "select id from t_jc_blwt where wtnr=? and state='1' and id <> ? ";
            val = new Object[]{wtnr, id};
        }
        return jdbcTemplate.queryForList(sql, val);
    }
}
