package com.lcc.taxi.dao;

import java.util.List;

import com.lcc.framework.util.LogUtil;
import com.lcc.taxi.bean.Jctp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JctpDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HibernateTemplate hTemplate;

    /**
     * 删除若干张图片
     */
    public int delJctp(String ids) throws Exception {
        String sql = "DELETE from t_jc_pic WHERE ID IN(?)";
        LogUtil.getLogger().debug("ids = " + ids);
        int count = 0;
        for (String id : ids.split(",")) {
            count += jdbcTemplate.update(sql, id);
        }
        return count;
    }

    /**
     * 图片下载
     */
    public Jctp gtp(String id) throws Exception {
        return hTemplate.get(Jctp.class, id);
    }

    /**
     * 查询所有图片id
     */
    public List<Jctp> getPics() throws Exception {
        String sql = "SELECT id,cjsj,czyid,name,path from t_jc_pic";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Jctp.class));
    }
}
