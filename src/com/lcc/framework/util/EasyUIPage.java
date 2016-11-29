package com.lcc.framework.util;

import java.util.ArrayList;
import java.util.List;

/**
 * easyui的分页类
 * @param <T>
 */
public class EasyUIPage<T> {

    /**
     * 起始页数
     */
    private int start;

    /**
     * 每页最大显示数
     */
    private int limit;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 每页数据
     */
    private List<T> rows = new ArrayList<T>();

    /**
     * 查询参数
     */
    private Object parameter;

    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List<T> getRows() {
        return rows;
    }
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    public Object getParameter() {
        return parameter;
    }
    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
}
