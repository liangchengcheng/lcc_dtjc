package com.lcc.framework.model;

import com.lcc.framework.dao.EntityDao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by lcc on 2016/11/30.
 */
@MappedSuperclass
public abstract class BaseAbstractEntity<T> extends EntityDao<T> {

    private static final long serialVersionUID = 3303054758723102256L;

    private String id;

    @Id
    @GeneratedValue(generator="paymentableGenerator")
    @GenericGenerator(name="paymentableGenerator",strategy="com.zght.taxiic.model.Sysid" )
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}