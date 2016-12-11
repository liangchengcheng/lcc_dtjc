package com.lcc.taxi.service;


import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.framework.util.ComUtils;
import com.lcc.framework.util.LogUtil;
import com.lcc.framework.util.PropertyLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QyndPjService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List<Map<String, Object>> qyList;

    /**
     * 企业年度评级前检查
     */
    public Map qyndPjCheck(Map map) throws Exception {
        Map result = new HashMap();
        result.put("state", true);
        String year = ComUtils.dealNull(map.get("year"));
        String minYear = "";
        String sql = "";
        Object[] values = null;
        int needMonth = 0;
        //判断是否进行过企业年度评分
        sql = "SELECT (SELECT COUNT(*) from t_base_com com WHERE com.state=1 AND COM.ID_owner NOT IN (?)) -(select COUNT(*) from t_kp_qy_khjg WHERE state=1 AND YEAR=? AND nzdf IS NOT NULL) from dual";
        if (jdbcTemplate.queryForObject(sql, new Object[]{SystemConstants.IDS,year},Integer.class) > 0) {
            result.put("state", false);
            result.put("info", "请确定所有企业已进行年度考核！");
            return result;
        }
        sql = "SELECT COUNT(*) from t_kp_qy_khjg WHERE state=1 AND YEAR=?";
        if (jdbcTemplate.queryForObject(sql, new Object[]{year},Integer.class) < 1) {
            result.put("state", false);
            result.put("info", "没有本年度企业数据！");
            return result;
        }
        // 获取最小年
        sql = "SELECT MIN(YEAR) from t_kp_qy_khjg where state=1";
        minYear = jdbcTemplate.queryForObject(sql, String.class);
        // 获取要评级年的一条数据
        sql = "SELECT nvl2(to_char(df1),'0',';')||nvl2(to_char(df2),'0',';')||nvl2(to_char(df3),'0',';')||nvl2(to_char(df4),'0',';')||"
                + " nvl2(to_char(df5),'0',';')||nvl2(to_char(df6),'0',';')||nvl2(to_char(df7),'0',';')||nvl2(to_char(df8),'0',';')||"
                + " nvl2(to_char(df9),'0',';')||nvl2(to_char(df10),'0',';')||nvl2(to_char(df11),'0',';')||nvl2(to_char(df12),'0',';') dfs "
                + " from t_kp_qy_khjg WHERE state=1 AND ROWNUM=1 AND YEAR=?";
        values = new Object[]{year};
        String dfs = jdbcTemplate.queryForObject(sql, values, String.class);
        needMonth = dfs.lastIndexOf(";");
        if (minYear.equalsIgnoreCase(year)) {// 上线的第一年，数据是不完整的，保证从第一个有数据的月份到12月都有数据
            while (dfs.startsWith(";")) {// 删除前面几个没有数据的月份
                dfs = dfs.substring(1);
            }
        } else {// 非第一年，保证12个月都有数据
        }
        if (dfs.contains(";")) {
            result.put("state", false);
            result.put("info", (needMonth + 1) + "月没有数据！");
        }
        // 判断T_kp_khrwb_nd.jsykhzt是否=1，只有=1才能进行年度评级
        sql = "SELECT jsykhzt from T_kp_khrwb_nd WHERE YEAR=? AND state=1";
        values = new Object[]{year};
        int jsykhzt = 0;
        try {
            jsykhzt = jdbcTemplate.queryForObject(sql, new Object[]{values},Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsykhzt != 1 && jsykhzt != 2) {
            result.put("state", false);
            result.put("info", result.get("info") == null ? "请先进行驾驶员评级！"
                    : result.get("info") + "</br>" + "请先进行驾驶员评级！");
        }
        return result;
    }

    /**
     * 企业年度评级
     */
    public Map qyndPj(Map map) throws Exception {
        PropertyLoad pp = PropertyLoad.getInstance("/pfjb.properties");
        ScriptEngineManager sem = new ScriptEngineManager();
        // 获取脚本引擎对象，这里为javascript
        ScriptEngine engine = sem.getEngineByName("JavaScript");

        String year = ComUtils.dealNull(map.get("year"));
        if (year.equals("") || year.equalsIgnoreCase(null) || year.equals("0")) {// 没有年份，默认本年
            Calendar c = Calendar.getInstance();
            year = c.get(Calendar.YEAR) + "";
        }
        engine.put("year", year);

        // 获取所有的企业信息
        queryQyList(year, ComUtils.dealNull(map.get("qyId")));
        String dj = "";
        String qyId = "";// 企业id
        String cftj = "";// 触发条件
        int df;// 得分
        int jsyCount;// 驾驶员人数
        double month = 0d;// 距注册日期的月数
        for (int i = 0; i < qyList.size(); i++) {
            Map qyMap = qyList.get(i);
            df = ComUtils.parseInt(qyMap.get("DF"));
            qyId = ComUtils.dealNull(qyMap.get("QYID"));
            jsyCount = ComUtils.parseInt(qyMap.get("JSYCOUNT"));
            month = ComUtils.parseDouble(qyMap.get("MC"));
            cftj = ComUtils.dealNull(qyMap.get("CFTJ"));
            if (!cftj.equals("")) {// 防止过长传到js中出现异常
                cftj = "notEmpty";
            }
            engine.put("qyId", qyId);
            engine.put("jsyCount", jsyCount);
            engine.put("month", month);
            engine.put("df", df);
            engine.put("cftj", cftj);

            //engine.put("ScriptHelper", new ScriptHelper());
            // 执行脚本
            try {
                String pfjb = pp.getValue("qyPj");
                engine.eval(pfjb);
                Invocable inv = (Invocable) engine;
                dj = inv.invokeFunction("calculate").toString();
            } catch (ScriptException e) {
                LogUtil.getLogger().error("ScriptException", e);
                throw e;
            }
            // 转型为脚本执行对象
            catch (NoSuchMethodException e) {
                LogUtil.getLogger().error("NoSuchMethodException", e);
                throw e;
            } catch (Exception e) {
                LogUtil.getLogger().error("qyndPj", e);
                throw e;
            }
            jdbcTemplate.update(
                    "UPDATE t_kp_qy_khjg SET zzpj=? WHERE YEAR=? AND qyid=?",
                    new Object[]{dj, year, qyId});
        }
        //不是个别企业单独评级
        if (map.get("singlePj") == null) {
            // 年度评级之后，修改khrwb_nd.khzt=1
            String sql = "UPDATE t_kp_khrwb_nd SET khzt=1 WHERE state=1 AND YEAR=?";
            Object[] values = new Object[]{year};
            jdbcTemplate.update(sql, values);
        }
        return null;
    }

    /*
     * 获取所有企业及驾驶员人数
     */
    private List queryQyList(String year, String qyid) throws Exception {
        //年度评级的时间差，默认为与年的12月31日比较
        String date = year + "-12-31";
        String sql = "SELECT khjg.tzhdf df,khjg.cftj, com.id_owner qyid, com.shortname,nvl(months_between(to_date('" + date + "','yyyy-mm-dd'),com.firstdate),0) mC,"
                + " nvl(jsy.jsycount,0) jsycount"
                + " from t_kp_qy_khjg khjg,t_base_com com"
                + " LEFT JOIN (SELECT qyid,COUNT(*) jsycount FROM t_kp_nd_jsykhjg WHERE YEAR=? GROUP BY qyid) jsy"
                + " ON to_char(com.id_owner)=jsy.qyid"
                + " WHERE khjg.qyid=to_char(com.id_owner)"
                + " AND khjg.year=? and khjg.state=1"
                + " and com.state=1 and com.id_owner NOT IN (" + SystemConstants.IDS + ")";
        Object[] values = null;
        if (qyid != null && qyid.length() > 0) {
            sql += " and khjg.qyid=? ";
            values = new Object[]{year, year, qyid};
        } else {
            values = new Object[]{year, year};
        }
        qyList = jdbcTemplate.queryForList(sql, values);
        return qyList;
    }

    /**
     * 得到修改评级的信息
     */
    public List<Map<String, Object>> getQykhDf(String syzbbbid, String qyid, String year) throws Exception {
        String sql = " select zbs.*,khjg.cftj  from (select jg.qyid QYID,jg.qyname QYNAME,jg.year YEAR, jg.ndbz ZNDBZ,zb.fzbmc FZBMC , zb.fkhfz FKHFZ, zb.fzbid FZBID ,zb.zzbmc ZZBMC ,zb.zkhfz ZKHFZ ,zb.zzbid ZZBID ,zb.zjjf ZJJF ,zb.zkhxz ZKHXZ ,jg.df DF  from  (" +
                " select f.zbmc fzbmc,f.khfz fkhfz,f.id fzbid,z.zbmc zzbmc,z.khfz zkhfz ,z.id zzbid ,z.jjf zjjf,z.khxz zkhxz from t_kp_zbgl f  left join t_kp_zbgl z"
                + "  on f.id=z.fid "
                + "  where f.sszbbbid= ? and f.sfzjd='0' and f.state='1' and z.state='1' order by f.khfz desc,f.zbmc  ) zb left join  t_kp_qynd_jg jg "
                + "  on zb.zzbid =  jg.zbid where jg.qyid = ? and jg.state = '1') zbs left join t_kp_qy_khjg khjg "
                + " on zbs.qyid = khjg.qyid where zbs.year = ? and khjg.year=?";
        Object[] values = new Object[]{syzbbbid, qyid, Integer.parseInt(year), Integer.parseInt(year)};
        List<Map<String, Object>> list = null;
        try {
            list = jdbcTemplate.queryForList(sql, values);
        } catch (Exception e) {
            LogUtil.getLogger().error("getQykhDf", e);
        }
        return list;
    }
}