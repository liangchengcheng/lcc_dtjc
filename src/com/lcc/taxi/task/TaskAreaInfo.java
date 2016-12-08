package com.lcc.taxi.task;

import com.lcc.framework.util.DataTypeUtil;

import java.awt.*;
import java.math.BigDecimal;

/**
 * Created by lcc on 2016/12/7.
 */
public class TaskAreaInfo {

    private String wlid;
    private Polygon area;
    private String area_str;

    public TaskAreaInfo(String wlid, String area_str) {
        this.wlid = wlid;
        this.area_str = area_str;
        initArea();
    }

    private void initArea() {
        area = new Polygon();
        String[] areas_ary = this.area_str.split(";");
        for (int i = 0; i < areas_ary.length; i++) {
            String[] area_ary = areas_ary[i].split(",");
            area.addPoint((int)(new BigDecimal(DataTypeUtil.toDouble(area_ary[0])*100000)).intValue(), new BigDecimal(DataTypeUtil.toDouble(area_ary[1])*100000).intValue());
        }
    }

    public String getWlid() {
        return wlid;
    }

    public Polygon getArea() {
        return area;
    }
}
