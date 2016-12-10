package com.lcc.taxi.dao;

import com.lcc.taxi.bean.DzwlLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 电子围栏日志记录类
 */
@Repository
public class DzwlLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增日志
     */
    public void addDzwlLog(DzwlLog dl) throws Exception{
        dl.save();
    }
    public void closeDzwl(DzwlLog dl) throws Exception{
        String sql = "UPDATE  t_jc_dzwl_log SET cczsj=?,czyid1=?,czyid2=?,cz=? where wlid =?";
        jdbcTemplate.update(sql, new Object[]{dl.getCczsj(),dl.getCzyid1(),dl.getCzyid2(),dl.getCz(),dl.getWlid()});
    }
}
