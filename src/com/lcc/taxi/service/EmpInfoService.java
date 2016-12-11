package com.lcc.taxi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.taxi.dao.EmpInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpInfoService {

    @Autowired
    private EmpInfoDao empInfoDao;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public HashMap<String, Object> queryempinfo(String sfzh) throws Exception {
        return empInfoDao.queryempinfo(sfzh);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public HashMap<String, Object> jsempinfo(String sfzh) throws Exception {
        return empInfoDao.jsempinfo(sfzh);
    }

    public void getPic(String lasttime) throws Exception {
        empInfoDao.getPic(lasttime);
    }

    public HashMap<String, Object> gtfs(String sfz) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> m = empInfoDao.gtfs(sfz);
        if (m != null && m.size() != 0) {
            map.put("workstate", m.get("workstate") != null ? m.get("workstate") : "");
            map.put("khfz", (m.get("khfz") != null && (Double.valueOf(SystemConstants.KHZF) > Double.valueOf(m.get("khfz").toString()))) ? (Double.valueOf(SystemConstants.KHZF) - Double.valueOf(m.get("khfz").toString())) : SystemConstants.KHZF);
        }
        return map;
    }

    public HashMap<String, Object> gtickh(String sfz) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = empInfoDao.gtickh(sfz);
        if (list != null && list.size() != 0) {
            map.put("ickh", list.get(0).get("ICKH"));
        }
        return map;
    }
}
