package com.lcc.taxi.dao;

import java.util.List;
import java.util.Map;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.taxi.bean.Clda;
import com.lcc.taxi.bean.Clxx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CldaDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据车牌号得到车辆档案信息
     */
    public List<Clda> getCldaInfo(String cphm) throws Exception {
        String sql = "select ve.id_vehicle,ve.id_owner,ve.mtype,ve.chassisid,ve.engineid,hmd.jryy,to_char(hmd.jrsj,'yyyy-MM-dd') jrsj, " +
                "ve.fueltype,ve.ccertid,to_char(ve.firstdate,'yyyy-MM-dd') firstdate,ve.vehicleid,com.ownername,com.shortname " +
                " from t_base_vehicle ve left join t_base_com com on com.id_owner = ve.id_owner left join (select h.cph,h.jryy,h.jrsj from t_jc_cl_hmd h where h.state='1') hmd  on ve.vehicleid=hmd.cph where com.state<>'0'" +
                " and ve.vehicleid= ? AND COM.ID_OWNER NOT IN (" + SystemConstants.IDS + ") and CCERTSTATE='10'";
        Object[] value = new Object[]{cphm};
        List<Clda> list = jdbcTemplate.query(sql, value, new BeanPropertyRowMapper<Clda>(Clda.class));
        return list;
    }

    public List<Clxx> getCldaInfoServiceCLJS(String cphm) throws Exception {
        String sql = "select ve.mtype,ve.chassisid,ve.ENGINEID," +
                "ve.ccertid,ve.vehicleid,com.shortname " +
                " from t_base_vehicle ve left join t_base_com com on com.id_owner = ve.id_owner where com.state<>'0'" +
                " and ve.vehicleid= ? AND COM.ID_OWNER NOT IN (" + SystemConstants.IDS + ") and CCERTSTATE='10'";
        Object[] value = new Object[]{cphm};
        List<Clxx> list = jdbcTemplate.query(sql, value, new BeanPropertyRowMapper<Clxx>(Clxx.class));
        return list;
    }

    public List<Map<String, Object>> getNameofEmp(Integer id_vehicle) throws Exception {
        String sql = "select emp.name,emp.ptidcard,hmd.jryy,hmd.jrsj from t_base_emp emp left join (select h.sfzh,h.jryy,h.jrsj from t_jc_emp_hmd h where h.state='1') hmd  on emp.ptidcard=hmd.sfzh  where id_vehicle = ?";
        Object[] value = new Object[]{id_vehicle};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, value);
        return list;
    }

    public List<Map<String, Object>> queryDbsj(Integer id_vehicle) throws Exception {
        String sql = "select to_char(d.SIGN_TIME,'yyyy-MM-dd HH:mm:ss') SIGNTIME,d.EMPLOYEEID PTIDCARD,e.NAME from T_BASE_DEV D INNER JOIN T_BASE_EMP E ON D.EMPLOYEEID=E.EMPLOYEECODE WHERE D.SIGN_STAT='0' AND d.id_vehicle=?";
        return jdbcTemplate.queryForList(sql, id_vehicle);
    }
}
