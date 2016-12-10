package com.lcc.taxi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.lcc.taxi.bean.Appxx;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Tjcdev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by lcc on 2016/12/10.
 */
@Repository
public class AppxxDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 校验app注册信息是否重复
     */
    public HashMap<String, Object> appzcCheck(Tjcdev appzc) throws Exception {
        HashMap<String, Object> m = new HashMap<String, Object>();

        StringBuffer sql = new StringBuffer("SELECT ID FROM T_JC_DEV WHERE SBBM = ? AND STATE<>'2' ");
        Object[] val = new Object[]{appzc.getSbbm()};

        StringBuffer sql2 = new StringBuffer("SELECT ID FROM T_JC_DEV WHERE SBBS = ?  AND STATE<>'2' ");
        Object[] val2 = new Object[]{appzc.getSbbs()};

        if (!"".equals(appzc.getId()) && appzc.getId() != null) {
            sql.append("  AND ID <> ? ");
            sql2.append("  AND ID <> ? ");
            val = new Object[]{appzc.getSbbm(), appzc.getId()};
            val2 = new Object[]{appzc.getSbbs(), appzc.getId()};
        }

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString(), val);
        List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql2.toString(), val2);

        m.put("sbbm", list);
        m.put("sbbs", list2);
        return m;
    }

    /**
     * 增加app注册信息
     */
    public void appzcAdd(Tjcdev appzc, Log log) throws Exception {
        appzc.save();
        log.save();
    }

    /**
     * 修改app注册信息
     */
    public void appzcUpd(Tjcdev appzc, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_DEV  SET SBBM = ?, SBBS = ?, GXSJ = ? WHERE ID = ?";
        Object[] values = new Object[]{appzc.getSbbm(), appzc.getSbbs(), appzc.getGxsj(), appzc.getId()};
        jdbcTemplate.update(sql, values);
    }

    /**
     * 删除app注册信息
     */
    public int appzcDel(String id, Log log) throws Exception {
        log.save();
        String sql = "UPDATE T_JC_DEV  SET STATE = '2' WHERE ID = ?";
        Object[] values = new Object[]{id};
        int n = jdbcTemplate.update(sql, values);
        return n;
    }

    public int bbmsDel(String id) throws Exception {
        String sql = "UPDATE T_JC_APPXX  SET STATE = '0' WHERE ID = ?";
        Object[] values = new Object[]{id};
        return jdbcTemplate.update(sql, values);
    }

    public void bbmsAdd(Appxx appxx) throws Exception {
        appxx.save();
    }

    public void bbmsUpd(Appxx appxx) throws Exception {
        appxx.update();
    }

    public int cz(String id, String sbbm) throws Exception {
        String sql = "UPDATE T_JC_DEV SET state=1,sbbm=? WHERE ID=?";
        Object[] values = new Object[]{sbbm, id};
        return jdbcTemplate.update(sql, values);
    }

    public List getSBBSForAutoSel(Tjcdev appzc) throws Exception {
        String sql = "select sbbm,sbbs from t_jc_dev where sbbm like ? and state=1";
        Vector values = new Vector();
        values.add("%" + appzc.getSbbm().trim() + "%");
        return jdbcTemplate.query(sql, values.toArray(), new BeanPropertyRowMapper<Tjcdev>(Tjcdev.class));
    }

    public List yxgjcz(String kssj, String jssj, String sbbm) throws Exception {
        String sql = "select t.cz,to_char(t.cjsj,'yyyy-mm-dd HH24:mi:ss') czsj,r.name from t_jc_logapp t,t_jc_dev d,t_jc_ry r where t.sbbs = d.sbbs(+) and t.czyid1=r.id(+) and d.sbbm =? and d.state=1 and t.cjsj>=to_date(?,'yyyy-MM-dd HH24-mi-ss') and t.cjsj<=to_date(?,'yyyy-MM-dd HH24-mi-ss')";
        Object[] values = new Object[]{sbbm, kssj, jssj};
        return jdbcTemplate.queryForList(sql, values);
    }
}
