package com.lcc.taxi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.framework.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ScriptHelper {

    private JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.getBean("jdbcTemplate");

    public List<Object> getParamsList() {
        return new ArrayList<Object>();
    }

    public Object loadDataBySql(String sql, List<Object> params) {
        Object obj = jdbcTemplate.queryForObject(sql, params.toArray(), Object.class);
        return obj;
    }

    /**
     *  list查询。返回List<Map<String,Object>>
     */
    public List queryForList(String sql, List<Object> params) {
        List list = jdbcTemplate.queryForList(sql, params.toArray());
        return list;
    }

    public Map queryForMap(String sql, List<Object> params) {
        Map map = null;
        try {
            map = jdbcTemplate.queryForMap(sql, params.toArray());
        } catch (Exception e) {
            map = new HashMap();
        }
        return map;
    }

}
