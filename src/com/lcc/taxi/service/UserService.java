package com.lcc.taxi.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.lcc.framework.bean.HeaderBean;
import com.lcc.framework.bean.UserLogin;
import com.lcc.framework.util.ComUtils;
import com.lcc.framework.util.LogUtil;
import com.lcc.framework.util.MD5Util;
import com.lcc.taxi.bean.Jcry;
import com.lcc.taxi.bean.Jctp;
import com.lcc.taxi.bean.LogApp;
import com.lcc.taxi.dao.UserDao;
import com.lcc.taxi.dao.UserLogDao;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserLogDao logdao;


    /**
     * 返回稽查人员信息
     */
    public boolean cheksb(String di) throws Exception {
        List<Map<String, Object>> list = userDao.checksb(di);
        if (list != null && list.size() != 0) {
            if (Integer.valueOf(list.get(0).get("state").toString()) == 1) {
                return true;
            }
        } else {
            userDao.saveBs(di);
        }
        return false;
    }

    /**
     * 返回稽查人员信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Jcry> getJcryInfo(String zh) throws Exception {
        List<Jcry> lists = logdao.getJcryInfoDao(zh);
        return lists;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Map chekyh(String tk) throws Exception {
        boolean result = false;
        Map<String, Object> map = new HashMap<String, Object>();
        List list = new ArrayList();

        map.put("result", 0);
        String[] str = new String[2];
        str = tk.split(",");
        int i = 0;
        int j = 0;
        for (String s : str) {
            List lt = userDao.checkyh(s);
            Map m = new HashMap<>();
            if (lt != null && lt.size() != 0) {// 已登陆
                m.put("tk", s);
                m.put("rt", 1);
                i++;
            } else {// 未登录
                m.put("tk", s);
                m.put("rt", 0);
                j++;
            }
            list.add(m);
        }
        // 两个用户都需要验证
        // if(i==str.length){
        // map.put("result", 1);
        // }

        // 只验证一个用户
        if (i >= 1) {
            map.put("result", 1);
        }
        map.put("list", list);
        return map;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Map chekyh2(String tk) throws Exception {
        boolean result = false;
        Map<String, Object> map = new HashMap<String, Object>();
        List list = new ArrayList();
        map.put("result", 0);
        String[] str = new String[2];
        str = tk.split(",");
        int i = 0;
        int j = 0;
        for (String s : str) {
            List lt = userDao.checkyh(s);
            Map m = new HashMap<>();
            if (lt != null && lt.size() != 0) {// 已登陆
                m.put("tk", s);
                m.put("rt", 1);
                i++;
            } else {// 未登录
                m.put("tk", s);
                m.put("rt", 0);
                j++;
            }
            list.add(m);
        }
        //两个用户都需要验证
        if (i == str.length) {
            map.put("result", 1);
        }

        map.put("list", list);
        return map;
    }

    public UserLogin login(String un, String pw, HeaderBean hb)
            throws Exception {
        UserLogin ul = new UserLogin();
        List<Map<String, Object>> list = userDao.login(un, pw, hb.getTs());
        System.out.println(list + pw);
        if (list != null && list.size() != 0) {
            String pwd = list.get(0).get("PASSWD").toString();
            if (MD5Util.getMD5Lower(pw + hb.getTs()).equals(
                    MD5Util.getMD5Lower(pwd + hb.getTs()))) {
                String uf = null;// 生成TOKEN
                try {
                    uf = MD5Util.getMD5Lower(UUID.randomUUID().toString()
                            .replaceAll("-", "")
                            + (System.currentTimeMillis() + ""));
                } catch (NoSuchAlgorithmException e) {
                    LogUtil.getLogger().info("MD5加密出错！", e);
                } catch (NullPointerException e) {
                    LogUtil.getLogger().info("MD5加密出错！", e);
                }
                ul.setId(list.get(0).get("ID") != null ? list.get(0).get("ID")
                        .toString() : null);
                ul.setUrl(list.get(0).get("TXID") != null ? list.get(0)
                        .get("TXID").toString() : null);
                ul.setDc(list.get(0).get("BH") != null ? list.get(0).get("BH")
                        .toString() : null);
                ul.setDd(list.get(0).get("BM") != null ? list.get(0).get("BM")
                        .toString() : null);
                ul.setUi(list.get(0).get("ZGZ") != null ? list.get(0)
                        .get("ZGZ").toString() : null);
                ul.setUn(list.get(0).get("NAME") != null ? list.get(0)
                        .get("NAME").toString() : null);
                ul.setMl(list.get(0).get("MOBILE") != null ? list.get(0)
                        .get("MOBILE").toString() : null);
                ul.setUf(uf);
                userDao.saveToken(hb, uf, un);
                LogApp log = new LogApp();
                log.setCjsj(new Date());
                log.setCz("登录");
                log.setCzyid1(list.get(0).get("ID") != null ? list.get(0)
                        .get("ID").toString() : null);
                log.setLat(hb.getLat());
                log.setLon(hb.getLon());
                log.setSbbs(hb.getDi());
                userDao.saveLog(log);
                // 封装返回APP实体Bean
                return ul;
            }
            LogUtil.getLogger().info("密码错误！");
        }
        LogUtil.getLogger().info("账号错误！");
        // 验证不成功一律返回null
        return null;
    }

    public boolean logout(String un, String uf, Double lat, Double lon,
                          String sbbs, String cl) throws Exception {
        //没有用户直接注销
        List list = userDao.logout(un);
        if (list == null || list.size() == 0) {
            return true;
        }
        int i = userDao.logout(lat, lon, sbbs, un, uf);
        if (i > 0) {
            userDao.saveLogCz("注销", cl, lat, lon, sbbs);
            return true;
        }

        return false;
    }

    public int xgpw(String un, String pw, String opw, Double lat, Double lon,
                    String sbbs, String cl) throws Exception {
        List list = userDao.getpw(un, opw);
        if (list != null && list.size() != 0) {
            int i = userDao.xgpw(un, pw, opw);
            if (i > 0) {
                userDao.saveLogCz("修改密码", cl, lat, lon, sbbs);
                return 1;
            }
        } else {
            return 2;
        }

        return 0;
    }

    public void savelog(String cz, String cl, String lat, String lon,
                        String sbbs) throws Exception {
        cl = ComUtils.dealNull(cl);
        String[] cls = cl.split(",");
        if (cls.length > 1) {
            userDao.saveLogCz2(cz, cls[0], cls[1], Double.valueOf(lat),
                    Double.valueOf(lon), sbbs);
        } else {
            userDao.saveLogCz(cz, cl, Double.valueOf(lat), Double.valueOf(lon),
                    sbbs);
        }
    }

    public void savePic(List<Jctp> jctp) throws Exception {
        userDao.savePic(jctp);

    }

    /**
     * 用户反馈
     */
    public boolean yhbzfkSave(String sbbs, String fknr, String cl)
            throws Exception {
        boolean result = false;
        int i = userDao.yhbzfkSave(sbbs, fknr, cl);
        if (i == 1) {
            result = true;
        }
        return result;
    }
}
