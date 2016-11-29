package com.lcc.framework.constants;

import com.lcc.framework.util.DataTypeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcc on 2016/11/29.
 */
public class MapConstants {
    public static HashMap<String, String> folkMap = new HashMap<String, String>();
    public static HashMap<String, String> jszcMap = new HashMap<String, String>();
    public static HashMap<String, String> whcdMap = new HashMap<String, String>();
    public static HashMap<String,Object> gpsCache= new HashMap<String, Object>();
    public static HashMap<String,Object> ssmap= new HashMap<String, Object>();

    static {
        folkMap.put("1", "汉族");
        folkMap.put("2", "蒙古族");
        folkMap.put("3", "回族");
        folkMap.put("4", "藏族");
        folkMap.put("5", "维吾尔族");
        folkMap.put("6", "苗族");
        folkMap.put("7", "彝族");
        folkMap.put("8", "壮族");
        folkMap.put("9", "布依族");
        folkMap.put("10", "朝鲜族");
        folkMap.put("11", "满族");
        folkMap.put("99", "其它");

        jszcMap.put("0", "无");
        jszcMap.put("1", "高级");
        jszcMap.put("2", "中级");
        jszcMap.put("3", "初级");

        whcdMap.put("11", "研究生");
        whcdMap.put("21", "大学");
        whcdMap.put("31", "专科");
        whcdMap.put("41", "中专");
        whcdMap.put("51", "技校");
        whcdMap.put("61", "高中");
        whcdMap.put("71", "初中");
        whcdMap.put("81", "小学");
    }


    public static String getFolk(String folk) {
        folk = folkMap.get(folk);
        if (DataTypeUtil.isEmptyStr(folk)) {
            folk = "";
        }
        return folk;
    }

    public static String getJszc(String jszc) {
        jszc = jszcMap.get(jszc);
        if (DataTypeUtil.isEmptyStr(jszc)) {
            jszc = "";
        }
        return jszc;
    }

    public static String getWhcd(String whcd) {
        whcd = whcdMap.get(whcd);
        if (DataTypeUtil.isEmptyStr(whcd)) {
            whcd = "";
        }
        return whcd;
    }

    public static Map<String, String> wzdmMap = new HashMap<String, String>();
    public static Map<String, String> kflxMap = new HashMap<String, String>();
    public static Map<String, String> tsZynrdmMap = new HashMap<String, String>();
    public static Map<String, String> bxgsMap = new HashMap<String, String>();

    static {

        wzdmMap.put("未按照规定操作计价器", "JJQ");
        wzdmMap.put("未按行业规范和企业要求着装", "ZZBZ");
        wzdmMap.put("未携带从业资格证", "WXDZGZ");
        wzdmMap.put("未主动给付乘客当次出租车客票", "BFCP");
        wzdmMap.put("无出租汽车服务资格证驾驶车辆", "WFWZGZ");
        wzdmMap.put("未携带道路运输证","WXDDLYSZ");
        wzdmMap.put("未按照计价器显示金额收费","JJQ");
        wzdmMap.put("无故拒载","JZ");
        wzdmMap.put("未按照价格主管部门有关规定收费","WAGDSF");

        kflxMap.put("无故拒载", "3");
        kflxMap.put("拒检", "3");
        kflxMap.put("倒客", "3");
        kflxMap.put("甩客", "3");
        kflxMap.put("未按照规定操作计价器", "3");
        kflxMap.put("未按照价格主管部门有关规定收费", "3");
        kflxMap.put("未按照计价器显示金额收费", "3");
        kflxMap.put("未携带从业资格证", "3");
        kflxMap.put("未主动给付乘客当次出租车客票", "3");
        kflxMap.put("无出租汽车服务资格证驾驶车辆", "3");
        kflxMap.put("不按规定放置资格证", "3");
        kflxMap.put("未携带道路运输证", "3");
        kflxMap.put("未按行业规范和企业要求着装", "3");
        kflxMap.put("安全设施不全", "3");
        kflxMap.put("无服务资格证", "5");
        kflxMap.put("私改计价器", "20");
        kflxMap.put("安装使用作弊装置", "20");

        tsZynrdmMap.put("拒载", "JZ");
        tsZynrdmMap.put("倒客", "DK");
        tsZynrdmMap.put("甩客", "SK");
        tsZynrdmMap.put("绕路高收费", "RDGSF");
        tsZynrdmMap.put("服务态度差", "FWZLC");
        tsZynrdmMap.put("其他出租投诉", "QTTS");
        tsZynrdmMap.put("计价器问题", "JJQ");
        tsZynrdmMap.put("违规合乘", "WGHC");
        tsZynrdmMap.put("收押金未候客", "WHK");
        tsZynrdmMap.put("无服务资格证", "WFWZGZ");
        tsZynrdmMap.put("票务问题", "WCYZGZ");
        tsZynrdmMap.put("无服务资格证", "WFWZGZ");
        tsZynrdmMap.put("营运中吸烟", "YSXY");

        bxgsMap.put("人保", "1");
        bxgsMap.put("大地", "2");
    }
}
