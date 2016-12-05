package com.lcc.framework.model.common;

import com.lcc.framework.util.DateUtil;

import java.util.Date;

/**
 * Created by lcc on 2016/12/5.
 */
public class SearchDate {

    public SearchDate(){

    }

    private String start = "";
    private String end = "";

    public SearchDate(String start,String end){
        super();
        if (null != start && !"".equals(start)){
            this.start = new StringBuffer(start).append(" 00:00:00").toString();
        }

        if (null != end && !"".equals(end)){
            this.end = new StringBuffer(end).append(" 00:00:00").toString();
        }
    }

    public SearchDate(Date start, Date end) {
        super();
        if (null != start) {
            this.start = DateUtil.dateFormatter(start) + " 00:00:00";
        }
        if (null != end) {
            this.end = DateUtil.dateFormatter(end) + " 00:00:00";
        }
    }

    /**
     * 构造时传入起始时间和结束时间，返回拼接好的Sql字符串
     *
     * @param column  要搜索的字段值
     */
    public String toSql(String column) {
        if ("".equals(start) && "".equals(end)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        if(start.equals("")){
            start = new StringBuffer(DateUtil.getCurrenDate()).append(" 00:00:00").toString();
        }
        if(end.equals("")){
            end = new StringBuffer(DateUtil.getCurrenDate()).append(" 00:00:00").toString();
        }
        sb.append(" and ");
        sb.append(column).append(" >= '");
        sb.append(DateUtil.addDayToString(start, 0)).append("'");
        sb.append(" and ");
        sb.append(column).append(" < '");
        sb.append(DateUtil.addDayToString(end, 1));
        sb.append("'");
        return sb.toString();
    }

    /**
     * 构造一个链接字符串，提供给pageLink用
     */
    public String toLink() {
        StringBuffer sb = new StringBuffer();
        if (null != start && !"".equalsIgnoreCase(start)) {
            sb.append("searchDate.start=").append(start.replace(" 00:00:00", "")).append("&");
        }
        if (null != end && !"".equalsIgnoreCase(end)) {
            sb.append("searchDate.end=").append(end.replace(" 00:00:00", "")).append("&");
        }
        return sb.toString();
    }

    public void setStart(String start) {
        if (null != start && !"".equals(start)) {
            this.start = new StringBuffer(start).append(" 00:00:00").toString();
        }
    }

    public void setEnd(String end) {
        if (null != end && !"".equals(end)) {
            this.end = new StringBuffer(end).append(" 00:00:00").toString();
        }
    }

    public String getStart() {
        if (null != start && !"".equals(start.trim())) {
            Date startDate = DateUtil.dateTimeParser(start);
            return DateUtil.dateFormatter(startDate);
        } else {
            return null;
        }
    }

    public String getEnd() {
        if (null != end && !"".equals(end.trim())) {
            Date endDate = DateUtil.dateTimeParser(end);
            return DateUtil.dateFormatter(endDate);
        } else {
            return null;
        }

    }
}
