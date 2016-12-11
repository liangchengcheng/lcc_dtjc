package com.lcc.taxi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Wfcd;
import com.lcc.taxi.bean.Wzxw;
import com.lcc.taxi.bean.Wzxwbl;
import com.lcc.taxi.dao.WzxwDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WzxwService {

    @Autowired
    private WzxwDao wzxwDao;

    public boolean wzxwAdd(Wzxw wzxw, List<Wfcd> list, List<Wzxwbl> listwfxw, Log log) throws Exception {
        boolean result = true;
        List l = wzxwDao.checkWzxw(wzxw);
        if (l != null && l.size() != 0) {
            result = false;
        } else {
            wzxwDao.wzxwAdd(wzxw, list, listwfxw, log);
        }
        return result;
    }

    public boolean wzxwUpd(Wzxw wzxw, List<Wfcd> list, List<Wzxwbl> listwfxw, Log log) throws Exception {
        boolean result = true;
        List l = wzxwDao.checkWzxw(wzxw);
        if (l != null && l.size() != 0) {
            result = false;
        } else {

            wzxwDao.wzxwUpd(wzxw, list, listwfxw, log);
        }
        return result;
    }

    public int wzxwDel(String id, Log log) throws Exception {
        return wzxwDao.wzxwDel(id, log);
    }

    public List wzxwlist() throws Exception {

        List<Map<String, Object>> list = wzxwDao.wzxwlistApp();
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        Map<String, Object> m = null;
        Map<String, Object> wfcdm = null;
        String wznr = "";
        List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> n : list) {

            if (n.get("WZNR") != null && !wznr.equals(n.get("WZNR"))) {
                m = new HashMap<String, Object>();
                m.put("id", n.get("ID"));
                m.put("wfxw", n.get("WZNR"));
                m.put("fgyj", n.get("WGYJ"));
                list3 = new ArrayList<Map<String, Object>>();
            }
            wfcdm = new HashMap<String, Object>();
            wfcdm.put("id", n.get("CDID"));
            wfcdm.put("wfcd", n.get("WFCD"));
            wfcdm.put("qjhg", n.get("HG"));
            wfcdm.put("cfbz", n.get("CFBZ"));
            list3.add(wfcdm);
            m.put("state", list3);
            if (n.get("WZNR") != null && !wznr.equals(n.get("WZNR"))) {
                list2.add(m);
            }

            wznr = n.get("WZNR") != null ? n.get("WZNR").toString() : "";

        }
        return list2;
    }

    public List wtlist(String type) throws Exception {
        return wzxwDao.wtlistApp(type);
    }

    public List<Map<String, Object>> wzxwcdlist() throws Exception {
        return wzxwDao.wzxwcdlist();
    }

    public List<Map<String, Object>> wzxwcdlist_upd(String id) throws Exception {
        return wzxwDao.wzxwcdlist_upd(id);
    }

    public List<Map<String, Object>> wzxwwtqdlx(String id) throws Exception {
        return wzxwDao.wzxwwtqdlx(id);
    }
}
