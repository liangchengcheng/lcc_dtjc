package com.lcc.taxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.TjcFwqcs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FwqcsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addFwqcs(TjcFwqcs t_jc_fwqcs) throws Exception{
        t_jc_fwqcs.save();
    }

    public void updFwqcs(TjcFwqcs t_jc_fwqcs) throws Exception{
        Object[] val = new Object[]{t_jc_fwqcs.getFwqdz() , t_jc_fwqcs.getYwdz() ,t_jc_fwqcs.getDkh() , t_jc_fwqcs.getCs() ,t_jc_fwqcs.getId()};
        String sql = "update T_JC_FWQCS set fwqdz = ?,ywdz = ?, dkh = ?, cs= ? where id = ?";
        jdbcTemplate.update(sql, val);
    }
    public void delFwqcs(String id) throws Exception{
        String sql = "delete from T_JC_FWQCS where ID = ?";
        Object[] val = new Object[] { id };
        jdbcTemplate.update(sql, val);
    }

}
