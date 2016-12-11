package com.lcc.taxi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.framework.util.LogUtil;
import com.lcc.taxi.dao.TbaseRunDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TbaseRunService {

    @Autowired
    private TbaseRunDao rundao;

    /**
     * 得到车辆运营档案信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Map<String, Object> getRunInfoService(String cphm, String begintime, String endtime) throws Exception {
        Map map = new HashMap();
        LogUtil.getLogger().info("qd" + System.currentTimeMillis());
        List<Map<String, Object>> qd = rundao.getQd(cphm, begintime, endtime);
        map.put(SystemConstants.QD, qd);
        LogUtil.getLogger().info("qt" + System.currentTimeMillis());
        List<Map<String, Object>> qt = rundao.getQt(cphm, begintime, endtime);
        map.put(SystemConstants.QT, qt);
        LogUtil.getLogger().info("list" + System.currentTimeMillis());
        List<Map<String, Object>> list = rundao.getRunInfo(cphm, begintime, endtime);
        LogUtil.getLogger().info("list_end" + System.currentTimeMillis());
        map.put(SystemConstants.YS, list);
        return map;
    }
}
