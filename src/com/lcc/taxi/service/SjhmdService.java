package com.lcc.taxi.service;

import java.util.List;
import java.util.Map;

import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Sjhmd;
import com.lcc.taxi.dao.SjhmdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SjhmdService {
    @Autowired
    public SjhmdDao sjhmdDao;

    public boolean sjhmdSfz(String sfzh) {
        List<Sjhmd> list = sjhmdDao.sjhmdSfz(sfzh);
        return list.size() == 0;
    }

    public void sjhmdAdd(Sjhmd sjhmd, Log log) {
        sjhmdDao.sjhmdAdd(sjhmd, log);
    }

    public int sjhmdDel(String id, Log log) {
        return sjhmdDao.sjhmdDel(id, log);
    }

    public void sjhmdqx(Sjhmd sjhmd, Log log) {
        sjhmdDao.sjhmdqx(sjhmd, log);
    }

    public int sjhmdupd(Sjhmd sjhmd, Log log) {
        return sjhmdDao.sjhmdupd(sjhmd, log);
    }

    public List<Map<String, Object>> sjhmdxx(String sj) throws Exception {
        return sjhmdDao.sjhmdxx(sj);
    }

    public List<Map<String, Object>> sjhmdname(String xm) throws Exception {
        return sjhmdDao.sjhmdname(xm);
    }

    public int checkSfz(String sjxm, String sfzhm) throws Exception {
        return sjhmdDao.checkSfz(sjxm, sfzhm);
    }
}
