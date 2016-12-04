package com.lcc.framework.dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.resteasy.plugins.spring.SpringContextLoaderListener;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * Created by lcc on 2016/12/4.
 */
public class MyContextLoaderListener extends SpringContextLoaderListener{

    /**
     * 获取sessionFactory
     */
    protected SessionFactory lookUpSessionFactory(ServletContext sc){
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        return (SessionFactory) wac.getBean("sessionFactory",SessionFactory.class);
    }

    public void contextInitialized(final ServletContextEvent servletContext){
        super.contextInitialized(servletContext);

        SessionLookup sl = new SessionLookup() {
            @Override
            public Session lookupSession() {
                SessionFactory sf = lookUpSessionFactory(servletContext.getServletContext());
                if (TransactionSynchronizationManager.hasResource(sf)) {
                    SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sf);
                    Session session = holder.getSession();
                    return session;
                } else{
                    //return SessionFactoryUtils.getSession(sf, true);
                    return null;
                }
            }
        };

        ModelContext.registerSessionLookup(sl);
    }
}
