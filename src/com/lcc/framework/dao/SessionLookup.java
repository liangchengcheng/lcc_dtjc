package com.lcc.framework.dao;

import org.hibernate.Session;

public interface SessionLookup {
    public Session lookupSession();
}
