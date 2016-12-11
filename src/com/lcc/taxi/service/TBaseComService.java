package com.lcc.taxi.service;

import java.util.List;
import java.util.Map;

import com.lcc.framework.constants.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TBaseComService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getQydaYear(String qyid) {
        String sql = "select year from t_kp_khrwb_nd where state= " + SystemConstants.STATE_YX + " order by year desc";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    /**
     * 按照id查询企业信息
     */
    public Map<String, Object> getQyxyById(String id, String year) {
        String sql = "select KHRWND.KHZT, KHRWND.SYZBBB,KHRWND.SYZBBBMC , COM.FAX, COM.OWNERADDR, COM.COMPID,COM.OWNERNAME, COM.LEGALP,COM.DEALPRINCIPAL,COM.PTTEL,COM.ECONTYPE,"
                + "COM.MGRAREA,COM.MCERTWORD,COM.MCERTID,to_char(COM.FIRSTDATE,'yyyy-mm-dd') FIRSTDATE,to_char(COM.EXPIREDATE,'yyyy-mm-dd') EXPIREDATE,"
                + "COM.CHECKGRANTORGAN,to_char(COM.CHECKGRANTDATE,'yyyy-mm-dd') CHECKGRANTDATE,"
                + "COM.REGCAPITAL,KHJG.TZHDF,KHJG.ZZPJ from T_KP_COM COM,T_KP_QY_KHJG KHJG, T_KP_KHRWB_ND KHRWND WHERE COM.ID_OWNER=KHJG.QYID AND KHJG.QYID=? AND KHJG.YEAR=? AND COM.YEAR = KHRWND.YEAR AND KHRWND.YEAR = KHJG.YEAR(+) AND KHRWND.STATE = 1 and com.state = '" + SystemConstants.STATE_YX + "'";
        Object[] value = new Object[]{id, year};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, value);
        if (list.size() == 0) {
            String sql2 = "select KHZT, KHRWND.SYZBBB,KHRWND.SYZBBBMC , FAX, OWNERNAME,OWNERADDR,LEGALP,DEALPRINCIPAL,PTTEL,ECONTYPE,COMPID,"
                    + "MGRAREA,MCERTWORD,MCERTID,to_char(FIRSTDATE,'yyyy-mm-dd') FIRSTDATE,to_char(EXPIREDATE,'yyyy-mm-dd') EXPIREDATE,"
                    + "CHECKGRANTORGAN,to_char(CHECKGRANTDATE,'yyyy-mm-dd') CHECKGRANTDATE,"
                    + "REGCAPITAL from t_base_com com ,(select KHZT, SYZBBB,SYZBBBMC  from T_KP_KHRWB_ND  where year =? AND STATE = 1) KHRWND where com.id_owner =? and com.id_owner NOT IN (" + SystemConstants.IDS + ") and com.state = '" + SystemConstants.STATE_YX + "'";
            Object[] val = new Object[]{year, id};
            List<Map<String, Object>> lis = jdbcTemplate.queryForList(sql2, val);
            Map<String, Object> m = lis.get(0);
            m.put("SFGD", "否");
            return m;
        } else {
            Map<String, Object> ma = list.get(0);
            ma.put("SFGD", "是");
            return ma;
        }
    }

    /**
     * 查询所有的企业
     */
    public List<Map<String, Object>> quqyinfo(String q) {
        String sql = "select id_owner,ownername from t_base_com where id_owner NOT IN (" + SystemConstants.IDS + ")  and state = '" + SystemConstants.STATE_YX + "' and ownername like '%" + q + "%'";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

}
