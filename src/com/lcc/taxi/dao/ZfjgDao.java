package com.lcc.taxi.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.TJcZfjg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ZfjgDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String zfjgAdd(TJcZfjg zfjg, Log log) throws Exception {
        log.save();
        zfjg.save();
        return zfjg.getId();
    }

    public void zfjgUpd(TJcZfjg zfjg, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_ZFJG SET JGMC = ?, ADDR = ?, XGSJ = ?,CZYID=? WHERE ID = ? ";
        Object[] value = new Object[]{zfjg.getJgmc(), zfjg.getAddr(), zfjg.getXgsj(), zfjg.getCzyid(), zfjg.getId()};
        jdbcTemplate.update(sql, value);
    }

    public void zfjgDel(TJcZfjg zfjg, Log log) throws Exception {
        zfjg.delete();
        log.save();
    }

    public List zfjgMCCheck(String jgmc, String id) throws Exception {
        String sql = "SELECT JGMC FROM T_JC_ZFJG  WHERE JGMC = ? AND STATE='1'";
        Object[] value = new Object[]{jgmc};
        if (id != null && !"".equals(id)) {
            sql = "SELECT JGMC FROM T_JC_ZFJG  WHERE JGMC = ? AND STATE='1' and id<>?";
            value = new Object[]{jgmc, id};
        }

        List list = jdbcTemplate.queryForList(sql, value);
        return list;
    }

    public List<Map<String, Object>> zfjgCom(String jgid) throws Exception {
        String sql = "select id,jgmc  from t_jc_zfjg where state=? connect by prior id = jgfid  start with id = ?";
        Object[] value = new Object[]{SystemConstants.STATE_YX, jgid};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> zfjgCom0() throws Exception {
        String sql = "select id,jgmc from t_jc_zfjg where state=?";
        Object[] value = new Object[]{SystemConstants.STATE_YX};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List checkJG(String id) throws Exception {
        String sql = "select id from t_jc_zfjg where jgfid=? and state=?";
        Object[] value = new Object[]{id, SystemConstants.STATE_YX};
        return jdbcTemplate.queryForList(sql, value);
    }


    public List<Map<String, Object>> findAll(String jgid) throws Exception {
        String sql = "select id,jgmc,jgfid,to_char(cjsj,'yyyy-MM-dd HH24:mi:ss') cjsj from t_jc_zfjg where state='1' and id in (select id  from t_jc_zfjg where state='1' connect by prior id = jgfid  start with id = '" + jgid + "') order by cjsj desc";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> findAllC(String jgid) throws Exception {
        String sql = "select id,jgmc text,jgfid from t_jc_zfjg where state='1' and id in (select id  from t_jc_zfjg where state='1' connect by prior id = jgfid  start with id = '" + jgid + "') order by cjsj desc";
        return jdbcTemplate.queryForList(sql);
    }

    public void jcryDel(String id) throws Exception {
        String sql = "update t_jc_ry set state=? where jgid=?";
        Object[] value = new Object[]{SystemConstants.STATE_WX, id};
        jdbcTemplate.update(sql, value);
    }

    public int updateJgid(String jgid, String zgz) throws Exception {
        String sql = "update t_jc_ry set jgid=? where zgz=? and state=?";
        Object[] value = new Object[]{jgid, zgz, SystemConstants.STATE_YX};
        int result = jdbcTemplate.update(sql, value);
        return result;
    }

}