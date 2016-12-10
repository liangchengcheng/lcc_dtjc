package com.lcc.taxi.dao;

import com.lcc.framework.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lcc on 2016/12/10.
 */
@Repository
public class AppDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 增加的服务
     */
    public int appgjAdd(String appbs, Double jd, Double wd, String user1, String user2) throws Exception {
        String sql = " INSERT INTO T_JC_APP_GJ(ID,APPBS,LNG,LAT, CJSJ,USER1,USER2) VALUES(?,?,?,?,SYSDATE,?,?)";
        String s = UUID.randomUUID().toString();
        s = s.substring(0,8)+ s.substring(9,13)+ s.substring(14, 18) + s.substring(19, 23);
        Object[] values = new Object[]{s, appbs, jd, wd,user1,user2};
        return  jdbcTemplate.update(sql,values);
    }

    /**
     * 根据app标识查询轨迹信息
     */
    public List<Map<String, Object>> queryGjByAppbs(Map parm) throws Exception{
        String sql = "select g.lon,g.lat,TO_CHAR(g.cjsj,'yyyy-MM-dd HH24-mi-ss') cjsj from t_jc_app_gj g where   g.sbbs=?  AND g.cjsj>=to_date(?,'yyyy-MM-dd HH24-mi-ss') AND g.cjsj<=to_date(?,'yyyy-MM-dd HH24-mi-ss') ORDER by g.cjsj ASC";
        LogUtil.getLogger().debug(sql);
        LogUtil.getLogger().debug(parm.toString());
        return jdbcTemplate.queryForList(sql,new Object[]{parm.get("appBs"), parm.get("kssj"),parm.get("jssj")});
    }

    public List getJglist(String jgid) throws Exception{
        String sql="select id  from t_jc_zfjg where state='1' ";
        if(jgid!=null&&!jgid.equals("")){
            sql="select id  from t_jc_zfjg where state='1' connect by prior id = jgfid  start with id = ?";
            Object[] values = new Object[]{jgid};
            return jdbcTemplate.queryForList(sql,values);
        }
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> dtjcqysl(String jgid) throws Exception{
        String sql="select to_char(count(*)) qysl from t_jc_dzwl where state='1' and gzzt=? "
                + " and zfjgid in (select id  from t_jc_zfjg where state='1' connect by prior id = jgfid  start with id = ?)";
        Object[] values = new Object[]{"1",jgid};
        return jdbcTemplate.queryForList(sql,values);
    }

    public List<Map<String, Object>> getWfjlxq(String date) throws Exception{
        StringBuffer sb=new StringBuffer();
        String sql1="select w.ajh,wzry,substr(w.WZSJ,6) wzsj,w.cph,w.wzdd,w.zfry1 || ',' || w.zfry2 zfry,wz.wznr from t_jc_wzjl w,t_jc_wzjl_xw x,t_jc_wzxw wz where w.ajh=x.ajh and x.wzxwid=wz.id AND w.state=? and x.state=? ";
        sb.append(sql1);
        sb.append(" AND substr(w.WZSJ,0,10) = ? ");
        sb.append(" order by w.ajh desc,w.wzsj desc ");
        Object[] values = new Object[]{"1","1",date};
        return jdbcTemplate.queryForList(sb.toString(),values);
    }

    public List<Map<String, Object>> sswfjlzs(String date) throws Exception{
        StringBuffer sb=new StringBuffer();
        String sql1="select to_char(count(*)) wfzs from t_jc_wzjl where state=? ";
        sb.append(sql1);
        sb.append(" AND substr(WZSJ,0,10) = ? ");
        sb.append(" order by wzsj desc ");
        Object[] values = new Object[]{"1",date};
        return jdbcTemplate.queryForList(sb.toString(),values);
    }

    public List<Map<String, Object>> kpsdsl(String date) throws Exception{
        StringBuffer sb=new StringBuffer();
        String sql1="select to_char(count(*)) kpsl from t_jc_wzjl where sfsd='1' and state=? ";
        sb.append(sql1);
        sb.append(" AND substr(WZSJ,0,10) = ? ");
        sb.append(" order by wzsj desc ");
        Object[] values = new Object[]{"1",date};
        return jdbcTemplate.queryForList(sb.toString(),values);
    }

    public List<Map<String, Object>> getKpsdxx(String date) throws Exception{
        StringBuffer sb=new StringBuffer();
        String sql1="select kpname,to_char(sdsj,'yyyy-mm-dd') sdsj,to_char(sdqx,'yyyy-mm-dd') sdqx from t_jc_wzjl  where sfsd='1' and state=? ";
        sb.append(sql1);
        sb.append(" AND substr(WZSJ,0,10) = ? ");
        sb.append(" order by wzsj desc ");
        Object[] values = new Object[]{"1",date};
        return jdbcTemplate.queryForList(sb.toString(),values);
    }

    public List<Map<String, Object>> getSblist()  throws Exception{
        String sql="select sbbm,sbbs from t_jc_dev where state<>'2'";
        return jdbcTemplate.queryForList(sql);
    }
}
