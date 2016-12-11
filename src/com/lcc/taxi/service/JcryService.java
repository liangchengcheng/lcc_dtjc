package com.lcc.taxi.service;

import java.util.List;
import java.util.Map;
import com.lcc.taxi.bean.Jcry;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.dao.JcryDao;
import com.lcc.taxi.dao.UserLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JcryService {

    @Autowired
    private JcryDao jcrydao;
    @Autowired
    private UserLogDao logdao;

    /**
     * 获取自动填充sel的稽查人员信息
     */
    public List getUsersForAutoSel(Jcry jcry) throws Exception {
        return jcrydao.getUsersForAutoSel(jcry);
    }

    /**
     * 修改稽查人员头像
     */
    public int addPortrait(Jcry jcry, Log log) throws Exception {
        int i = jcrydao.addPortrait(jcry);
        if (i > 0) {
            logdao.saveLog(log);
        }
        return i;
    }

    /**
     * 添加jcry
     */
    public void addJcryService(Jcry jcry, Log log) throws Exception {
        jcrydao.addJcryDao(jcry, log);
    }

    public void addJcryService(Jcry jcry) throws Exception {
        jcrydao.addJcryDao(jcry);
    }

    /**
     * 依照id查询稽查人员信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Jcry> getJcryByid(String id) throws Exception {
        List<Jcry> list = jcrydao.getJcryByIdDao(id);
        return list;
    }

    /**
     * 依照账号查询稽查人员信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Jcry> getJcryByZH(String zh) throws Exception {
        List<Jcry> list = jcrydao.getJcryByZHDao(zh);
        return list;
    }

    /**
     * 修改jcry
     */
    public void upJcryService(Jcry jcry, Log log) throws Exception {
        jcrydao.upJcryDao(jcry, log);
    }

    public void upJcryService(Jcry jcry) throws Exception {
        jcrydao.upJcryDao(jcry);
    }

    /**
     * 删除jcry
     */
    public int deleteJcryService(String id, Log log) throws Exception {
        int c = jcrydao.delJcryDao(id, log);
        return c;
    }

    public int deleteJcryService(String id) throws Exception {
        int c = jcrydao.delJcryDao(id);
        return c;
    }

    /**
     * 稽查人员--修改密码
     */
    public int updPassword(String id, String oldpw, String newpw) throws Exception {
        return jcrydao.updPassword(id, oldpw, newpw);
    }

    public boolean checkname(String id, String zh) throws Exception {
        List list = null;
        if (id != null && !"".equals(id)) {
            list = jcrydao.checkname(id, zh);
        } else {
            list = jcrydao.checkname(zh);
        }

        if (list != null && list.size() != 0) {
            return false;
        }
        return true;
    }

    public boolean checkzgz(String zgz) throws Exception {
        List list = null;

        list = jcrydao.checkzgz(zgz);

        if (list != null && list.size() != 0) {
            return false;
        }
        return true;
    }

    public void yhjsadd(String id) {

    }

    /**
     * 查询权限combox
     */
    public List<Map<String, Object>> queryyhjs(String sysflag) {
        List<Map<String, Object>> list = jcrydao.queryyhjsDao(sysflag);
        return list;
    }

    public int setResetPass(String id, String pass, Log log) throws Exception {
        return jcrydao.setResetPass(id, pass, log);
    }

    public List<Map<String, Object>> roleCom(String id) throws Exception {
        return jcrydao.roleCom(id);
    }
}

