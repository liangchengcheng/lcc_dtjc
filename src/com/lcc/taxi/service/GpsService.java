package com.lcc.taxi.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.lcc.framework.bean.Ssgj;
import com.lcc.framework.constants.MapConstants;
import com.lcc.framework.constants.SystemConstants;
import com.lcc.framework.util.EvilTransform;
import com.lcc.taxi.dao.GpsDao;
import com.lcc.taxi.dao.UserDao;
import com.lcc.taxi.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GpsService {

    @Autowired
    private GpsDao gpsDao;
    @Autowired
    private UserDao userDao;
    private static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

    /**
     * 周边车辆
     */
    public List<Ssgj> getZbcl(Double lat, Double lon, String jl)
            throws Exception {
        double[] newlatlon = EvilTransform.BD09ToGCJ02(lat, lon);
        lat = newlatlon[1];
        lon = newlatlon[0];
        List<Ssgj> ssgj = new ArrayList<>();
        // 先计算查询点的经纬度范围
        double r = 6371;// 地球半径千米
        double dis = Double.valueOf(jl);// 0.5千米距离
        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(lat * Math.PI / 180));
        dlng = dlng * 180 / Math.PI;// 角度转为弧度
        double dlat = dis / r;
        dlat = dlat * 180 / Math.PI;
        double minlat = lat - dlat;
        double maxlat = lat + dlat;
        double minlng = lon - dlng;
        double maxlng = lon + dlng;
        // LogUtil.getLogger().info("minlat:"+minlat+"maxlat:"+maxlat+"minlng:"+minlng+"maxlng:"+maxlng);
        Ssgj ss = null;
        List<Map<String, Object>> list = gpsDao.getZbcl(minlat, maxlat, minlng,
                maxlng);
        double d = dis * 1000;

        for (Map m : list) {

            Double dist = MapUtils.getDistance(lat, lon,
                    Double.valueOf(m.get("LATITUDE").toString()),
                    Double.valueOf(m.get("LONGITUDE").toString()));
            if (dist <= d) {

                ss = new Ssgj();
                ss.setCph(m.get("VEHICLEID") != null ? m.get("VEHICLEID")
                        .toString() : "");
                ss.setFx(m.get("ORIENTATION") != null ? m.get("ORIENTATION")
                        .toString() : "");
                //ss.setJd(m.get("LONGITUDE") != null ? Double.valueOf(m.get(
                //"LONGITUDE").toString()) : 0);
                //ss.setWd(m.get("LATITUDE") != null ? Double.valueOf(m.get(
                //"LATITUDE").toString()) : 0);
                double latt = Double.valueOf(m.get("LATITUDE") != null ? m.get("LATITUDE").toString() : "0");
                double lonn = Double.valueOf(m.get("LONGITUDE") != null ? m.get("LONGITUDE").toString() : "0");
                double[] dd = EvilTransform.GCJ02ToBD09(latt, lonn);
                ss.setJd(dd[0]);
                ss.setWd(dd[1]);
                ss.setSj(m.get("GPSTIME") != null ? m.get("GPSTIME").toString() : "");
                ss.setSp(m.get("SPEED") != null ? m.get("SPEED").toString() : "");
                ss.setYxzt(m.get("DEVRUNSTATE") != null ? m.get("DEVRUNSTATE").toString() : "");
                ss.setBjbz(m.get("DEVALARMFLAG") != null ? m.get("DEVALARMFLAG").toString() : "");
                ss.setDis(dist);
                ss.setName(m.get("NAME") != null ? m.get("NAME").toString() : "");
                ss.setCom(list.get(0).get("COM") != null ? list.get(0).get("COM").toString() : "");
                ssgj.add(ss);
            }
        }

        return ssgj;
    }

    /**
     * 实时轨迹
     */
    public Ssgj ssgj(String cp) throws Exception {
        Map<String, Object> map = MapConstants.gpsCache;
        Ssgj ssgj = new Ssgj();
        if (map.get("gpstime") != null && Long.valueOf(map.get("gpstime").toString()) < (System.currentTimeMillis() - 2 * Long.valueOf(SystemConstants.GPS_CACHE))) {
            if (map.containsKey(cp)) {
                ssgj = (Ssgj) map.get(cp);
            } else {
                getSsgj(cp, ssgj);
            }
        } else {
            getSsgj(cp, ssgj);
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

                @Override
                public void run() {

                    MapConstants.gpsCache.put("gpstime", System.currentTimeMillis());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.MINUTE, -6);
                    try {
                        List<Map<String, Object>> list = gpsDao.ssgjAll();
                        if (list != null && list.size() != 0) {
                            Ssgj ssgj = null;
                            Map<String, Object> map = null;
                            for (int i = 0; i < list.size(); i++) {
                                ssgj = new Ssgj();
                                map = list.get(i);
                                if (map.get("GPSTIME") != null) {
                                    if (c.after(sdf.parse(map.get("GPSTIME").toString()))) {
                                        ssgj.setState("2");
                                    } else {
//									SimpleDateFormat sdff = new SimpleDateFormat("mm");
//									ssgj.setJd(list.get(i).get("LONGITUDE") != null ? Double
//											.valueOf(list.get(i).get("LONGITUDE").toString())
//											+ (Double.valueOf(sdff.format(new Date())) / 1000000)
//											: 0);
//									ssgj.setWd(list.get(i).get("LATITUDE") != null ? Double
//											.valueOf(list.get(i).get("LATITUDE").toString())
//											+ (Double.valueOf(sdff.format(new Date())) / 1000000)
//											: 0);
                                        // 以上为测试

                                        ssgj.setCph(map.get("VEHICLEID") != null ? map.get("VEHICLEID").toString() : "");
                                        ssgj.setFx(map.get("ORIENTATION") != null ? map.get("ORIENTATION").toString() : "");
//									 ssgj.setJd(map.get("LONGITUDE")!=null?Double.valueOf(map.get("LONGITUDE").toString()):0);

//									 ssgj.setWd(map.get("LATITUDE")!=null?Double.valueOf(map.get("LATITUDE").toString()):0);
                                        double lat = Double.valueOf(map.get("LATITUDE") != null ? map.get("LATITUDE").toString() : "0");
                                        double lon = Double.valueOf(map.get("LONGITUDE") != null ? map.get("LONGITUDE").toString() : "0");
                                        double[] d = EvilTransform.GCJ02ToBD09(lat, lon);
                                        ssgj.setJd(d[0]);
                                        ssgj.setWd(d[1]);
                                        ssgj.setSj(map.get("GPSTIME") != null ? map.get("GPSTIME").toString() : "");
                                        ssgj.setSp(map.get("SPEED") != null ? map.get("SPEED").toString() : "");
                                        ssgj.setYxzt(map.get("DEVRUNSTATE") != null ? map.get("DEVRUNSTATE").toString() : "");
                                        ssgj.setBjbz(map.get("DEVALARMFLAG") != null ? map.get("DEVALARMFLAG").toString() : "");
                                        ssgj.setName(map.get("NAME") != null ? map.get("NAME").toString() : "");
                                        ssgj.setCom(map.get("COM") != null ? map.get("COM").toString() : "");
                                        ssgj.setSfz(map.get("SFZ") != null ? map.get("SFZ").toString() : "");
                                    }
                                }
                                MapConstants.gpsCache.put(ssgj.getCph(), ssgj);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 1, Long.valueOf(SystemConstants.GPS_CACHE), TimeUnit.SECONDS);
        }


        return ssgj;
    }

    /**
     * 实时轨迹
     */
    public Ssgj getSsgj(String cp, Ssgj ssgj) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, -6);
        List<Map<String, Object>> list = gpsDao.ssgj(cp);
        if (list != null && list.size() != 0) {
            if (list.get(0).get("GPSTIME") != null) {
                if (c.after(sdf.parse(list.get(0).get("GPSTIME").toString()))) {
                    ssgj.setState("2");
                } else {
                    Map<String, Object> map = list.get(0);
//					SimpleDateFormat sdff = new SimpleDateFormat("mm");
//					ssgj.setJd(map.get("LONGITUDE") != null ? Double
//							.valueOf(map.get("LONGITUDE").toString())
//							+ (Double.valueOf(sdff.format(new Date())) / 1000000)
//							: 0);
//					ssgj.setWd(map.get("LATITUDE") != null ? Double
//							.valueOf(map.get("LATITUDE").toString())
//							+ (Double.valueOf(sdff.format(new Date())) / 1000000)
//							: 0);
                    // 以上为测试

                    ssgj.setCph(map.get("VEHICLEID") != null ? map.get("VEHICLEID").toString() : "");
                    ssgj.setFx(map.get("ORIENTATION") != null ? map.get("ORIENTATION").toString() : "");
//					 ssgj.setJd(map.get("LONGITUDE")!=null?Double.valueOf(map.get("LONGITUDE").toString()):0);
                    double lat = Double.valueOf(map.get("LATITUDE") != null ? map.get("LATITUDE").toString() : "0");
                    double lon = Double.valueOf(map.get("LONGITUDE") != null ? map.get("LONGITUDE").toString() : "0");
                    double[] d = EvilTransform.GCJ02ToBD09(lat, lon);
                    ssgj.setJd(d[0]);
                    ssgj.setWd(d[1]);
//					 ssgj.setWd(map.get("LATITUDE")!=null?Double.valueOf(map.get("LATITUDE").toString()):0);
                    ssgj.setSj(map.get("GPSTIME") != null ? map
                            .get("GPSTIME").toString() : "");
                    ssgj.setSp(map.get("SPEED") != null ? map
                            .get("SPEED").toString() : "");
                    ssgj.setYxzt(map.get("DEVRUNSTATE") != null ? map.get("DEVRUNSTATE").toString() : "");
                    ssgj.setBjbz(map.get("DEVALARMFLAG") != null ? map.get("DEVALARMFLAG").toString() : "");
                    ssgj.setName(map.get("NAME") != null ? map
                            .get("NAME").toString() : "");
                    ssgj.setCom(map.get("COM") != null ? map
                            .get("COM").toString() : "");
                    ssgj.setSfz(map.get("SFZ") != null ? map
                            .get("SFZ").toString() : "");
                }
            }
        } else {
            ssgj.setState("0");
        }
        return ssgj;
    }

    /**
     * 实位置汇报
     */
    public Map wzhb(String lat, String lon, String cl, String uf, String cv, String di) throws Exception {
        Map map = new HashMap<>();
        gpsDao.wzhb(lat, lon, cl, di);
        List list = gpsDao.getPz(cv);
        if (list != null && list.size() != 0) {
            map.put("cv", list.get(0));
        } else {
            map.put("cv", null);
        }
        String[] ufs = uf.split(",");
        for (String s : ufs) {
            List lt = userDao.checkyh(s);
            if (lt != null && lt.size() != 0) {
                map.put(s, 1);
            } else {
                map.put(s, 0);
            }
        }

        return map;
    }

    /**
     * 历史轨迹
     */
    public List<Map<String, Object>> gettrack(String cp, String kst, String jst) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        list = gpsDao.gettrack(cp, kst, jst);
        for (Map<String, Object> m : list) {
            double lat = Double.valueOf(m.get("WD") != null ? m.get("WD").toString() : "0");
            double lon = Double.valueOf(m.get("JD") != null ? m.get("JD").toString() : "0");

            double[] d = EvilTransform.GCJ02ToBD09(lat, lon);
            m.put("JD", d[0]);
            m.put("WD", d[1]);
        }
        return list;
    }

}
