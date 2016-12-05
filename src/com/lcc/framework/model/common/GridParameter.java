package com.lcc.framework.model.common;

/**
 * Created by lcc on 2016/12/5.
 */
public class GridParameter {
    private int pageNo; // 当前页号

    private int pageSize; // 每页显示记录数

    private String sortid;

    private String sorttype;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getSortid() {
        return sortid;
    }

    public void setSortid(String sortid) {
        this.sortid = sortid;
    }

    public String getSorttype() {
        return sorttype;
    }

    public void setSorttype(String sorttype) {
        this.sorttype = sorttype;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
