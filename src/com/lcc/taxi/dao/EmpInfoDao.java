package com.lcc.taxi.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lcc.framework.constants.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

@Repository
public class EmpInfoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public HashMap<String, Object> queryempinfo(String sfzh) throws Exception {
        String sql = "SELECT EMP.NAME,EMP.PTIDCARD,CO.SHORTNAME,EMP.ID_ECERT,EMP.ECERTTYPE,EMP.ECERTGRANTDATE,EMP.WORKSTATE,EMP.HONESTCHKLVL,CP.VEHICLEID,EXT.KPSFJH,TO_CHAR (EXT.FKSJ, 'yyyy-MM-dd') FKSJ,TO_CHAR (EXT.FWZGZYXQ, 'yyyy-MM-dd') FWZGZYXQ"
                + ",CP.MTYPE,CP.ENGINEID,CP.CHASSISID,hmd.jryy,hmd.jrsj FROM "
                + "T_BASE_EMP EMP LEFT JOIN "
                + "T_BASE_COM CO ON EMP.ID_OWNER = CO.ID_OWNER LEFT JOIN "
                + "T_BASE_VEHICLE CP ON CP.ID_VEHICLE = EMP.ID_VEHICLE LEFT JOIN T_FK_BASE_EMP_EXT EXT ON EMP.ID_EMPLOYEE = EXT.ID_EMPLOYEE  left join (select h.sfzh,h.jryy,h.jrsj from t_jc_emp_hmd h where h.state='1') hmd  on emp.ptidcard=hmd.sfzh "
                + "WHERE " + "PTIDCARD = ? AND CO.ID_OWNER NOT IN ("
                + SystemConstants.IDS + ")";
        Object[] val = new Object[]{sfzh};
        // 查询考核分值(正负？？？？？)
        List res = jdbcTemplate.queryForList(sql, val);
        sql = "select dqkf KHFZ from t_jc_emp_ndkh where ptidcard=? and year=?";
        Calendar cc = Calendar.getInstance();
        val = new Object[]
                {sfzh, cc.get(Calendar.YEAR)};
        List<Map<String, Object>> reskf = jdbcTemplate.queryForList(sql, val);
        if (res.size() > 0) {
            HashMap<String, Object> newmap = new HashMap<>();
            newmap = (HashMap<String, Object>) res.get(0);
            Set set = newmap.keySet();
            Iterator it = set.iterator();
            String str = "";
            while (it.hasNext()) {
                str = it.next().toString();
                if (newmap.get(str) == null) {
                    newmap.put(str, "");
                }
            }
            if (reskf == null || reskf.size() == 0) {
                newmap.put("KHFZ", SystemConstants.KHZF);
            } else {
                double khfz = Double.valueOf(reskf.get(0).get("KHFZ") == null ? "0" : reskf.get(0).get("KHFZ").toString());
                double khzf = Double.valueOf(SystemConstants.KHZF);
                newmap.put("KHFZ", khzf < khfz ? 0 : (khzf - khfz));
            }
            newmap.put("picpath", SystemConstants.PIC);
            return newmap;
        } else {
            return null;
        }
    }

    public HashMap jsempinfo(String sfzh) throws Exception {
        String sql = "SELECT EMP.NAME,EMP.ADDR,EMP.TEL,EMP.PTIDCARD,CO.SHORTNAME,EMP.ID_ECERT,EMP.ECERTTYPE,EMP.ECERTGRANTDATE,EMP.WORKSTATE,EMP.HONESTCHKLVL,CP.VEHICLEID "
                + ",CP.MTYPE,CP.ENGINEID,CP.CHASSISID,CP.CCERTID FROM "
                + "T_BASE_EMP EMP LEFT JOIN "
                + "T_BASE_COM CO ON EMP.ID_OWNER = CO.ID_OWNER LEFT JOIN "
                + "T_BASE_VEHICLE CP ON CP.ID_VEHICLE = EMP.ID_VEHICLE "
                + "WHERE " + " PTIDCARD = ? AND CO.ID_OWNER NOT IN ("
                + SystemConstants.IDS + ")";
        Object[] val = new Object[]{sfzh};
        //查询考核分值(正负？？？？？)
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, val);
        HashMap<String, Object> hashmap = new HashMap<>();
        if (res != null && res.size() != 0) {
            Map<String, Object> map = res.get(0);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() == null) {
                    hashmap.put(entry.getKey(), "");
                } else {
                    hashmap.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return hashmap;
    }

    public void getPic(String lasttime) throws Exception {
        final LobHandler lobHandler = new DefaultLobHandler();
        String sql = "select ptidcard,pic_emp from t_base_emp where lasttime>=to_date('" + lasttime + "','yyyy-MM-dd HH24:mi:ss' )";
        jdbcTemplate.query(sql, new AbstractLobStreamingResultSetExtractor() {
            protected void streamData(ResultSet rs) throws SQLException, IOException, DataAccessException {
                do {
                    String name = rs.getString("ptidcard");
                    //Blob pic=rs.getBlob("pic_emp");
                    byte pic[] = lobHandler.getBlobAsBytes(rs, "pic_emp");
                    File file = new File(SystemConstants.PROJECT_HOME + File.separator + "pic" + File.separator + name + ".jpg");
                    FileOutputStream out = new FileOutputStream(file);
                    if (pic != null) {
                        out.write(pic);
                    }
                } while (rs.next());
            }
        });
    }

    public HashMap<String, Object> gtfs(String sfz) throws Exception {
        String sql = "select emp.workstate,(select KH.dqkf  from t_jc_emp_ndkh KH where KH.ptidcard=EMP.PTIDCARD and KH.year=? ) KHFZ FROM T_BASE_EMP EMP WHERE EMP.PTIDCARD=?";
        Calendar c = Calendar.getInstance();
        Object[] val = new Object[]{c.get(Calendar.YEAR), sfz};
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql, val);
        HashMap<String, Object> map = new HashMap<>();
        if (res != null && res.size() != 0) {
            Map<String, Object> m = res.get(0);
            map.put("workstate", m.get("WORKSTATE"));
            map.put("khfz", m.get("KHFZ"));
        }
        return map;
    }

    public List<Map<String, Object>> gtickh(String sfz) throws Exception {
        String sql = "SELECT ext.ICKH from T_BASE_EMP EMP LEFT JOIN t_fk_base_emp_ext ext on EMP.ID_EMPLOYEE=ext.ID_EMPLOYEE WHERE emp.ptidcard=?";
        Object[] val = new Object[]{sfz};
        return jdbcTemplate.queryForList(sql, val);
    }
}
