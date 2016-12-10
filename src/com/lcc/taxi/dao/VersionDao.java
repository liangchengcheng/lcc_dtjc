package com.lcc.taxi.dao;

import java.util.List;

import com.lcc.taxi.bean.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VersionDao {

    @Autowired
    private JdbcTemplate jdbctemplate;

    /**
     * 发布
     */
    public int publish(String id) throws Exception {
        String sql = "UPDATE T_JC_VERSION SET state=2 WHERE ID=?";
        return jdbctemplate.update(sql, id);
    }

    /**
     * 查询所有的版本信息
     */
    public List<Version> getVersionDao() throws Exception {
        String sql = "select * from (select t.id,t.durl,t.fbsj,t.bbms,t.version,t.sfsj,t.code,t.state,t.apkdx from t_jc_version t where state = '2'  order by code desc) where rownum=1 ";
        List<Version> list = jdbctemplate.query(sql, new BeanPropertyRowMapper<Version>(Version.class));
        return list;
    }
}
