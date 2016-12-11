package com.lcc.taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Wzjl;
import com.lcc.taxi.dao.WzjlDao;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WzjlService {

    @Autowired
    private WzjlDao wzjldao;

    @Autowired
    private JdbcTemplate jdbctemplate;

    /**
     * 得到车辆违章信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Map<String, Object>> getWzjlInfoService(String sfz, String cp, String kst, String jst)
            throws Exception {
        List<Map<String, Object>> list = wzjldao.getWzjlInfo(sfz, cp, kst, jst);
        return list;
    }

    /**
     * 保存违章信息
     */
    public boolean saveWzjlService(String wzry, String wzdd, String zfry, String cph, String wzsj) throws Exception {
        Wzjl wzjl = new Wzjl();
        wzjl.setCph(cph);
        wzjl.setState("1");
        wzjl.setWzdd(wzdd);
        wzjl.setWzry(wzry);
        // wzjl.setZfry(zfry);
        Date s = new SimpleDateFormat("yyyy-MM-dd").parse(wzsj);
        // wzjl.setWzsj(s);
        wzjldao.saveWzjlDao(wzjl);
        return true;
    }

    /**
     * 保存信息
     */
    public void saveWzjlInfo(Wzjl wzjl) throws Exception {
        wzjl.save();
        /*
		 * String sql = " update t_jc_wzjl set state = '0' where id <> ? ";
		 * Object[] value = new Object[] { wzjl.getId() };
		 * jdbctemplate.update(sql, value);
		 */
    }

    /**
     * 根据id获得版本信息
     */
    public List<Wzjl> getWzjlById(String id) throws Exception {
        String sql = "select t.id,t.wzry,t.cph,t.wzsj,t.wzdd,t.zfry,t.state from t_jc_wzjl t where id = ? ";
        Object[] value = new Object[]{id};
        List<Wzjl> list = jdbctemplate.query(sql, value, new BeanPropertyRowMapper<Wzjl>(Wzjl.class));
        return list;
    }

    /**
     * 更新信息
     */
    public void updateWzjlInfo(Wzjl wzjl) throws Exception {
        String sql = "update t_jc_wzjl set wzry = ? , cph = ? , wzsj = ? , wzdd = ? , zfry = ? where id = ?";
        Object[] value = new Object[]{wzjl.getWzry(), wzjl.getCph(), wzjl.getWzsj(), wzjl.getWzdd(), wzjl.getId()};
        jdbctemplate.update(sql, value);
    }

    /**
     * 删除
     */
    public void deleteWzjlInfo(String id) throws Exception {
        String sql = "DELETE FROM  T_JC_WZJL  where id = ?";
        Object[] val = new Object[]{id};
        jdbctemplate.update(sql, val);
    }

    public boolean saveWzApp(Wzjl wz, JSONArray wfxw) throws Exception {
        boolean result = true;
        wzjldao.saveWzApp(wz);
        int count = 0;
        int c = wfxw.size();
        for (int i = 0; i < c; i++) {
            JSONObject js = JSONObject.fromObject(wfxw.getString(i));
            count += wzjldao.saveWfxwApp(wz.getAjh(), js.getString("wfxw"), js.getString("wfcdid"));
        }
        if (count != c) {
            result = false;
        }
        return result;
    }

    // /**
    // * 根据id获得违章记录详情
    // *
    // * @param id
    // * @return
    // */
    // public List<Wzjl> getWzjlxq(String id) throws Exception {
    // String sql = "select t.data from t_jc_wzjl t where id = ? and state='1'";
    // Object[] value = new Object[] { id };
    // List<Wzjl> list = jdbctemplate.query(sql, value, new
    // BeanPropertyRowMapper<Wzjl>(Wzjl.class));
    // return list;
    // }
    public List<Map<String, Object>> getTs(String cp, String sfz, String kst, String jst) throws Exception {
        StringBuffer sql = new StringBuffer(
                "select cph,zynr,qyname,to_char(ldsj,'yyyy-MM-dd HH24:mi:ss') tssj from t_kp_qyyd_tsmx where 1=1 ");
        List<Object> args = new ArrayList<>();
        if (cp != null && !cp.equals("")) {
            sql.append(" and cph like ?");
            args.add("%" + cp.trim().toUpperCase().replace("鲁", "") + "");
        }
        // if(sfz!=null&&sfz.equals("")){
        // sql.append(" and sfz =?");
        // args.add(cp.trim());
        // }
        if (kst != null && !kst.equals("")) {
            sql.append(" and ldsj >=to_date('" + kst + "','yyyy-MM-dd HH24:mi:ss')");
        }
        if (jst != null && !jst.equals("")) {
            sql.append(" and ldsj <=to_date('" + jst + "','yyyy-MM-dd HH24:mi:ss')");
        }
        sql.append(" order by ldsj desc");
        return jdbctemplate.queryForList(sql.toString(), args.toArray());
    }
}
