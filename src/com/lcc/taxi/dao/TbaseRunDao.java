package com.lcc.taxi.dao;


import java.util.List;
import java.util.Map;

import com.lcc.framework.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TbaseRunDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 得到车辆运营档案信息
     */
    public List<Map<String, Object>> getRunInfo(String cphm, String begintime, String endtime) {
        String sql = "select run.cphm,run.sjdm,to_char(run.scsj,'yyyy-MM-dd HH24:mi:ss') scsj,to_char(run.xcsj,'yyyy-MM-dd HH24:mi:ss') xcsj,run.dhsj,run.fjf,"
                + "run.yylc,run.yyje,run.jylx,(select EMP.name from T_BASE_EMP emp where EMP.EMPLOYEECODE=run.SJDM) name,(select EMP.ID_ECERT from T_BASE_EMP emp where EMP.EMPLOYEECODE=run.SJDM) ID_ECERT"
                + " from t_busi_run run  where cphm = ? "
                + "and scsj >= to_date(? ,'yyyy-MM-dd HH24:mi:ss' ) "
                + "and xcsj<= to_date(? ,'yyyy-MM-dd HH24:mi:ss' )";
        LogUtil.getLogger().info("sql" + sql);
        Object[] value = new Object[]{cphm, begintime, endtime};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, value);
        return list;
    }

    public List<Map<String, Object>> getQd(String cphm, String begintime,
                                           String endtime) throws Exception {
        String sql = "select c.SIGNIN_LONGITUDE lon,c.SIGNIN_LATITUDE lat,to_char(c.SIGNIN_TIME,'yyyy-MM-dd HH24:mi:ss') sj,(select EMP.name from T_BASE_EMP emp where EMP.EMPLOYEECODE=c.EMPLOYEECODE) name,(select EMP.ID_ECERT from T_BASE_EMP emp where EMP.EMPLOYEECODE=c.EMPLOYEECODE) ID_ECERT from t_busi_drisign c "
                + "  where c.VEHICLEID=?  and c.SIGNIN_TIME>=to_date(? ,'yyyy-MM-dd HH24:mi:ss' ) and c.SIGNIN_TIME<=to_date(? ,'yyyy-MM-dd HH24:mi:ss' )";
        Object[] value = new Object[]{cphm, begintime, endtime};
        return jdbcTemplate.queryForList(sql, value);
    }

    public List<Map<String, Object>> getQt(String cphm, String begintime,
                                           String endtime) throws Exception {
        //String sql="select r.name,r.ID_ECERT cr,c.SIGNIN_LONGITUDE lon,c.SIGNIN_LATITUDE lat,to_char(c.SIGNIN_TIME,'yyyy-MM-dd HH24:mi:ss') qdsj,to_char(c.SIGNOUT_TIME,'yyyy-MM-dd HH24:mi:ss') qtsj,c.ZYYLC yylc,c.ZXSLC xslc,c.RUNNUMS nm,c.SIGNOUT_LONGITUDE lon,c.SIGNOUT_LATITUDE lat from t_busi_drisign c inner join"
        //+ " t_base_emp r on c.EMPLOYEECODE=r.PTIDCARD where c.VEHICLEID=? and c.SIGNTYPE='1' and c.SIGNIN_TIME>=to_date(? ,'yyyy-MM-dd HH24:mi:ss' ) and c.SIGNIN_TIME<=to_date(? ,'yyyy-MM-dd HH24:mi:ss' )";
        String sql = "select c.SIGNIN_LONGITUDE lon,c.DBZJE,c.SIGNIN_LATITUDE lat,to_char(c.SIGNIN_TIME,'yyyy-MM-dd HH24:mi:ss') qdsj,to_char(c.SIGNOUT_TIME,'yyyy-MM-dd HH24:mi:ss') qtsj,c.DBYYLC yylc,c.DBDISTANCE xslc,c.DBCOUNT nm,c.SIGNOUT_LONGITUDE lon,c.SIGNOUT_LATITUDE lat,c.DBTIME,(select EMP.name from T_BASE_EMP emp where EMP.EMPLOYEECODE=c.EMPLOYEECODE) name,(select EMP.ID_ECERT from T_BASE_EMP emp where EMP.EMPLOYEECODE=c.EMPLOYEECODE) ID_ECERT"
                + " from t_busi_drisign c   where c.VEHICLEID=? and c.SIGNTYPE='1' and c.SIGNIN_TIME>=to_date(? ,'yyyy-MM-dd HH24:mi:ss' ) and c.SIGNIN_TIME<=to_date(? ,'yyyy-MM-dd HH24:mi:ss' )";
        Object[] value = new Object[]{cphm, begintime, endtime};
        return jdbcTemplate.queryForList(sql, value);
    }
}