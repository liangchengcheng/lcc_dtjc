package com.lcc.taxi.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.lcc.framework.constants.SystemConstants;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GpsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 周边车辆
     */
    public List<Map<String, Object>> getZbcl(Double minlat, Double maxlat, Double minlng, Double maxlng) throws Exception {
        String sql = "select t.VEHICLEID,(SELECT C.SHORTNAME FROM T_BASE_VEHICLE V inner JOIN T_BASE_COM C ON V.ID_OWNER=C.ID_OWNER WHERE V.VEHICLEID=T.VEHICLEID and c.state='1' AND C.ID_OWNER NOT IN (" + SystemConstants.IDS + ")) COM,(select e.NAME from T_BASE_DEV D INNER JOIN T_BASE_EMP E ON D.EMPLOYEEID=E.EMPLOYEECODE WHERE D.SIGN_STAT='0' AND d.VEHICLEID=t.VEHICLEID and e.workstate='1') NAME,t.DEVALARMFLAG,t.DEVRUNSTATE,t.LATITUDE,t.LONGITUDE,t.ORIENTATION,t.SPEED,to_char(t.GPSTIME,'yyyy-MM-dd HH:mm:ss') GPSTIME from gps.t_gps_last t where t.LONGITUDE >=? and t.LONGITUDE<=? and t.LATITUDE>=? and t.LATITUDE<=?";
        Object[] value = new Object[]{minlng, maxlng, minlat, maxlat};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, value);
        return list;
    }

    /**
     * 实时轨迹
     */
    public List<Map<String, Object>> ssgj(String cp) throws Exception {
        //(select e.NAME from T_BASE_DEV D INNER JOIN T_BASE_EMP E ON D.EMPLOYEEID=E.EMPLOYEECODE WHERE D.SIGN_STAT='0' AND d.VEHICLEID=t.VEHICLEID) NAME,
        String sql = "select t.VEHICLEID,DEV.EMPLOYEEID SFZ,EP.NAME NAME,(SELECT C.SHORTNAME FROM T_BASE_VEHICLE V LEFT JOIN T_BASE_COM C ON V.ID_OWNER=C.ID_OWNER WHERE V.VEHICLEID=T.VEHICLEID and c.state='1' AND C.ID_OWNER NOT IN (" + SystemConstants.IDS + ")) COM,t.DEVALARMFLAG,t.DEVRUNSTATE,t.LATITUDE,t.LONGITUDE,t.ORIENTATION,t.SPEED,to_char(t.GPSTIME,'yyyy-MM-dd HH:mm:ss') GPSTIME from gps.t_gps_last t LEFT JOIN T_BASE_DEV DEV ON T.VEHICLEID=DEV.VEHICLEID  LEFT JOIN T_BASE_EMP EP ON DEV.EMPLOYEEID=EP.EMPLOYEECODE where  ep.workstate='1' and t.VEHICLEID=? ";
        Object[] value = new Object[]{cp};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, value);
        return list;
    }

    /**
     * 实时轨迹全部缓存
     */
    public List<Map<String, Object>> ssgjAll() throws Exception {
        String sql = "select t.VEHICLEID,(SELECT C.SHORTNAME FROM T_BASE_VEHICLE V inner JOIN T_BASE_COM C ON V.ID_OWNER=C.ID_OWNER WHERE V.VEHICLEID=T.VEHICLEID and c.state='1' AND C.ID_OWNER NOT IN (" + SystemConstants.IDS + ")) COM,(select e.NAME from T_BASE_DEV D INNER JOIN T_BASE_EMP E ON D.EMPLOYEEID=E.EMPLOYEECODE WHERE D.SIGN_STAT='0' AND d.VEHICLEID=t.VEHICLEID and e.workstate='1') NAME,t.DEVALARMFLAG,t.DEVRUNSTATE,t.LATITUDE,t.LONGITUDE,t.ORIENTATION,t.SPEED,to_char(t.GPSTIME,'yyyy-MM-dd HH:mm:ss') GPSTIME from gps.t_gps_last t ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    /**
     * 位置汇报
     */
    public int wzhb(String lat, String lon, String cl, String di) throws Exception {
        String[] cls = cl.split(",");
        String sql = "";
        Object[] value = null;
        if (cls.length > 1) {
            sql = "insert into t_jc_app_gj (id,lat,lon,sbbs,czyid1,czyid2,cjsj) values (?,?,?,?,?,?,SYSDATE)";
            value = new Object[]{UUID.randomUUID().toString().replaceAll("-", ""), lat, lon, di, cls[0], cls[1]};
        } else {
            sql = "insert into t_jc_app_gj (id,lat,lon,sbbs,czyid1,cjsj) values (?,?,?,?,?,SYSDATE)";
            value = new Object[]{UUID.randomUUID().toString().replaceAll("-", ""), lat, lon, di, cls[0]};
        }
        return jdbcTemplate.update(sql, value);
    }

    /**
     * 获取配置信息
     */
    public List getPz(String cv) throws Exception {
        String sql = "select * from t_jc_pzxx where version>?";
        Object[] value = new Object[]{cv};
        return jdbcTemplate.queryForList(sql, value);
    }

    /**
     * 获取历史轨迹
     */
    public List<Map<String, Object>> gettrack(String cp, String kst, String jst) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMdd");
        cp = cp.trim().toUpperCase();
        //gps.暂时这个用户下
        String sql = "select sd,jd,wd,fx,bjbz,yxzt,zt,sj from gps.t_gps_" + sdff.format(sdf.parse(kst)) + " where  cph='" + cp + "' and sj<=to_date('" + jst + "','yyyy-MM-dd HH24:mi:ss' ) and sj>=to_date('" + kst + "','yyyy-MM-dd HH24:mi:ss' ) order by sj";
        if (!(kst.substring(0, 10).equals(jst.substring(0, 10)))) {
            sql = "select sd,jd,wd,fx,bjbz,yxzt,zt from (select sd,jd,wd,fx,bjbz,yxzt,zt,sj from gps.t_gps_" + sdff.format(sdf.parse(kst)) + " t1 where  t1.cph='" + cp + "' and t1.sj>=to_date('" + kst + "','YYYY-MM-DD HH24:MI:SS' ) " +
                    " Union All " +
                    " select sd,jd,wd,fx,bjbz,yxzt,zt,sj from gps.t_gps_" + sdff.format(sdf.parse(jst)) + " t2 where cph='" + cp + "' and sj<=to_date('" + jst + "','YYYY-MM-DD HH24:MI:SS' ))t  order by sj";
            //sql="select sd,jd,wd,fx,bjbz,yxzt,zt,sj from gps.t_gps_"+sdff.format(sdf.parse(jst))+" where  cph='"+cp+"' and sj>=to_date('"+kst+"','yyyy-MM-dd HH24:mi:ss' ) ";
            //sql+=" union  select sd,jd,wd,fx,bjbz,yxzt,zt,sj from gps.t_gps_"+sdff.format(sdf.parse(jst))+" where cph='"+cp+"' and sj<=to_date('"+jst+"','yyyy-MM-dd HH24:mi:ss' ) order by sj";
        }
        return jdbcTemplate.queryForList(sql);
    }
}
