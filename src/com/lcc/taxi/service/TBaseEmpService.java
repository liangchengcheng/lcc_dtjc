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
public class TBaseEmpService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> getJsyDaxyById(int id, int year) {
        String sql = "select KHRWND.KHZT, emp.name,emp.ptsex, to_char(emp.birthday,'yyyy-mm-dd hh24:mi:ss') birthday, emp.ptidcard ,"
                + "emp.folk, emp.tel, emp.addr,emp.diploma, emp.ecerttype, emp.id_ecert,to_char(emp.firstdatee,'yyyy-mm-dd') firstdatee, "
                + "to_char(emp.ecertgrantdate,'yyyy-mm-dd hh24:mi:ss') ecertgrantdate,to_char(emp.certendate,'yyyy-mm-dd') certendate, "
                + "emp.grantorgan, emp.drliencestat, emp.driverlicense, emp.drivertype, "
                + "to_char(emp.drliencedate,'yyyy-mm-dd') drliencedate, co.ownername,jg.khdj,jg.df from T_KP_EMP emp , T_BASE_COM co,T_KP_ND_JSYKHJG jg, T_KP_KHRWB_ND KHRWND where emp.id_employee=? and emp.year = ? "
                + "and emp.year = jg.year AND KHRWND.YEAR = emp.year  and emp.id_owner = co.id_owner(+) and to_char(emp.id_employee) = jg.jsyid(+) and co.id_onwer NOT IN (" + SystemConstants.IDS + ")";
        Object[] values = new Object[]{id, year};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, values);
        if (list.size() > 0) {
            Map<String, Object> m = list.get(0);
            return m;
        } else {
            String sql2 = "select KHRWND.KHZT,emp.name,emp.ptsex, to_char(emp.birthday,'yyyy-mm-dd hh24:mi:ss') birthday, emp.ptidcard , "
                    + "emp.folk, emp.tel, emp.addr,emp.diploma, emp.ecerttype, emp.id_ecert,to_char(emp.firstdatee, 'yyyy-mm-dd') firstdatee, "
                    + "to_char(emp.ecertgrantdate,'yyyy-mm-dd hh24:mi:ss') ecertgrantdate,to_char(emp.certendate,'yyyy-mm-dd') certendate, "
                    + "emp.grantorgan, emp.drliencestat, emp.driverlicense, emp.drivertype, "
                    + "to_char(emp.drliencedate,'yyyy-mm-dd') drliencedate, co.ownername,jg.khdj,jg.df "
                    + "from T_BASE_EMP emp , T_BASE_COM co,(select jsyid, khdj, df from T_KP_ND_JSYKHJG where jsyid = ? and year = ? ) jg ,(select KHZT from T_KP_KHRWB_ND  where year =? AND STATE = 1)  KHRWND where emp.id_employee=? "
                    + "and emp.id_owner = co.id_owner(+) and to_char(emp.id_employee) = jg.jsyid(+) and co.id_onwer NOT IN (" + SystemConstants.IDS + ")";
            Object[] val = new Object[]{id, year, year, id};
            list = jdbcTemplate.queryForList(sql2, val);
            Map<String, Object> m = list.get(0);
            return m;
        }
    }

    /**
     * 根据驾驶员身份证号和年查询不良行为信息
     */
    public List<Map<String, Object>> jsyblxwlist(String year, String jsysfzh) {
        String sql = "select id,cxyy,jsyxm,cxdd, year, month from T_KP_QYYD_AQYY where  state = '" + SystemConstants.STATE_YX + "' and year = ? and jsysfz = ? ORDER BY cxsj";
        Object[] val = new Object[]{year, jsysfzh};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, val);
        return list;
    }

    /**
     * 根据id查询驾驶员不良行为信息详细
     */
    public List<Map<String, Object>> jsyblxwxx(String id) {
        String sql = "select JSYXM, JSYSFZ, CPH, BXR, to_char(BASJ,'yyyy-mm-dd hh24:mi:ss') basj, to_char(CXSJ, 'yyyy-mm-dd hh24:mi:ss') cxsj ,"
                + " to_char(LARQ,'yyyy-mm-dd hh24:mi:ss') larq, SSRS, SWRS, ZRB, BAR, SFJA, CXDD, CXYY, XLDM, YEAR, MONTH from T_KP_QYYD_AQYY where id = ? ";
        Object[] val = new Object[]{id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, val);
        return list;
    }
}
