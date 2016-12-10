package com.lcc.taxi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Sjhmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SjhmdDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Sjhmd> sjhmdSfz(String sfzh) {
        String sql = "SELECT sfzh from t_jc_emp_hmd where state ='1'  and sfzh=?";  //zt 1是没有取消，0是取消
        List<Object> values = new ArrayList();
        values.add(sfzh);
        return jdbcTemplate.query(sql, values.toArray(), new BeanPropertyRowMapper(Sjhmd.class));
    }

    public void sjhmdAdd(Sjhmd sjhmd, Log log) {
        sjhmd.save();
        log.save();
    }

    public int sjhmdDel(String id, Log log) {
        log.save();
        String sql = "UPDATE T_JC_EMP_HMD SET STATE = '3',LASTTIME=? WHERE ID = ?";
        Object[] val = new Object[]{new Date(), id};
        int n = jdbcTemplate.update(sql, val);
        return n;
    }

    public void sjhmdqx(Sjhmd sjhmd, Log log) {
        log.save();

        String sql = "UPDATE T_JC_EMP_HMD SET QXR=?,QXSJ=?,QXSM =?,STATE=?,LASTTIME=? WHERE ID = ?";
        Object[] val = new Object[]{sjhmd.getQxr(), sjhmd.getQxsj(), sjhmd.getQxsm(), sjhmd.getState(), sjhmd.getLasttime(), sjhmd.getId()};
        int n = jdbcTemplate.update(sql, val);
    }

    public int sjhmdupd(Sjhmd sjhmd, Log log) {
        log.save();

        String sql = "UPDATE T_JC_EMP_HMD SET XGR=?,XGSJ=?,JRYY =?,LASTTIME=? WHERE ID = ?";
        Object[] val = new Object[]{sjhmd.getXgr(), sjhmd.getXgsj(), sjhmd.getJryy(), sjhmd.getLasttime(), sjhmd.getId()};
        int n = jdbcTemplate.update(sql, val);

        return n;
    }

    public List<Map<String, Object>> sjhmdxx(String sj) throws Exception {
        String sql = "select * from t_jc_emp_hmd where state='1'";
        Object[] val = new Object[]{1};
        System.out.println("sj" + sj.equals("") + "**" + sj);
        if (sj != null && !sj.isEmpty()) {

            sql = "select * from t_jc_emp_hmd where state='1' and lasttime>=to_date(?,'yyyy-MM-dd HH24-mi-ss')";
            val = new Object[]{sj};
            List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, val);
            return res;
        }
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
        return res;

    }

    public List<Map<String, Object>> sjhmdname(String xm) throws Exception {
        String sql = "select name || '('|| ptidcard || ')' nameCard from t_base_emp where name like ? and workstate='1' ";
        Object[] value = new Object[]{"%" + xm + "%"};
        return jdbcTemplate.queryForList(sql, value);
    }

    public int checkSfz(String sjxm, String sfzhm) throws Exception {
        int result = 0;
        String sql = "select count(id_employee) from t_base_emp where name= ? and workstate='1'";
        int count = jdbcTemplate.queryForObject(sql,new Object[] { sjxm }, Integer.class);
        if (count > 0) {
            result++;
        }
        sql = "select count(id_employee) from t_base_emp where ptidcard= ? and workstate='1'";
        count = jdbcTemplate.queryForObject(sql,new Object[] { sfzhm }, Integer.class);
        if (count > 0) {
            result++;
        }
        return result;
    }
}

