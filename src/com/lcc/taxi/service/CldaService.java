package com.lcc.taxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.lcc.framework.util.DataTypeUtil;
import com.lcc.taxi.bean.CarInfo;
import com.lcc.taxi.bean.Clda;
import com.lcc.taxi.bean.Clxx;
import com.lcc.taxi.dao.CldaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CldaService {

    @Autowired
    private CldaDao cldadao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 得到车辆档案信息
     */
    @Transactional(rollbackFor=Exception.class)
    public List<Clda> getCldaInfoService(String cphm) throws Exception{
        List<Clda> list = cldadao.getCldaInfo(cphm);
        if(list.size() != 0){
            Clda clda = list.get(0);
            List<Map<String,Object>> temp = cldadao.getNameofEmp(clda.getId_vehicle());
            List<Map<String,Object>> newtemp=new ArrayList<>();
            Map<String,Object> newmap=null;
            for(Map<String,Object> m:temp){
                newmap=new HashMap<>();
                for(Map.Entry<String, Object> set:m.entrySet()){
                    newmap.put(set.getKey(), set.getValue()!=null?set.getValue():"");
                }
                newtemp.add(newmap);
            }

            list.get(0).setDriver(newtemp);
            //查询当班司机
            List<Map<String,Object>> db=cldadao.queryDbsj(clda.getId_vehicle());
            if(db!=null&& db.size()!=0){
                list.get(0).setDr(db.get(0));
            }
        }
        return list;
    }

    /**
     * 查询所有车辆设备安装信息
     */
    public List<CarInfo> getAllCarInfo() {
        String sql = "select tbd.id_vehicle as id_vehicle,tbd.vehicleid as vehicle,tbd.dev_id as id_dev,tbd.sim_no as sim,tbd.employeeid as driverid, tgl.gpstime as gpstime,tgl.speed as speed,tgl.ORIENTATION as direction,tgl.latitude as lat,tgl.longitude as lon,tgl.devalarmflag as alarmflag,tgl.devrunstate as state from T_BASE_DEV tbd left join gps.T_GPS_LAST tgl on tbd.dev_id=tgl.dev_id";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        List<CarInfo> carList = new ArrayList<CarInfo>();
        for (int i = 0; i < list.size(); i++) {
            CarInfo car = new CarInfo();
            Map<String, Object> map = list.get(i);
            car.setId_vehicle(DataTypeUtil.toInt(map.get("id_vehicle")));
            car.setVehicle(DataTypeUtil.toNotNullString(map.get("vehicle")));
            car.setId_dev(DataTypeUtil.toInt(map.get("id_dev")));
            car.setSim(DataTypeUtil.toNotNullString(map.get("sim")));
            car.setDriverid(DataTypeUtil.toNotNullString(map.get("driverid")));
            car.setGpstime((Date) map.get("gpstime"));
            car.setSpeed(DataTypeUtil.toDouble(map.get("speed"), true));
            car.setDirection(DataTypeUtil.toInt(map.get("direction"), 0));
            car.setLat(DataTypeUtil.toDouble(map.get("lat"), true));
            car.setLon(DataTypeUtil.toDouble(map.get("lon"), true));
            car.setAlarmFlag(DataTypeUtil.toLong(map.get("alarmflag"), 0));
            car.setState(DataTypeUtil.toLong(map.get("state"), 0));
            carList.add(car);
        }
        return carList;
    }

    /**
     * 查询车辆当班驾驶员
     */
    public List<CarInfo> getCurEmpOnCar() {
        String sql = "select tbe.id_vehicle,tbe.employeecode as driverid,name as driver,tel from t_base_emp tbe, t_base_dev tbd where tbe.employeecode=tbd.employeeid and tbd.sign_stat='0' and tbe.workstate=1";
        List<CarInfo> carempList = new ArrayList<CarInfo>();
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            CarInfo carinfo = new CarInfo();
            carinfo.setId_vehicle(DataTypeUtil.toInt(map.get("id_vehicle")));
            carinfo.setDriverid(DataTypeUtil.toNotNullString(map.get("driverid")));
            carinfo.setDriver(DataTypeUtil.toNotNullString(map.get("driver")));
            carinfo.setTel(DataTypeUtil.toNotNullString(map.get("tel")));
            carempList.add(carinfo);
        }
        return carempList;
    }

    public List<Clxx> getCldaInfoServiceCLJS(String cph) throws Exception{
        return  cldadao.getCldaInfoServiceCLJS(cph);
    }

}
