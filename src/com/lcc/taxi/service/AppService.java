package com.lcc.taxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lcc.framework.util.ComUtils;
import com.lcc.taxi.dao.AppDao;
import com.lcc.taxi.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppService {

    @Autowired
    private AppDao appdao;

    /**
     * 添加app轨迹
     */
    public void appgjAdd(String appbs, Double lng, Double lat, String un, String user1, String user2) throws Exception {
        int i = appdao.appgjAdd(appbs, lng, lat, user1, user2);

    }

    /**
     * 查询设备轨迹信息
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List queryGjByAppbs(Map parm) throws Exception {
        return appdao.queryGjByAppbs(parm);
    }

    public List getbbmsforversion(String version) throws Exception {
//		List<Map<String, Object>> list = appdao.bbmsList(version);
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        Map<String, Object> m;
//		for(Map<String, Object> n : list){
//			m = new HashMap<String, Object>();
//			m.put("id", n.get("ID"));
//			m.put("bbms", n.get("BBMS"));
//			list2.add(m);
//		}
        return list2;
    }

    /**
     * 查询在一个圆内的所有车辆信息(没有考虑跨赤道，
     * 跨本初子午线情况，因为这个项目肯定只在国内使用)
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List queryVehiclesInCircle(Map parm) throws Exception {
        List res = new ArrayList();
        List vehicles = new ArrayList();
        //圆心
        double cLng = ComUtils.parseDouble(parm.get("lng"));//圆心经度
        double cLat = ComUtils.parseDouble(parm.get("lat"));//圆心纬度
        //半径
        double radius = ComUtils.parseDouble(parm.get("radius"));
        double lng, lat, distance;
        //vehicles.add(1);
        for (int i = 0; i < vehicles.size(); i++) {
            //确定如何查询车辆之后，这里需要修改
            lng = 0;
            lat = 0;
            /*lng = 116.31002426;
            lat = 39.94784523;*/
            distance = MapUtils.getDistance(lat, lng, cLat, cLng);
            if (distance < radius) {
                res.add(vehicles.get(i));
            }
        }
        return res;
    }

    public List getJglist(String jgid) throws Exception {
        return appdao.getJglist(jgid);
    }

    public List<Map<String, Object>> dtjcqysl(String jgid) throws Exception {
        return appdao.dtjcqysl(jgid);
    }

    public List<Map<String, Object>> getWfjlxq(String date) throws Exception {
        return appdao.getWfjlxq(date);
    }

    public List<Map<String, Object>> sswfjlzs(String date) throws Exception {
        return appdao.sswfjlzs(date);
    }

    public List<Map<String, Object>> kpsdsl(String date) throws Exception {
        return appdao.kpsdsl(date);
    }

    public List<Map<String, Object>> getKpsdxx(String date) throws Exception {
        return appdao.getKpsdxx(date);
    }

    public List<Map<String, Object>> getSblist() throws Exception {
        return appdao.getSblist();
    }
}
