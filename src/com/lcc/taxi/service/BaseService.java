package com.lcc.taxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.framework.constants.SystemConstants;
import com.lcc.framework.constants.UserConstants;
import com.lcc.framework.util.DateUtil;
import com.lcc.taxi.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BaseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getVehicleListByCphm(String cphm) {
        String sql = "select t1.ID_VEHICLE,t1.VEHICLEID,t2.ID_OWNER,t2.OWNERNAME,t2.OWNERADDR,t3.JSYSL from T_BASE_VEHICLE t1 inner join T_BASE_COM t2 on t1.ID_OWNER=t2.ID_OWNER left join ";
        sql += " (select emp.ID_VEHICLE,count(emp.ID_VEHICLE)as JSYSL from T_BASE_EMP emp,T_FK_BASE_EMP_EXT ext,T_BASE_VEHICLE vehicle where emp.ID_VEHICLE=vehicle.ID_VEHICLE and emp.ID_EMPLOYEE=ext.ID_EMPLOYEE and emp.WORKSTATE=? and ext.SFDBJSY=? and vehicle.VEHICLEID like ? group by emp.ID_VEHICLE)t3 on t1.ID_VEHICLE=t3.ID_VEHICLE";
        sql += " where t1.CCERTSTATE=? and t1.VEHICLEID like ? AND t2.id_owner NOT IN (" + SystemConstants.IDS + ")";
        Object[] values = new Object[]{UserConstants.CYZT_CY, UserConstants.YXBZ_FALSE, "%" + cphm + "%", UserConstants.CLYYZT_YY, "%" + cphm + "%"};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, values);
        return list;
    }

    /**
     * 获取企业申请入职驾驶员车辆选择列表
     */
    public List<Map<String, Object>> getVehicleListByCphmForRz(String cphm, Long idOwner) {
        String sql = "select t1.ID_VEHICLE,t1.VEHICLEID,t2.JSYSL from (select ID_VEHICLE,VEHICLEID,ID_OWNER from T_BASE_VEHICLE where ID_OWNER=? and CCERTSTATE=? and VEHICLEID like ?)t1 left join ";
        sql += "(select ID_VEHICLE,count(ID_VEHICLE)as JSYSL from T_BASE_EMP emp,T_FK_BASE_EMP_EXT ext where emp.ID_EMPLOYEE=ext.ID_EMPLOYEE and emp.WORKSTATE=? and ext.SFDBJSY=? and emp.ID_OWNER=? group by ID_VEHICLE)t2 on t1.ID_VEHICLE=t2.ID_VEHICLE";
        Object[] values = new Object[]{idOwner, UserConstants.CLYYZT_YY, "%" + cphm + "%", UserConstants.CYZT_CY, UserConstants.YXBZ_FALSE, idOwner};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, values);
        return list;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TBaseEmp findBaseEmpById(Long idEmployee) {
        return TBaseEmp.get(TBaseEmp.class, idEmployee);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public boolean empExistByPtidcard(String ptidcard) {
        String sql = "select count(0) from T_BASE_EMP where PTIDCARD=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ptidcard}, Integer.class) > 0;
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TBaseEmp findEmpByPtidcard(String ptidcard) {
        String hql = "from TBaseEmp where ptidcard=?";
        List<TBaseEmp> list = TBaseEmp.find(hql, ptidcard);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TBaseCom findComByOwnername(String ownername) {
        String hql = "from TBaseCom where ownername=?";
        List<TBaseCom> list = TBaseCom.find(hql, ownername);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TBaseVehicle findVehicleByCphm(String cphm) {
        String hql = "from TBaseVehicle where vehicleid=?";
        List<TBaseVehicle> list = TBaseCom.find(hql, cphm);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 操作员密码修改
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TBaseEmpExt findBaseEmpExtById(Long idEmployee) {
        return TBaseEmpExt.get(TBaseEmpExt.class, idEmployee);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TBaseCom findBaseComById(Long idOwner) {
        return TBaseCom.get(TBaseCom.class, idOwner);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TBaseVehicle findBaseVehicleById(Long idVehicleid) {
        return TBaseVehicle.get(TBaseVehicle.class, idVehicleid);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TBaseEmpFwbg> findEmpFwbgListByIdEmployee(Long idEmployee) {
        String hql = "from TBaseEmpFwbg where idEmployee=? order by fwkssj desc";
        return TBaseEmpFwbg.find(hql, idEmployee);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TBaseCom> getDBComList() {
        List<TBaseCom> comList = TBaseCom.find(" from TBaseCom where state=?", new Object[]{UserConstants.QYJYZT_YY});
        return comList;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TBaseCom> getComList() {
        List<TBaseCom> comList = TBaseCom.find(" from TBaseCom where state=?", new Object[]{UserConstants.QYJYZT_YY});
        return comList;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TBaseEmp> queryEmpByAuto(Long idOwner, String name) {
        List<TBaseEmp> empList = TBaseEmp.find("select emp from TBaseEmp emp,TBaseEmpExt t1 where t1.idEmployee=emp.idEmployee and (t1.sfdbjsy=? or t1.sfdbjsy is null) and emp.name like ? and emp.idOwner=?",
                new Object[]{UserConstants.YXBZ_FALSE, name + "%", idOwner});
        return empList;
    }

    /**
     * 通过身份证号查询入职驾驶员基本信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Object[]> queryRzjsyByPtidcard(String ptidcard, String name) {
        String hql = " from TBaseEmp t1,TBaseEmpExt t2 where t1.idEmployee=t2.idEmployee and t1.ptidcard=? and t1.name=?";
        List<Object[]> empList = TBaseEmp.find(hql, new Object[]{ptidcard, name});
        return empList;
    }

    /**
     * 通过身份证号查询驾驶员基本信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Object[]> queryJsyByPtidcard(String ptidcard, String name) {
        String hql = " from TBaseEmp t1,TBaseEmpExt t2 where t1.idEmployee=t2.idEmployee and t1.ptidcard=? and t1.name=?";
        List<Object[]> empList = TBaseEmp.find(hql, new Object[]{ptidcard, name});
        return empList;
    }

    /**
     * 通过身份证号查询入职驾驶员基本信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Object[]> queryCljsyByPtidcard(String ptidcard) {
        String hql = " from TBaseEmp t1,TBaseEmpExt t2 where t1.idEmployee=t2.idEmployee and t2.proxzcl=? and t1.ptidcard=?";
        List<Object[]> empList = TBaseEmp.find(hql, new Object[]{UserConstants.JSY_TYPE_CL, ptidcard});
        return empList;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Object[]> queryEmpByIckh(String ickh) {
        String hql = " from TBaseEmp t1,TBaseEmpExt t2 where t1.idEmployee=t2.idEmployee and t2.ickh=?";
        List<Object[]> empList = TBaseEmp.find(hql, new Object[]{ickh});
        return empList;
    }

    /**
     * 通过驾驶员姓名查询驾驶员当前驾驶车辆   不能是顶班驾驶员
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Map<String, Object>> queryEmpAndVehicleByEmpname(String name, Long idOwner) {
        String sql = "select t1.NAME,t1.PTIDCARD,t1.ID_EMPLOYEE,t2.ID_VEHICLE,t2.VEHICLEID,to_char(t3.ZHSCSJ,'YYYY-MM-DD')as ZHSCSJ from T_BASE_EMP t1 inner join T_FK_BASE_EMP_EXT t3 on t1.ID_EMPLOYEE=t3.ID_EMPLOYEE left join T_BASE_VEHICLE t2 on t1.ID_VEHICLE=t2.ID_VEHICLE where t1.NAME like ? and (t3.sfdbjsy=? or t3.sfdbjsy is null) and t1.ID_OWNER=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{name + "%", UserConstants.YXBZ_FALSE, idOwner});
        return list;
    }

    /**
     * 通过驾驶员姓名查询驾驶员列表
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Map<String, Object>> queryEmpByNameJsydx(String name) {
        String sql = "select t1.NAME,t1.PTIDCARD,t1.ID_EMPLOYEE,t1.WORKSTATE,t1.ID_OWNER,t2.ICKH from T_BASE_EMP t1,T_FK_BASE_EMP_EXT t2 where t1.ID_EMPLOYEE=t2.ID_EMPLOYEE and t1.NAME like ? ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{name + "%"});
        return list;
    }

    /**
     * 通过IC卡物理卡号来查询驾驶员信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Map<String, Object> queryEmpByIckh(String ickh, Long idOwner) {
        String sql = "select t1.NAME,t1.PTIDCARD,t1.ID_EMPLOYEE,t2.ID_VEHICLE,t2.VEHICLEID from T_BASE_EMP t1 inner join T_FK_BASE_EMP_EXT t3 on t1.ID_EMPLOYEE=t3.ID_EMPLOYEE left join T_BASE_VEHICLE t2 on t1.ID_VEHICLE=t2.ID_VEHICLE where t3.ICKH=? and t1.ID_OWNER=?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[]{ickh, idOwner});
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 培训机构主页查询数据
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Map<String, Object> queryHyztqk() {
        //企业总数
        String sql = "select count(0) from T_BASE_COM and ID_OWNER NOT IN (?) ";
        int qysl = jdbcTemplate.queryForObject(sql, new Object[]{SystemConstants.IDS}, Integer.class);

        //车辆总数
        sql = "select count(0) from T_BASE_VEHICLE where CCERTSTATE= ?";
        int clzs = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.CLYYZT_YY}, Integer.class);

        //新增驾驶员总数（年度）
        String firstday = DateUtil.getStringDate("yyyy") + "0101";
        String lastday = DateUtil.getStringDate("yyyy") + "1231 23:59:59";
        sql = "select count(0) from T_FK_BASE_EMP_EXT where PROXZCL= ? and FKSJ >=to_date(?,'YYYYMMDD') and FKSJ<=to_date(?,'YYYYMMDD HH24:MI:SS')";
        int xzjsyzs = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.JSY_TYPE_XZ, firstday, lastday}, Integer.class);

        //审核通过
        sql = "select count(0) from T_FK_BASE_EMP_EXT where PROXZCL=? and SHZT=? and SHJG=?";
        int xzjsyshtg = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.JSY_TYPE_XZ, UserConstants.YXBZ_TRUE, UserConstants.YXBZ_TRUE}, Integer.class);

        //驾驶员总数
        sql = "select count(0) from T_BASE_EMP where WORKSTATE in (?,?)";
        int jsyzs = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.CYZT_CY, UserConstants.CYZT_DY}, Integer.class);

        //已发卡数
        sql = "select count(0) from T_FK_BASE_EMP_EXT t1,T_BASE_EMP t2 where t1.ID_EMPLOYEE=t2.ID_EMPLOYEE ";
        sql += " and t2.WORKSTATE in (?,?) and t1.SFYFK=?";
        int jsyyfks = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.YXBZ_TRUE, UserConstants.CYZT_DY}, Integer.class);

        //未发卡数
        int jsywfks = jsyzs - jsyyfks;

        //上岗数
        sql = "select count(0) from T_BASE_EMP where WORKSTATE= ?";
        int jsysgs = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.CYZT_CY}, Integer.class);

        //待业数
        int jsydys = jsyzs - jsysgs;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("qysl", qysl);
        map.put("clzs", clzs);
        map.put("xzjsyzs", xzjsyzs);
        map.put("xzjsyshtg", xzjsyshtg);
        map.put("jsywfks", jsywfks);
        map.put("jsysgs", jsysgs);
        map.put("jsydys", jsydys);
        map.put("jsyzs", jsyzs);
        map.put("jsyyfks", jsyyfks);
        return map;
    }


    /**
     * 企业主页面数据查询
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Map<String, Object> queryQyztqk(Long idOwner) {
        //车辆总数
        String sql = "select count(0) from T_BASE_VEHICLE where CCERTSTATE=? and ID_OWNER=?";
        int clzs = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.CLYYZT_YY, idOwner}, Integer.class);

        //驾驶员总数
        sql = "select count(0) from T_BASE_EMP where WORKSTATE in (?,?) and ID_OWNER=?";
        int jsyzs = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.CYZT_CY, UserConstants.CYZT_DY, idOwner}, Integer.class);

        //顶班驾驶员总数
        sql = "select count(0) from T_FK_BASE_EMP_EXT t1,T_BASE_EMP t2 where t1.ID_EMPLOYEE=t2.ID_EMPLOYEE ";
        sql += " and t2.WORKSTATE in (?,?) and t1.SFDBJSY=? and t2.ID_OWNER=?";
        int dbjsyzs = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.CYZT_CY, UserConstants.CYZT_DY, UserConstants.YXBZ_TRUE, idOwner}, Integer.class);

        //服务资格证即将到期数
        sql = "select count(0) from T_FK_BASE_EMP_EXT t1,T_BASE_EMP t2 where t1.ID_EMPLOYEE=t2.ID_EMPLOYEE ";
        sql += " and t2.WORKSTATE in (?,?) and t1.FWZGZYXQ<=? and t2.ID_OWNER=?";
        int jjdqjsysl = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.CYZT_CY, UserConstants.CYZT_DY,
                DateUtil.toSqlTimestamp(DateUtil.addDay(new Date(), 31)), idOwner}, Integer.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clzs", clzs);
        map.put("jsyzs", jsyzs);
        map.put("dbjsyzs", dbjsyzs);
        map.put("jjdqjsysl", jjdqjsysl);
        return map;
    }


    /**
     * 行管部门主页查询数据
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Map<String, Object> queryHyztqkHgbm() {
        //企业总数
        String sql = "select count(0) from T_BASE_COM ID_OWNER NOT IN (?)";
        int qysl = jdbcTemplate.queryForObject(sql,new Object[]{SystemConstants.IDS },Integer.class);

        //车辆总数
        sql = "select count(0) from T_BASE_VEHICLE where CCERTSTATE = ?";
        int clzs = jdbcTemplate.queryForObject(sql,new Object[]{UserConstants.CLYYZT_YY},Integer.class);

        //新增驾驶员总数（年度）
        String firstday = DateUtil.getStringDate("yyyy") + "0101";
        String lastday = DateUtil.getStringDate("yyyy") + "1231 23:59:59";
        sql = "select count(0) from T_FK_BASE_EMP_EXT where PROXZCL=? and FKSJ >=? and FKSJ<=?";
        int xzjsyzs = jdbcTemplate.queryForObject(sql,new Object[]{UserConstants.JSY_TYPE_XZ ,firstday,lastday},Integer.class);


        //驾驶员总数
        sql = "select count(0) from T_BASE_EMP where WORKSTATE in (?,?)";
        int jsyzs = jdbcTemplate.queryForObject(sql,new Object[]{UserConstants.CYZT_CY,UserConstants.CYZT_DY},Integer.class);

        //已发卡数
        sql = "select count(0) from T_FK_BASE_EMP_EXT t1,T_BASE_EMP t2 where t1.ID_EMPLOYEE=t2.ID_EMPLOYEE ";
        sql += " and t2.WORKSTATE in ('?,?) and t1.SFYFK=?";
        int jsyyfks = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.CYZT_CY ,UserConstants.CYZT_DY,UserConstants.YXBZ_TRUE},Integer.class);

        //未发卡数
        int jsywfks = jsyzs - jsyyfks;

        //上岗数
        sql = "select count(0) from T_BASE_EMP where WORKSTATE=?";
        int jsysgs = jdbcTemplate.queryForObject(sql,new Object[]{UserConstants.CYZT_CY},Integer.class);

        //待业数
        int jsydys = jsyzs - jsysgs;

        //男女驾驶员
        sql = "select ptsex as PTSEX,count(ptsex)as SL from T_BASE_EMP where WORKSTATE in ('" + UserConstants.CYZT_CY + "','" + UserConstants.CYZT_DY + "') group by ptsex";
        List<Map<String, Object>> listSex = jdbcTemplate.queryForList(sql);

        //驾驶员按年龄
        sql = "select count(0) from T_BASE_EMP where WORKSTATE in ('" + UserConstants.CYZT_CY + "','" + UserConstants.CYZT_DY + "') and BIRTHDAY>=?";
        sql += " union all select count(0) from T_BASE_EMP where WORKSTATE in ('" + UserConstants.CYZT_CY + "','" + UserConstants.CYZT_DY + "') and BIRTHDAY<? and BIRTHDAY>=?";
        sql += " union all select count(0) from T_BASE_EMP where WORKSTATE in ('" + UserConstants.CYZT_CY + "','" + UserConstants.CYZT_DY + "') and BIRTHDAY<? and BIRTHDAY>=?";
        sql += " union all select count(0) from T_BASE_EMP where WORKSTATE in ('" + UserConstants.CYZT_CY + "','" + UserConstants.CYZT_DY + "') and BIRTHDAY<?";
        Date now = new Date();
        Date date30 = DateUtil.addYear(now, -30);
        Date date40 = DateUtil.addYear(now, -40);
        Date date50 = DateUtil.addYear(now, -50);
        List<Integer> listJsynl = jdbcTemplate.queryForList(sql, new Object[]{date30, date30, date40, date40, date50, date50}, Integer.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("qysl", qysl);
        map.put("clzs", clzs);
        map.put("xzjsyzs", xzjsyzs);
        map.put("jsywfks", jsywfks);
        map.put("jsysgs", jsysgs);
        map.put("jsydys", jsydys);
        map.put("jsyzs", jsyzs);
        map.put("jsyyfks", jsyyfks);
        map.put("listSex", listSex);
        map.put("listJsynl", listJsynl);
        return map;
    }


    /**
     * 通过操作员ID获取操作员菜单权限
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> getCzyMenuids(Integer czyid) {
        String sql = "select MENUID from T_FK_BASE_CZYMENU where YXBZ=? and CZYID=?";
        return jdbcTemplate.queryForList(sql, String.class, new Object[]{UserConstants.YXBZ_TRUE, czyid});
    }


    /**
     * 通过卡号判断 卡片是否被使用过
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public int cardnoValid(String cardno) {
        String sql = "select count(0) from T_FK_BUSI_BLACKLIST where YXBZ=? and ICKH=?";
        int result = jdbcTemplate.queryForObject(sql, new Object[]{UserConstants.YXBZ_TRUE, cardno},Integer.class);
        if (result > 0) {
            return UserConstants.CARDTYPE_HMD;
        }
        sql = "select count(0) from T_FK_BASE_EMP_EXT where ICKH=?";
        result = jdbcTemplate.queryForObject(sql, new Object[]{cardno},Integer.class);
        if (result > 0) {
            return UserConstants.CARDTYPE_YSY;
        }

        return UserConstants.CARDTYPE_ZC;
    }

    /**
     * 通过银行卡号和身份证号判断 银行卡号是否被其它人使用
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public String bankCardnoValid(String bankcardno, String ptidcard) {
        String sql = "select t.NAME,t.PTIDCARD from T_BASE_EMP t,T_FK_BASE_EMP_EXT t1 where t.ID_EMPLOYEE=t1.ID_EMPLOYEE and t.PTIDCARD<>? and t1.KHZH=? ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, ptidcard, bankcardno);
        String retStr = "";
        if (list.size() > 0) {
            Map<String, Object> map = list.get(0);
            retStr = map.get("NAME") + "(" + map.get("PTIDCARD") + ")";
        }
        return retStr;
    }


    /**
     * 通过银行卡号和身份证号判断 银行卡号是否被其它人使用
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TBaseEmp> queryEmpByIdvehicle(Long idvehicle) {
        String hql = "from TBaseEmp where idVehicle=?";
        return TBaseEmp.find(hql, idvehicle);
    }

    /**
     * 根据用户的roleId和SystemId查询权限编号，隐藏menu
     */
    public List queryRolePriv(Jcry user) {
        List res = null;
        try {
            String sql = "SELECT to_char(rp.privilege) from t_base_rolePriv rp WHERE rp.role_id=? AND rp.privilege_visible='1' AND rp.privilege_enabled='1' AND rp.sysflag=?";
            Object[] values = new Object[]{user.getRole_id(), user.getSystmid()};
            if (user.getId().equals("11111111")) {
                sql = "SELECT to_char(rp.privilege) from t_base_privileges rp WHERE rp.sysflag=?";
                values = new Object[]{user.getSystmid()};
            }
            res = jdbcTemplate.queryForList(sql, String.class, values);
        } catch (Exception e) {
            res = new ArrayList();
        }
        return res;
    }
}
