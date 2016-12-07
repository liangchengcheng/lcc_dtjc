package com.lcc.taxi.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * Created by lcc on 2016/12/7.
 */
public class AjclDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 删除操作
     */
    public int ajcDel(String id){
        String sql = "delete from T_JC_WZJL WHERE ID = ?";
        Object[] val = new Object[]{id};
        return jdbcTemplate.update(sql,val);
    }

    //public int ajclresult(String id,Log)
}
