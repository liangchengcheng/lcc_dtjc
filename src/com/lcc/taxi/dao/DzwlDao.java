package com.lcc.taxi.dao;

import com.lcc.framework.util.LogUtil;
import com.lcc.taxi.bean.Dzwl;
import com.lcc.taxi.bean.Log;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DzwlDao {

    private static final Logger logger = Logger.getLogger(DzwlDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增电子围栏
     */
    public void addDzwl(Dzwl dzwl, Log log) throws Exception {
        log.save();
        LogUtil.getLogger().debug("addDzwl=" + dzwl);
        dzwl.save();
    }

    /**
     * 新增和修改dzwl前判断是否已存在
     */
    public int addUpDzwlCheck(Dzwl dzwl) throws Exception {
        String sql = "SELECT COUNT(*) from t_jc_dzwl WHERE state=1 and name =? AND ID<>?";
        return jdbcTemplate.queryForObject(sql, new Object[] { dzwl.getName(),dzwl.getId() }, Integer.class);
    }

    /**
     * 查询所有符合条件的电子围栏
     */
    public List<Dzwl> queryAllDzwl(Dzwl dzwl, String tk) throws Exception {
        String sql = "SELECT wl.* from T_JC_DZWL wl WHERE 1=1 ";
        List<Object> values = new ArrayList();
        if (dzwl.getId() != null) {
            sql += " and wl.id=?";
            values.add(dzwl.getId());
        }

        if (dzwl.getName() != null) {
            sql += " and wl.name=?";
            values.add(dzwl.getName());
        }

        if (dzwl.getState() != null) {
            sql += " and wl.state=?";
            values.add(dzwl.getState());
        }

        return jdbcTemplate.query(sql, values.toArray(), new BeanPropertyRowMapper(Dzwl.class));
    }

    /**
     * 删除一个电子围栏
     */
    public int delDzwl(String id, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_DZWL SET state=0 WHERE ID=?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * 更新电子围栏
     */
    public int upDzwl(Dzwl dzwl, Log log) throws Exception {
        log.save();
        StringBuffer sql = new StringBuffer("UPDATE t_jc_dzwl SET id=id");
        Vector values = new Vector();
        if (dzwl.getName() != null && dzwl.getName().trim().length() > 0) {
            sql.append(" ,name =?");
            values.add(dzwl.getName());
        }
        if (dzwl.getLatlon() != null && dzwl.getLatlon().trim().length() > 0) {
            sql.append(" ,latlon=?,lat=?,lon=?");
            values.add(dzwl.getLatlon());
            values.add(dzwl.getLat());
            values.add(dzwl.getLon());
        }
        if (dzwl.getGzzt() != null) {
            sql.append(" ,gzzt=?");
            values.add(dzwl.getGzzt());
        }
        if (dzwl.getXswz() != null) {
            sql.append(" ,xswz=?");
            values.add(dzwl.getXswz());
        }

        sql.append(" where id=?");
        values.add(dzwl.getId());
        System.out.println(values);
        return jdbcTemplate.update(sql.toString(), values.toArray());
    }

    public int upDzwl(Dzwl dzwl) throws Exception {
        StringBuffer sql = new StringBuffer("UPDATE t_jc_dzwl SET id=id");
        Vector values = new Vector();
        if (dzwl.getName() != null && dzwl.getName().trim().length() > 0) {
            sql.append(" ,name =?");
            values.add(dzwl.getName());
        }
        if (dzwl.getLatlon() != null && dzwl.getLatlon().trim().length() > 0) {
            sql.append(" ,latlon=?,lat=?,lon=?");
            values.add(dzwl.getLatlon());
            values.add(dzwl.getLat());
            values.add(dzwl.getLon());
        }
        if (dzwl.getGzzt() != null) {
            sql.append(" ,gzzt=?");
            values.add(dzwl.getGzzt());
        }
        if (dzwl.getXswz() != null) {
            sql.append(" ,xswz=?");
            values.add(dzwl.getXswz());
        }
        sql.append(" where id=?");
        values.add(dzwl.getId());
        System.out.println(values);
        return jdbcTemplate.update(sql.toString(), values.toArray());
    }
}
