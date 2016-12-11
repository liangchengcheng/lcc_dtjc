package com.lcc.taxi.service;

import java.util.List;
import com.lcc.taxi.bean.Log;
import com.lcc.taxi.bean.Version;
import com.lcc.taxi.dao.VersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BbxxService {

    @Autowired
    private JdbcTemplate jdbctemplate;

    @Autowired
    private VersionDao versiondao;

    /**
     * 发布
     */
    public int publish(String id) throws Exception{
        return versiondao.publish(id);
    }

    /**
     * 保存版本信息
     */
    public void saveVersionInfo(Version ver,Log log) throws Exception {
        ver.save();
        log.save();
    }

    /**
     * 根据id获得版本信息
     */
    public List<Version> getVersionByid(String id) throws Exception {
        String sql = "select t.id,t.durl,t.fbsj,t.bbms,t.version,t.sfsj,t.code,t.czyid,t.xgsj,state from t_jc_version t where id = ? ";
        Object[] value = new Object[] { id };
        List<Version> list = jdbctemplate.query(sql, value, new BeanPropertyRowMapper<Version>(Version.class));
        return list;
    }
    /**
     * 得到所有版本信息
     */
    public List<Version> getVersion() throws Exception {
        List<Version> list = versiondao.getVersionDao();
        return list;
    }

    /**
     * 更新版本信息
     */
    public void updateVersionInfo(Version ver,Log log) throws Exception {
        ver.update();
        log.save();
    }
    public void fbbb(Log log) throws Exception {
        log.save();
    }
    /**
     * 删除
     */
    public void deleteVersionInfo(String id,Log log) throws Exception {
        log.save();
        String sql = "delete from t_jc_version where id = ?";
        Object[] value = new Object[] { id };
        jdbctemplate.update(sql, value);
    }
}
