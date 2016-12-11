package com.lcc.taxi.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Jctp;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.dao.JctpDao;
import com.lcc.taxi.dao.UserLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JctpService {

    @Autowired
    private JctpDao jctpDao;
    @Autowired
    private UserLogDao logdao;

    /**
     *查询所有图片对象
     */
    public List<Jctp> getJctps() throws Exception {
        return jctpDao.getPics();
    }

    /**
     * 删除稽查图片
     */
    public int delJctp(String ids, Log log) throws Exception {
        int count = jctpDao.delJctp(ids);
        if (count > 0) {
            logdao.saveLog(log);
        }
        return count;
    }

    public byte[] gtp(String id) throws Exception {

        Jctp tp = new Jctp();
        tp = jctpDao.gtp(id);
        return tp.getPic();
    }

    public String tppath(String id) throws Exception {

        Jctp tp = new Jctp();
        tp = jctpDao.gtp(id);
        return tp.getPath();
    }

    /**
     * 查询所有图片id
     */
    public List getPics() throws Exception {
        List<Jctp> res = jctpDao.getPics();
        List resList = new ArrayList();
        Map resMap = null;
        for (Jctp tp : res) {
            resMap = new HashMap();
            resMap.put("ID", tp.getId());
            resMap.put("NAME", tp.getName());
            resMap.put("URL", tp.getPath());
            resList.add(resMap);
        }
        return resList;
    }
}
