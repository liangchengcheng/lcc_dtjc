package com.lcc.taxi.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.lcc.framework.bean.HeaderBean;
import com.lcc.taxi.bean.Jctp;
import com.lcc.taxi.bean.LogApp;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HibernateTemplate hTemplate;

    /**
     * 保存标识
     */
    public void saveBs(String bs) throws Exception {
        String sql = "insert into t_jc_dev (id,sbbm,sbbs,cjsj,state) values (?,?,?,?,?)";
        Object[] params = new Object[]{UUID.randomUUID().toString().replaceAll("-", ""), bs, bs, new Date(), 0};
        jdbcTemplate.update(sql, params);
    }

    /**
     * 验证设备
     */
    public List<Map<String, Object>> checksb(String di) throws Exception {
        String sql = "select id,state from t_jc_dev WHERE  SBBS=? and state<>'2'";
        Object[] params = new Object[]{di};
        return jdbcTemplate.queryForList(sql, params);
    }

    /**
     * 验证用户
     */
    public List checkyh(String tk) throws Exception {
        String sql = "select id from t_jc_ry where token=? and state=?";
        Object[] params = new Object[]{tk, 1};
        return jdbcTemplate.queryForList(sql, params);
    }

    /**
     * 登录
     */
    public List<Map<String, Object>> login(String un, String pw, Double ts) throws Exception {
        // TODO Auto-generated method stub
        String sql = "select ry.*,jg.jgmc BM,jg.id BH from t_jc_ry ry left join t_jc_zfjg jg on ry.jgid=jg.id where ry.zgz=? and ry.state=1 and ry.type like '%2'";
        Object[] params = new Object[]{un};
        return jdbcTemplate.queryForList(sql, params);
    }

    /**
     * 保存日志对象
     */
    public void saveLog(LogApp log) throws Exception {
        hTemplate.save(log);
    }

    /**
     * 只有一个用户的情况下日志记录
     */
    public void saveLogCz(String cz, String zh, Double lat, Double lon, String sbbs) throws Exception {
        LogApp log = new LogApp();
        log.setCjsj(new Date());
        log.setCz(cz);
        log.setCzyid1(zh);
        log.setLat(lat);
        log.setLon(lon);
        log.setSbbs(sbbs);
        hTemplate.save(log);
    }

    /**
     * 如果有两个用户的情况下日志记录
     */
    public void saveLogCz2(String cz, String zh1, String zh2, Double lat, Double lon, String sbbs) throws Exception {
        LogApp log = new LogApp();
        log.setCjsj(new Date());
        log.setCz(cz);
        log.setCzyid1(zh1);
        log.setCzyid2(zh2);
        log.setLat(lat);
        log.setLon(lon);
        log.setSbbs(sbbs);
        hTemplate.save(log);
    }

    /**
     * 注销
     */
    public int logout(Double lat, Double lon, String sbbs, String un, String uf) throws Exception {
        String sql = "update T_JC_RY set lat=?,lon=?,sbbs=?,token='' where zgz=?  and state='1' and type like '%2'";
        Object[] params = new Object[]{lat, lon, sbbs, un};
        return jdbcTemplate.update(sql, params);
    }

    /**
     * 验证密码
     */
    public List getpw(String un, String pw) throws Exception {
        String sql = "select * from t_jc_ry where token=? and passwd=?";
        Object[] params = new Object[]{un, pw};
        return jdbcTemplate.queryForList(sql, params);

    }

    /**
     * 修改密码
     */
    public int xgpw(String un, String pw, String opw) throws Exception {
        String sql = "update t_jc_ry set passwd=? where token=?";
        Object[] params = new Object[]{pw, un};
        return jdbcTemplate.update(sql, params);
    }

    /**
     * 保存登录信息
     */
    public void saveToken(HeaderBean hb, String uf, String un) throws Exception {
        String sql = "update t_jc_ry set token =?,lat=?,lon=?,sbbs=?,dlsj=? where zgz=?";
        Object[] params = new Object[]{uf, hb.getLat(), hb.getLon(), hb.getDi(), new Date(), un};
        jdbcTemplate.update(sql, params);
    }

    public void savePic(final List<Jctp> list) throws Exception {
        Integer i = hTemplate.execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                Transaction ts = session.beginTransaction();
                int j = 0;
                for (Jctp p : list) {
                    session.save(p);
                    j++;
                    if (j % 20 == 0) {
                        session.flush();
                        session.clear();
                    }
                }
                ts.commit();
                return null;
            }
        });
    }

    /**
     * 用户反馈
     */
    public int yhbzfkSave(String sbbs, String fknr, String cl) throws Exception {
        String[] cls = cl.split(",");
        String sql = "";
        Object[] values = null;
        if (cls.length > 1) {
            sql = "INSERT INTO T_JC_YHFK (ID, SBBS, FKNR, CJSJ, STATE,CZYID1,CZYID2) VALUES(?, ?, ?, SYSDATE, 1,?,?)";
            values = new Object[]{UUID.randomUUID().toString().replaceAll("-", ""), sbbs, fknr, cls[0], cls[1]};
        } else {
            sql = "INSERT INTO T_JC_YHFK (ID, SBBS, FKNR, CJSJ, STATE,CZYID1) VALUES(?, ?, ?, SYSDATE, 1,?)";
            values = new Object[]{UUID.randomUUID().toString().replaceAll("-", ""), sbbs, fknr, cls[0]};
        }
        return jdbcTemplate.update(sql, values);
    }

    public List logout(String un) throws Exception {
        String sql = "select id from t_jc_ry where zgz=?  and state='1' and type like '%2'";
        Object[] params = new Object[]{un};
        return jdbcTemplate.queryForList(sql, params);
    }
}

