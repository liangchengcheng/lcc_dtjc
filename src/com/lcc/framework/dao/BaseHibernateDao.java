package com.lcc.framework.dao;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@SuppressWarnings("unchecked")
@Component
@Transactional
/**
 * Created by lcc on 2016/12/4.
 */
public class BaseHibernateDao<T, PK extends Serializable> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SessionFactory sessionFactory;

    protected Class<T> entityClass;

    public BaseHibernateDao() {
        //this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());
        //this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void save(T entity) {
        Assert.notNull(entity);
        getSession().saveOrUpdate(entity);
        logger.info("save entity: {}", entity);
    }

    public void delete(T entity) {
        Assert.notNull(entity);
        getSession().delete(entity);
        logger.info("delete entity: {}", entity);
    }

    public void delete(PK id) {
        Assert.notNull(id);
        delete(get(id));
    }

    public T get(final PK id) {
        return (T) getSession().get(entityClass, id);
    }

    public List<T> getAll(){
        Criteria criteria = getSession().createCriteria(entityClass);
        return criteria.list();
    }

    public List<T> find(String hql, Object... values) {
        return createQuery(hql, values).list();
    }

    public List<T> find(String hql, int pageSize, Object... values) {
        return createQuery(hql, values).setFirstResult(0).setMaxResults(pageSize).list();
    }

    public Object findUnique(String hql, Object... values) {
        return createQuery(hql, values).uniqueResult();
    }

    public List<T> findByProperty(String propertyName, Object value) {
        Assert.hasText(propertyName);
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq(propertyName, value));
        return criteria.list();
    }

    public List<T> findByProperty(String propertyName, Object value, int pageSize) {
        Assert.hasText(propertyName);
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq(propertyName, value));
        return criteria.setFirstResult(0).setMaxResults(pageSize).list();
    }

    public T findUniqueByProperty(String propertyName, Object value) {
        Assert.hasText(propertyName);
        Criteria criteria = getSession().createCriteria(entityClass);
        criteria.add(Restrictions.eq(propertyName, value));
        return (T) criteria.uniqueResult();
    }

    public boolean isPropertyUnique(String propertyName, Object newValue, Object orgValue) {
        if (newValue == null || newValue.equals(orgValue))
            return true;
        Object object = findUniqueByProperty(propertyName, newValue);
        return (object == null);
    }

    public Query createQuery(String queryString, Object... values) {
        Assert.hasText(queryString);
        Query queryObject = getSession().createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }

    public void refresh(Object entity){
        getSession().refresh(entity);
    }

    public void refresh(Object entity, final LockMode lockMode){
        Assert.notNull(lockMode);
        getSession().refresh(entity, lockMode);
    }

    public void evict(Object entity){
        getSession().evict(entity);
    }

    public void flush(){
        getSession().flush();
    }

    public void clear(){
        getSession().clear();
    }

    /**
     * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
     *
     * @see #pagedQuery(String,int,int,Object[])
     */
    private static String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    /**
     * 去除hql的orderby 子句，用于pagedQuery.
     *
     * @see #pagedQuery(String,int,int,Object[])
     */
    private static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
                Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 分页查询函数，使用hql.
     *
     * @param pageNo   页号,从1开始.
     */
    public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        // Count查询
        String countQueryString = " select count (*) "
                + removeSelect(removeOrders(hql));
        List countlist = find(countQueryString, values);
        long totalCount = 0;
        if (countlist.size() != 0) {
            totalCount = (Long) countlist.get(0);
        }

        if (totalCount < 1)
            return new Page();
        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        Query query = createQuery(hql, values);
        List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
                .list();

        return new Page(startIndex, totalCount, pageSize, list);
    }
}
