package com.lcc.taxi.dao;

import java.util.List;
import java.util.Map;
import com.lcc.framework.constants.UserConstants;
import com.lcc.taxi.bean.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class YhqxDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询父节点
     * PRIVILEGE_ID,PRIVILEGE_NAME,PRIVILEGE_PARENT,LEAF_NOD,PRIVILEGE,SYSFLAG
     */
    public List<Map<String, Object>> queryroot() throws Exception {
        String sql = "SELECT * FROM T_BASE_PRIVILEGES WHERE SYSFLAG='" + UserConstants.SYSTEMID + "' order by privilege";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    /**
     * 添加权限表
     */
    public int addqxjsDao(int role_id, int privilege, String visible, String enable, String sysfalg) throws Exception {
        String sql = "INSERT INTO T_BASE_ROLEPRIV (ROLE_ID,PRIVILEGE,PRIVILEGE_VISIBLE,PRIVILEGE_ENABLED,SYSFLAG)VALUES(?,?,?,?,?)";
        Object[] values = new Object[]{role_id, privilege, visible, enable, sysfalg};
        int c = jdbcTemplate.update(sql, values);
        return c;
    }

    public void log(Log log) {
        log.save();
    }

    /**
     * 查询父节点
     * PRIVILEGE_ID,PRIVILEGE_NAME,PRIVILEGE_PARENT,LEAF_NOD,PRIVILEGE,SYSFLAG
     */
    public List<Map<String, Object>> queryqx(int privilegeid) throws Exception {
        String sql = "SELECT * FROM T_BASE_PRIVILEGES WHERE PRIVILEGE_PARENT=?";
        Object[] value = new Object[]{privilegeid};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, value);
        return list;
    }


    /**
     * 删除角色
     */
    public int deljsDao(int id, Log log) throws Exception {
        log.save();
        String sql = "delete from T_BASE_ROLES where role_id=?";
        Object[] values = new Object[]{id};
        int c = jdbcTemplate.update(sql, values);
        return c;
    }

    /**
     * 删除角色权限对照表
     */
    public int delqxDao(int id) {
        String sql = "delete from T_BASE_ROLEPRIV where role_id=?";
        Object[] values = new Object[]{id};
        int c = jdbcTemplate.update(sql, values);
        return c;
    }

    /**
     * 添加角色
     */
    public int addjsDao(int role_id, String role_name, String role_desc, String sysflag, Log log) {
        log.save();
        String sql = "INSERT INTO T_BASE_ROLES (ROLE_ID,ROLE_NAME,ROLE_DESC,SYSFLAG,ID_OWNER)VALUES(?,?,?,?,?)";
        Object[] values = new Object[]{role_id, role_name, role_desc, sysflag, "0"};
        int c = jdbcTemplate.update(sql, values);
        return c;
    }

    /**
     * 按ID查询
     */
    public List getjsByIdDao(int role_id) {
        String sql = "SELECT * FROM T_BASE_ROLES WHERE ROLE_ID = ?";
        Object[] value = new Object[]{role_id};
        List list = jdbcTemplate.queryForList(sql, value);
        return list;
    }

    /**
     * 按ID查询
     */
    public int queryjs(int role_id) {
        String sql = "SELECT count(*) FROM T_BASE_ROLES WHERE ROLE_ID = ?";
        Object[] value = new Object[]{role_id};
        int r = jdbcTemplate.queryForObject(sql, value, Integer.class);
        return r;
    }

    /**
     * 更新
     */
    public int updatejs(int role_id, String role_desc, String role_name, Log log) {
        log.save();
        String sql = "update T_BASE_ROLES  set ROLE_NAME=?,ROLE_DESC=? where ROLE_ID = ?";
        Object[] values = new Object[]{role_name, role_desc, role_id};
        int c = jdbcTemplate.update(sql, values);
        return c;
    }

    /**
     * 查询角色节点
     */
    public List<Map<String, Object>> queryqxjs(int role_id) {
        String sql = "SELECT PRIVILEGE FROM T_BASE_ROLEPRIV WHERE ROLE_ID = ?";
        Object[] values = new Object[]{role_id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, values);
        return list;
    }

    /**
     * 查询角色节点
     */
    public List<Map<String, Object>> queryqxByPri(int privilege) {
        System.out.println(privilege);
        String sql = "SELECT PRIVILEGE_ID FROM T_BASE_PRIVILEGES WHERE PRIVILEGE =? and sysflag='" + UserConstants.SYSTEMID + "'";
        Object[] values = new Object[]{privilege};
        List<Map<String, Object>> i = jdbcTemplate.queryForList(sql, values);
        return i;
    }

}
