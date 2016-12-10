package com.lcc.taxi.dao;


import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.taxi.bean.Jcry;
import com.lcc.taxi.bean.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JcryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取自动填充sel的稽查人员信息
     */
    public List getUsersForAutoSel(Jcry jcry) throws Exception {
        String sql = "select * from t_jc_ry where name like ?";
        Vector values = new Vector();
        values.add("%" + jcry.getName().trim() + "%");
        return jdbcTemplate.query(sql, values.toArray(), new BeanPropertyRowMapper<Jcry>(Jcry.class));
    }

    /**
     * 修改稽查人员头像
     */
    public int addPortrait(Jcry jcry) throws Exception {
        String sql = "update t_jc_ry set txid=?";
        Vector values = new Vector();
        values.add(jcry.getTxid());
        if (jcry.getId() != null && jcry.getId().trim().length() > 0) {
            sql += " where id=?";
            values.add(jcry.getId());
        }
        return jdbcTemplate.update(sql, values.toArray());
    }

    /**
     * 添加jcry
     */
    public void addJcryDao(Jcry jcry, Log log) throws Exception {
        jcry.save();
        log.save();
    }

    public void addJcryDao(Jcry jcry) throws Exception {
        jcry.save();
    }

    /**
     * 依照id查询稽查人员信息
     */
    public List<Jcry> getJcryByIdDao(String id) throws Exception {
        String sql = "select * from t_jc_ry where id = ? and state <> '0'";
        Object[] value = new Object[]{id};
        List<Jcry> list = jdbcTemplate.query(sql, value, new BeanPropertyRowMapper<Jcry>(Jcry.class));
        return list;

    }

    /**
     * 依照账号查询稽查人员信息
     */
    public List<Jcry> getJcryByZHDao(String zh) throws Exception {
        String sql = "select * from t_jc_ry where zgz = ? and state <> '0'";
        Object[] value = new Object[]{zh};
        List<Jcry> list = jdbcTemplate.query(sql, value, new BeanPropertyRowMapper<Jcry>(Jcry.class));
        return list;

    }

    /**
     * 修改jcry
     */
    public void upJcryDao(Jcry jcry, Log log) throws Exception {
        jcry.update();
        log.save();
    }

    public void upJcryDao(Jcry jcry) throws Exception {
        jcry.update();
    }

    /**
     * 删除jcry
     */
    public int delJcryDao(String id, Log log) throws Exception {
        log.save();
        String sql = "update t_jc_ry set state = '0' where id = ?";
        Object[] values = new Object[]{id};
        int c = jdbcTemplate.update(sql, values);
        return c;
    }

    public int delJcryDao(String id) throws Exception {
        String sql = "update t_jc_ry set state = '0' where id = ?";
        Object[] values = new Object[]{id};
        int c = jdbcTemplate.update(sql, values);
        return c;
    }

    public int updPassword(String id, String oldpw, String newpw) throws Exception {
        String sql = "SELECT ID FROM T_JC_RY WHERE ID = ? AND PASSWD = ? AND STATE = 1 ";
        Object[] vlaues = new Object[]{id, oldpw};
        List list = jdbcTemplate.queryForList(sql, vlaues);
        if (list.size() == 0) {
            return 0;
        }
        String sql2 = "UPDATE T_JC_RY SET PASSWD = ? WHERE ID = ? AND STATE = 1 ";
        Object[] values2 = new Object[]{newpw, id};
        jdbcTemplate.update(sql2, values2);
        return 1;
    }

    public List checkname(String zh) throws Exception {
        String sql = "select * from t_jc_ry where zgz=? and state=?";
        Object[] values = new Object[]{zh, SystemConstants.STATE_YX};
        return jdbcTemplate.queryForList(sql, values);
    }

    public List checkname(String id, String zh) throws Exception {
        String sql = "select * from t_jc_ry where zgz=? and state=? and id<>?";
        Object[] values = new Object[]{zh, SystemConstants.STATE_YX, id};
        return jdbcTemplate.queryForList(sql, values);
    }

    public List checkzgz(String zgz) throws Exception {
        String sql = "select * from t_jc_ry where zgz=? and state=?";
        Object[] values = new Object[]{zgz, SystemConstants.STATE_YX};
        return jdbcTemplate.queryForList(sql, values);
    }

    /**
     * 查询角色combox
     */
    public List<Map<String, Object>> queryyhjsDao(String sysflag) {
        String sql = "SELECT * FROM T_BASE_ROLES WHERE SYSFLAG = ?";
        Object[] values = new Object[]{sysflag};
        return jdbcTemplate.queryForList(sql, values);
    }

    public int setResetPass(String id, String pass, Log log) {
        log.save();
        String sql = "UPDATE T_JC_RY SET PASSWD = ? WHERE ID = ? AND STATE = '1' ";
        Object[] value = new Object[]{pass, id};
        return jdbcTemplate.update(sql, value);
    }

    public List<Map<String, Object>> roleCom(String sysflag) {
        String sql = "select role_id,role_name from t_base_roles where sysflag=?";
        Object[] value = new Object[]{sysflag};
        return jdbcTemplate.queryForList(sql, value);
    }
}

