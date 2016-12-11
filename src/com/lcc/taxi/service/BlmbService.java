package com.lcc.taxi.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Blmb;
import com.lcc.taxi.bean.Blwt;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Wtmb;
import com.lcc.taxi.dao.BlmbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlmbService {

    @Autowired
    private BlmbDao blmbDao;

    public boolean blwtAdd(Blwt blwt, Log log) throws Exception {
        boolean result = true;
        List list = blmbDao.checkWt(blwt.getId(), blwt.getWtnr());
        if (list != null && list.size() != 0) {
            result = false;
        }

        blmbDao.blwtAdd(blwt, log);
        return result;
    }

    public boolean blwtUpd(String wtnr, String id, Log log) throws Exception {
        boolean result = true;
        List list = blmbDao.checkWt(id, wtnr);
        if (list != null && list.size() != 0) {
            result = false;
        }
        if (result) {
            blmbDao.blwtUpd(wtnr, id, log);
        }

        return result;
    }

    public int blwtDel(String id, Log log) throws Exception {
        return blmbDao.blwtDel(id, log);
    }

    public List<Map<String, Object>> blwt_combobox() throws Exception {
        // TODO Auto-generated method stub
        return blmbDao.blwt_combobox();
    }

    public boolean blmbAdd(Blmb blmb, List<Wtmb> list, Log log) throws Exception {
        boolean result = true;
        List lis = blmbDao.checkMb(blmb.getMbmc(), blmb.getType(), blmb.getId());
        if (lis != null && lis.size() != 0) {
            result = false;
        }
        blmbDao.blmbAdd(blmb, list, log);
        return result;
    }

    public boolean blmbMBMC(String mbmc) throws Exception {
        boolean result = true;
        List lis = blmbDao.mbmccz(mbmc);
        if (lis != null && lis.size() != 0) {
            result = false;
        }
        return result;
    }

    public boolean blwtnr(String wtnr) throws Exception {
        boolean result = true;
        List lis = blmbDao.blwtnr(wtnr);
        if (lis != null && lis.size() != 0) {
            result = false;
        }
        return result;
    }

    public List<Map<String, Object>> boxupd(String id) throws Exception {
        return blmbDao.boxupd(id);
    }

    public boolean blmbUpd(String id, String mbmc, List<Wtmb> list, String radioid) throws Exception {
        boolean result = true;
        List lis = blmbDao.checkMb(mbmc, radioid, id);
        if (lis != null && lis.size() != 0) {
            result = false;
        }
        blmbDao.blmbUpd(id, mbmc, list, radioid);
        return result;
    }

    public int blmbDel(String id, Log log) throws Exception {
        return blmbDao.blmbDel(id, log);
    }

    public int szmrlx(String id, String type) throws Exception {
        return blmbDao.szmrlx(id, type);
    }

    public int szmrlx2(String id, String type, Log log) throws Exception {
        return blmbDao.szmrlx2(id, type, log);
    }
}

