package com.lcc.taxi.bean;


import com.lcc.framework.dao.EntityDao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_JC_LOG")
public class TjcLog extends EntityDao<TjcLog> {

    private static final long serialVersionUID = 5881533796663809520L;

    private String id;
    private String cz;
    private Date cjsj;
    private String czy;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCz() {
        return cz;
    }

    public void setCz(String cz) {
        this.cz = cz;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getCzy() {
        return czy;
    }

    public void setCzy(String czy) {
        this.czy = czy;
    }
}