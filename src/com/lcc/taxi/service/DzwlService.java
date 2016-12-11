package com.lcc.taxi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Dzwl;
import com.lcc.taxi.bean.DzwlLog;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.dao.DzwlDao;
import com.lcc.taxi.dao.DzwlLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DzwlService {

    @Autowired
    private DzwlDao dzwlDao;
    @Autowired
    private DzwlLogDao dzwlLogDao;

    /**
     * 新增电子围栏
     */
    public void addDzwl(Dzwl dzwl, Log log) throws Exception {
        dzwlDao.addDzwl(dzwl, log);
    }

    /**
     * 新增修改dzwl前判断电子围栏是否已存在
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Map addUpDzwlCheck(Dzwl dzwl) throws Exception {
        Map res = new HashMap();
        res.put("state", true);//校验通过
        if (dzwlDao.addUpDzwlCheck(dzwl) > 0) {
            res.put("state", false);//校验没有通过
            res.put("info", "该名称已存在！");
        }
        return res;
    }

    /**
     * 查询所有电子围栏
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Dzwl> queryAllDzwl(Dzwl dzwl, String tk) throws Exception {
        return dzwlDao.queryAllDzwl(dzwl, tk);
    }

    /**
     * 删除一个电子围栏
     */
    public int delDzwl(String id, Log log) throws Exception {
        return dzwlDao.delDzwl(id, log);
    }

    /**
     * 更新电子围栏
     */
    public int upDzwl(Dzwl dzwl, Log log) throws Exception {
        return dzwlDao.upDzwl(dzwl, log);
    }

    /**
     * 开启电子围栏
     */
    public int openWl(Dzwl dzwl, DzwlLog dl, Log log) throws Exception {
        int count = dzwlDao.upDzwl(dzwl, log);
        if (count > 0) {
            dzwlLogDao.addDzwlLog(dl);
        }
        return count;
    }

    public int openWl(Dzwl dzwl, DzwlLog dl) throws Exception {
        int count = dzwlDao.upDzwl(dzwl);
        return count;
    }

    /**
     * 关闭电子围栏
     */
    public int closeDzwl(Dzwl dzwl, DzwlLog dl, Log log) throws Exception {
        int count = dzwlDao.upDzwl(dzwl, log);
        if (count > 0) {
            dzwlLogDao.addDzwlLog(dl);
        }
        return count;
    }

    public int closeDzwl(Dzwl dzwl, DzwlLog dl) throws Exception {
        int count = dzwlDao.upDzwl(dzwl);
        if (count > 0) {
            dzwlLogDao.closeDzwl(dl);
        }
        return count;
    }
}