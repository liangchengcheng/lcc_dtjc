package com.lcc.framework.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Created by lcc on 2016/12/1.
 */
public class ActionTransactionInterceptor implements Interceptor {

    private static final long serialVersionUID = 1275359471958141842L;

    private DefaultTransactionDefinition def = new DefaultTransactionDefinition();

    @Autowired
    private HibernateTransactionManager transactionManager;

    public void destroy() {
    }

    public void init() {
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        String ret = null;
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            ret = ai.invoke();
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        return ret;
    }
}
