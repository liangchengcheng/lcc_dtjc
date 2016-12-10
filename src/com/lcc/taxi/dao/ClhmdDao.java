package com.lcc.taxi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Clhmd;
import com.lcc.taxi.bean.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class ClhmdDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Clhmd> clhmdCph(String cph) {

        String sql = "SELECT cph from t_jc_cl_hmd where state ='1' and cph=?";  //zt 1是没有取消，0是取消
        List<Object> values = new ArrayList();
        values.add(cph);
        return jdbcTemplate.query(sql, values.toArray(), new BeanPropertyRowMapper(Clhmd.class));
    }

    public void clhmdAdd(Clhmd clhmd, Log log) {
        clhmd.save();
        log.save();
    }

    public int clhmdDel(String id, Log log) {
        log.save();
        String sql = "UPDATE T_JC_CL_HMD SET STATE = '3',LASTTIME=? WHERE ID = ?";
        Object[] val = new Object[]{new Date(), id};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public void clhmdqx(Clhmd clhmd, Log log) {
        log.save();

        String sql = "UPDATE T_JC_CL_HMD SET QXR=?,QXSJ=?,QXSM =?,STATE=?,LASTTIME=? WHERE ID = ?";
        Object[] val = new Object[]{clhmd.getQxr(), clhmd.getQxsj(), clhmd.getQxsm(), clhmd.getState(), clhmd.getLasttime(), clhmd.getId()};
        int n = jdbcTemplate.update(sql, val);
    }

    public int clhmdupd(Clhmd clhmd, Log log) {
        log.save();

        String sql = "UPDATE T_JC_CL_HMD SET XGR=?,XGSJ=?,JRYY =?,LASTTIME=? WHERE ID = ?";
        Object[] val = new Object[]{clhmd.getXgr(), clhmd.getXgsj(), clhmd.getJryy(), clhmd.getLasttime(), clhmd.getId()};
        int n = jdbcTemplate.update(sql, val);

        return n;
    }

    public List<Map<String, Object>> clhmdxx(String sj) throws Exception {
        String sql = "select * from t_jc_cl_hmd where state='1'";
        Object[] val = new Object[]{1};
        if (sj != null && !sj.equals("")) {
            sql = "select * from t_jc_cl_hmd where state='1' and lasttime>=to_date(?,'yyyy-MM-dd HH24-mi-ss')";
            val = new Object[]{sj};
            List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, val);
            return res;
        }
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
        return res;
    }

    public List<Map<String, Object>> clhmdcphauto(String cph) throws Exception {
        String sql = "select vehicleid from t_base_vehicle where vehicleid like ?";
        Object[] value = new Object[]{"%" + cph + "%"};
        return jdbcTemplate.queryForList(sql, value);
    }

    public boolean checkch(String cph) throws Exception {
        boolean result = false;
        String sql = "select count(id_vehicle) from t_base_vehicle where vehicleid= ? and ccertstate='10'";
        int count = jdbcTemplate.queryForObject(sql,new Object[] { cph }, Integer.class);
        if (count == 1) {
            result = true;
        }
        return result;
    }
}
