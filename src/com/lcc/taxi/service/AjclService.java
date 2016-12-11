package com.lcc.taxi.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.lcc.taxi.bean.*;
import com.lcc.taxi.dao.AjclDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AjclService {

    @Autowired
    private AjclDao ajclDao;

    public int ajclDel(String id, String sfzh, String kf, String year, String zfzt) throws Exception {
        List<Map<String, Object>> ajlist = null;
        EmpNdkh ndkh = null;
        int dqkf = Integer.valueOf(kf);
        if (Integer.valueOf(zfzt) == 2) {
            ajlist = ajclDao.getNfkhBySfz(sfzh, year);
            ndkh = new EmpNdkh();
            if (ajlist == null || ajlist.size() == 0) {
            } else {
                Map<String, Object> map = ajlist.get(0);
                ndkh.setDqkf(Integer.valueOf(map.get("DQKF").toString()) - dqkf);
                ndkh.setSumkf(Integer.valueOf(map.get("SUMKF").toString()) - dqkf);
                ndkh.setId(map.get("ID") + "");
                //ndkh.setLastkf(dqkf);
                ndkh.setIslock(map.get("ISLOCK").toString());
                //ndkh.setLasttime(wzjl.getJasj());
                ndkh.setPtidcard(sfzh);
                ndkh.setYear(year);
                ajclDao.udateNdkh(ndkh);
            }
        } else if (Integer.valueOf(zfzt) == 3) {
            List<Map<String, Object>> list = ajclDao.getgl(id);
            if (list != null && list.size() != 0) {
                Map<String, Object> map = list.get(0);
                ajlist = ajclDao.getNfkhBySfz(map.get("sfzhm").toString(), year);
                ndkh = new EmpNdkh();
                if (ajlist != null && ajlist.size() != 0) {
                    sfzh = map.get("sfzhm").toString();
                    dqkf = Integer.valueOf(map.get("kf").toString());
                    Map<String, Object> m = ajlist.get(0);
                    ndkh.setDqkf(Integer.valueOf(m.get("DQKF").toString()) - dqkf);
                    ndkh.setSumkf(Integer.valueOf(m.get("SUMKF").toString()) - dqkf);
                    ndkh.setId(m.get("ID") + "");
                    //ndkh.setLastkf(dqkf);
                    ndkh.setIslock(m.get("ISLOCK").toString());
                    //ndkh.setLasttime(wzjl.getJasj());
                    ndkh.setPtidcard(sfzh);
                    ndkh.setYear(year);
                    ajclDao.udateNdkh(ndkh);
                }
            }
        }

        return ajclDao.ajclDel(id);
    }

    public int ajclresult(String id, Log log) throws Exception {
        return ajclDao.ajclresult(id, log);
    }

    public int szlr(String id, String lrr, String lrsj, Log log) throws Exception {
        return ajclDao.szlr(id, lrr, lrsj, log);
    }

    public List<Map<String, Object>> wzxwlist(String ajh) throws Exception {
        return ajclDao.wzxwlist(ajh);
    }

    public List<Map<String, Object>> ycxht(String wzjlid) throws Exception {
        return ajclDao.ycxht(wzjlid);
    }

    public Boolean addycx(Wzjlycx ycx, Log log) throws Exception {
        return ajclDao.addycx(ycx, log);
    }

    public int updateycx(Wzjlycx ycx, Log log) throws Exception {
        return ajclDao.updateycx(ycx, log);
    }

    public List<Map<String, Object>> yjawzxw(String ajh) throws Exception {
        return ajclDao.yjawzxw(ajh);
    }

    public List<Map<String, Object>> wfcd(String id) throws Exception {
        return ajclDao.wfcd(id);
    }

    public List<Map<String, Object>> wzjlxwchax(String ajh, String wzxwid) throws Exception {
        return ajclDao.wzjlxwchax(ajh, wzxwid);
    }

    public int wzjlxwxg(String ajh, String wzxwid, String cd) throws Exception {
        return ajclDao.wzjlxwxg(ajh, wzxwid, cd);
    }

    public Boolean wzjlxwadd(String ajh, String wzxwid, String cd) throws Exception {
        return ajclDao.wzjlxwadd(ajh, wzxwid, cd);
    }

    public List<Map<String, Object>> yjawzxw2(String ajh) throws Exception {
        return ajclDao.yjawzxw2(ajh);
    }

    public List<Map<String, Object>> jsysfz(String jsyid) throws Exception {
        return ajclDao.jsysfz(jsyid);
    }

    public int yjadsr(List<Wzjlxw> list, Wzjl wzjl, JsySskf jsysskf, String sfzh, String ajh, String zfs) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(wzjl.getJasj());
        String year = c.get(Calendar.YEAR) + "";
        List<Map<String, Object>> ajlist = ajclDao.getNfkhBySfz(sfzh, year);
        EmpNdkh ndkh = new EmpNdkh();
        int dqkf = Integer.valueOf(wzjl.getKf() != null ? wzjl.getKf() : "0");
        if (ajlist == null || ajlist.size() == 0) {
            ndkh.setDqkf(dqkf);
            //卡片锁定相关？？？？？？？？？？
            ndkh.setIslock("0");
            ndkh.setLastkf(dqkf);
            ndkh.setLasttime(wzjl.getJasj());
            ndkh.setPtidcard(sfzh);
            ndkh.setSumkf(dqkf);
            ndkh.setYear(year);
            ajclDao.insertNdkh(ndkh);
        } else {
            Map<String, Object> map = ajlist.get(0);
            ndkh.setDqkf(dqkf + Integer.valueOf(map.get("DQKF").toString()));
            ndkh.setSumkf(dqkf + Integer.valueOf(map.get("SUMKF").toString()));
            ndkh.setId(map.get("ID") + "");
            ndkh.setLastkf(dqkf);
            ndkh.setIslock(map.get("ISLOCK").toString());
            ndkh.setLasttime(wzjl.getJasj());
            ndkh.setPtidcard(sfzh);
            ndkh.setYear(year);
            ajclDao.udateNdkh(ndkh);
        }

        return ajclDao.yjadsr(list, wzjl, jsysskf, sfzh, ajh, zfs);
    }

    public int yjadsrupd(List<Wzjlxw> list, YjaDsr yjadsr) throws Exception {
        return ajclDao.yjadsrupd(list, yjadsr);
    }

    public List<Map<String, Object>> yjadsrhtfs(String dsrid) throws Exception {
        return ajclDao.yjadsrhtfs(dsrid);
    }

    public List<Map<String, Object>> yjadsrhtfs_ck(String dsrid) throws Exception {
        return ajclDao.yjadsrhtfs_ck(dsrid);
    }

    public List<Map<String, Object>> yjafdsrhtfs_ck(String dsrid) throws Exception {
        return ajclDao.yjafdsrhtfs_ck(dsrid);
    }

    public List<Map<String, Object>> yjawzxwfdsrhtfs(String dsrid) throws Exception {
        return ajclDao.yjawzxwfdsrhtfs(dsrid);
    }

    public int yjafdsr(List<Wzjlxw> list, Wzjl wzjl, Wzjl jl, JsySskf jsysskf, List<Wzjlxw> list2) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(wzjl.getJasj());
        String year = c.get(Calendar.YEAR) + "";
        String sfzh = wzjl.getIdcard();
        List<Map<String, Object>> ajlist = ajclDao.getNfkhBySfz(sfzh, year);
        EmpNdkh ndkh = new EmpNdkh();
        int dqkf = Integer.valueOf(wzjl.getKf() != null ? wzjl.getKf() : "0");
        if (ajlist == null || ajlist.size() == 0) {
            ndkh.setDqkf(dqkf);
            //卡片锁定相关？？？？？？？？？？
            ndkh.setIslock("0");
            ndkh.setLastkf(dqkf);
            ndkh.setLasttime(wzjl.getJasj());
            ndkh.setPtidcard(sfzh);
            ndkh.setSumkf(dqkf);
            ndkh.setYear(year);
            ajclDao.insertNdkh(ndkh);
        } else {
            Map<String, Object> map = ajlist.get(0);
            ndkh.setDqkf(dqkf + Integer.valueOf(map.get("DQKF").toString()));
            ndkh.setSumkf(dqkf + Integer.valueOf(map.get("SUMKF").toString()));
            ndkh.setId(map.get("ID") + "");
            ndkh.setLastkf(dqkf);
            ndkh.setIslock(map.get("ISLOCK").toString());
            ndkh.setLasttime(wzjl.getJasj());
            ndkh.setPtidcard(sfzh);
            ndkh.setYear(year);
            ajclDao.udateNdkh(ndkh);
        }
        return ajclDao.yjafdsr(list, wzjl, jl, jsysskf, list2);
    }

    public List<Map<String, Object>> yjafdsrht(String wzjlid) throws Exception {
        return ajclDao.yjafdsrht(wzjlid);
    }

    public int yjafdsrupd(List<Wzjlxw> list, YjaFdsr yjafdsr) throws Exception {
        return ajclDao.yjafdsrupd(list, yjafdsr);
    }

    public List<Map<String, Object>> yjadsrht(String wzjlid) throws Exception {
        return ajclDao.yjadsrht(wzjlid);
    }

    public List<Map<String, Object>> yjafdsrjbxxck(String wzjlid) throws Exception {
        return ajclDao.yjafdsrjbxxck(wzjlid);
    }

    public List<Wzjl> fdsrja(String wzjlid) throws Exception {
        return ajclDao.fdsrja(wzjlid);
    }

    public List<Map<String, Object>> searchsfzh(String sfzh) throws Exception {
        return ajclDao.searchsfzh(sfzh);
    }

    public List<Map<String, Object>> searchsfzh_fdsr(String sfzh) throws Exception {
        return ajclDao.searchsfzh_fdsr(sfzh);
    }

    public List<Map<String, Object>> searchsfzhfs(String sfzh) throws Exception {
        return ajclDao.searchsfzhfs(sfzh);
    }

    public List<Map<String, Object>> searchcph(String cph) throws Exception {
        return ajclDao.searchcph(cph);
    }

    public List<Map<String, Object>> zfry_combobox(String jgid) throws Exception {
        return ajclDao.zfry_combobox(jgid);
    }

    public int add_aj(List<Wzjlxw> list, Wzjl wzjl) throws Exception {
        return ajclDao.add_aj(list, wzjl);
    }

    public List<Map<String, Object>> ajclsfzhauto(String sfzh) throws Exception {
        return ajclDao.ajclsfzhauto(sfzh);
    }

    public List<Map<String, Object>> getgl(String glid) throws Exception {
        return ajclDao.getgl(glid);
    }

    public List<Map<String, Object>> getglaj(String id) throws Exception {
        return ajclDao.getglaj(id);
    }

    public boolean jskp(String id, String jsid) throws Exception {
        return ajclDao.jskp(id, jsid);
    }
}

