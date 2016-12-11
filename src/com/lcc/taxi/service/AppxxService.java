package com.lcc.taxi.service;


import java.util.HashMap;
import java.util.List;

import com.lcc.taxi.bean.Appxx;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Tjcdev;
import com.lcc.taxi.dao.AppxxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppxxService {

    @Autowired
    private AppxxDao appxxDao;

    public HashMap<String, Object> appzcCheck(Tjcdev appzc) throws Exception{
        HashMap<String, Object> m = appxxDao.appzcCheck(appzc);
        return m;
    }

    public void appzcAdd(Tjcdev appzc,Log log) throws Exception{
        appxxDao.appzcAdd(appzc,log);
    }

    public void appzcUpd(Tjcdev appzc,Log log) throws Exception{
        appxxDao.appzcUpd(appzc,log);
    }

    /**
     * 删除app注册信息
     */
    public int appzcDel(String id,Log log) throws Exception{
        return appxxDao.appzcDel(id,log);
    }

    public int bbmsDel(String id) throws Exception {
        return appxxDao.bbmsDel(id);
    }

    public void bbmsAdd(Appxx appxx) throws Exception {
        appxxDao.bbmsAdd(appxx);
    }

    public void bbmsUpd(Appxx appxx) throws Exception{
        appxxDao.bbmsUpd(appxx);
    }
    public int cz(String id,String sbbm) throws Exception{
        return appxxDao.cz(id,sbbm);
    }
    public List getSBBSForAutoSel(Tjcdev appzc) throws Exception{
        return appxxDao.getSBBSForAutoSel(appzc);
    }
    public List yxgjcz(String kssj,String jssj,String sbbm) throws Exception{
        return appxxDao.yxgjcz(kssj,jssj,sbbm);
    }
}
