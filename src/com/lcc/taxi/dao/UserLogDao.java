package com.lcc.taxi.dao;

import java.util.List;

import com.lcc.taxi.bean.Jcry;
import com.lcc.taxi.bean.JcryDlxx;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Tjcdev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserLogDao {
    @Autowired
    private HibernateTemplate hTemplate;

    @Autowired
    private JdbcTemplate jdbctemplate;

    /**
     * 保存平台日志信息
     */
    public void saveLog(Log log) throws Exception {
        String sql = "INSERT INTO t_jc_log(ID,cz,cjsj,czyid,czyxm,detail) VALUES(sys_guid(),?,?,?,?,?)";
        jdbctemplate.update(sql, new Object[]{log.getCz(), log.getCjsj(), log.getCzyid(), log.getCzyxm(), log.getDetail()});
    }

    /**
     * 保存登陆信息
     */

    public void saveLogInfo(JcryDlxx jcdlxx, Log log) throws Exception {
        jcdlxx.save();
        log.save();
    }

    /**
     * 校验唯一标识
     */
    public List<Tjcdev> gcheckAppWybs(String sbbs) throws Exception {
        String sql = "select id,appname,sbbm,wybs,cjsj,gxsj,czy,state "
                + "from t_jc_appzc where  wybs = ? and state <> '0'";
        Object[] value = new Object[]{sbbs};
        List<Tjcdev> list = jdbctemplate.query(sql, value, new BeanPropertyRowMapper<Tjcdev>(Tjcdev.class));
        return list;
    }

    /**
     * 校验用户名和密码
     */
    public List<Jcry> checkNamePWD(String zh) throws Exception {
        String sql = "select id,zh,passwd,name from t_jc_ry where zh = ? and state <> '0'";
        Object[] values = new Object[]{zh};
        List<Jcry> lists = jdbctemplate.query(sql, values, new BeanPropertyRowMapper<Jcry>(Jcry.class));
        return lists;
    }

    /**
     * 得到稽查人员信息
     */
    public List<Jcry> getJcryInfoDao(String zh) throws Exception {
        String sql = "select id,zh,passwd,name,mobile,zgz,bm,cjsj,xgsj,state from t_jc_ry where zh = ? and state <>'0'";
        Object[] values = new Object[]{zh};
        List<Jcry> lists = jdbctemplate.query(sql, values, new BeanPropertyRowMapper<Jcry>(Jcry.class));
        return lists;
    }

    public HibernateTemplate gethTemplate() {
        return hTemplate;
    }

    public void sethTemplate(HibernateTemplate hTemplate) {
        this.hTemplate = hTemplate;
    }

    public JdbcTemplate getJdbctemplate() {
        return jdbctemplate;
    }

    public void setJdbctemplate(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }
}
