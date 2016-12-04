package com.lcc.framework.dao;

import org.hibernate.Session;

/**
 * Created by lcc on 2016/12/4.
 */
public class ModelContext {
    private  static SessionLookup SESSION_LOOKUP  = null;

    public final static void registerSessionLookup(SessionLookup lookup){
        SESSION_LOOKUP = lookup;
    }


    public final static Session getSession() {
        return SESSION_LOOKUP.lookupSession();
    }
}
