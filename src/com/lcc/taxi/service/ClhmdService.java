package com.lcc.taxi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Clhmd;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.dao.ClhmdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClhmdService {

    @Autowired
    private ClhmdDao clhmdDao;

    @Autowired
    private JdbcTemplate jdbctemplate;

    public boolean clhmdcph(String cph) {
        List<Clhmd> list = clhmdDao.clhmdCph(cph);
        return list.size() == 0;
    }

    public void clhmdAdd(Clhmd clhmd, Log log) {
        clhmdDao.clhmdAdd(clhmd, log);
    }

    public int clhmdDel(String id, Log log) {
        return clhmdDao.clhmdDel(id, log);
    }

    public void clhmdqx(Clhmd clhmd, Log log) {
        clhmdDao.clhmdqx(clhmd, log);
    }

    public int clhmdupd(Clhmd clhmd, Log log) {
        return clhmdDao.clhmdupd(clhmd, log);
    }

    public List<Map<String, Object>> clhmdxx(String sj) throws Exception {
        return clhmdDao.clhmdxx(sj);
    }

    public List<Map<String, Object>> clhmdcphauto(String cph) throws Exception {
        return clhmdDao.clhmdcphauto(cph);
    }

    public boolean checkch(String cph) throws Exception {
        return clhmdDao.checkch(cph);
    }
}

