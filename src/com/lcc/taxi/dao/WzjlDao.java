package com.lcc.taxi.dao;

import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Wzjl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WzjlDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 得到车辆违章信息
     */
    public List<Map<String, Object>> getWzjlInfo(String sfz, String cp, String kst, String jst) throws Exception {
        //String sql = " select id,ajh,wzry,wzdd,cph,to_char(wzsj,'yyyy-MM-dd HH24:mi:ss') wzsj,zfry1,zfry2,zfzt,data,xcbl,wxbl from t_jc_wzjl "
        String sql = " select jl.ajh,substr(jl.wzsj,0,10) wzsj,jl.zfzt,jl.fkje,jl.kf,w.wznr,cd.wfcd from t_jc_wzjl jl right join t_jc_wzjl_xw  xw on jl.ajh=xw.ajh left join t_jc_wzxw w on xw.wzxwid=w.id left join t_jc_wfcd cd on xw.wfcdid=cd.id where jl.zfzt＝'2' and xw.state='1' ";

        if (cp != null && !"".equals(cp)) {
            sql += " and jl.cph like '%" + cp.trim() + "%' ";
        }
        if (sfz != null && !"".equals(sfz)) {
            sql += " and jl.idcard = '" + sfz.trim() + "' ";
        }
        if (kst != null && !"".equals(kst)) {
            sql += " and jl.wzsj >='" + kst + "'";
        }
        if (jst != null && !"".equals(jst)) {
            sql += "and jl.wzsj <= '" + jst + "'";
        }

        sql += "order by jl.ajh desc,jl.wzsj desc";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;

    }

    public boolean saveWzjlDao(Wzjl wzjl) throws Exception {
        wzjl.save();
        return true;
    }

    public void saveWzApp(Wzjl wz) throws Exception {
        wz.save();
    }

    public int saveWfxwApp(String ajh, String wfxw, String wfcd) throws Exception {
        String sql = "insert into t_jc_wzjl_xw (ajh,wzxwid,wfcdid,state) values (?,?,?,'1')";
        Object[] value = new Object[]{ajh, wfxw, wfcd};
        return jdbcTemplate.update(sql, value);
    }
}
