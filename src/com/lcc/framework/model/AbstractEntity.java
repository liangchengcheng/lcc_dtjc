package com.lcc.framework.model;

import javax.persistence.MappedSuperclass;

/**
 * Created by lcc on 2016/12/4.
 */
@MappedSuperclass
public abstract class AbstractEntity<T> extends BaseAbstractEntity<T> {

    private static final long serialVersionUID = 3303054758723102256L;

}
