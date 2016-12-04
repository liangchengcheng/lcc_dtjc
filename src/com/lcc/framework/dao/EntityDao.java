package com.lcc.framework.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lcc.framework.model.AbstractEntity;
import com.lcc.framework.util.LogUtil;
import com.lcc.framework.validate.ValidateContext;
import com.lcc.framework.validate.annotation.Validation;
import com.lcc.framework.validate.annotation.Validations;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.Assert;

/**
 * Created by lcc on 2016/11/30.
 */
public class EntityDao<T> implements Serializable  {
    protected Class<T> persistentClass;

    private static final long serialVersionUID = -6833276619166596270L;

    public EntityDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public static Session getSession() {
        Session session = ModelContext.getSession();
        LogUtil.getLogger().info("getsession,sessionid:" + session.hashCode());
        return session;
    }

    public void save() {
        save(this);
    }

    public void save(Object obj) {
        getSession().save(obj);
    }

    public void update() {
        update(this);
    }

    public void update(Object obj) {
        getSession().update(obj);
        getSession().flush();
    }

    @SuppressWarnings("unchecked")
    public void saveOrUpdate() {
        if (this instanceof AbstractEntity) {
            AbstractEntity entity = (AbstractEntity) this;
            if (entity.getId() != null) {
                update(entity);
            } else {
                save(entity);
            }
        }
    }

    public void delete() {
        getSession().delete(this);
        getSession().flush();
    }

    public static <T> T get(Class<T> persistentClass, Serializable id) {
        return (T) getSession().get(persistentClass, id);
    }

    public static <T> List<T> getAll(Class<T> persistentClass) {
        Criteria criteria = getSession().createCriteria(persistentClass);
        return criteria.setCacheable(true).list();
    }

    public static <T> List<T> find(String hql, Object... values) {
        return createQuery(hql, values).list();
    }

    public static <T> List<T> findBysql(String sql, Object... values) {

        return createSQLQuery(sql, values).list();
    }

    public static int execute(String hql, Object... values) {
        return createQuery(hql, values).executeUpdate();
    }

    public static int executeSQL(String sql, Object... values) {
        return createSQLQuery(sql, values).executeUpdate();
    }

    public static <T> List<T> find(String hql, int pageSize, Object... values) {
        return createQuery(hql, values).setFirstResult(0)
                .setMaxResults(pageSize).list();
    }

    public static Object findUnique(String hql, Object... values) {
        return createQuery(hql, values).uniqueResult();
    }

    public static <T> List<T> findByProperty(Class<T> persistentClass,
                                             String propertyName, Object value) {
        Assert.hasText(propertyName);
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(propertyName, value));
        return criteria.list();
    }

    public static <T> List<T> findByProperty(Class<T> persistentClass,
                                             String propertyName, Object value, int pageSize) {
        Assert.hasText(propertyName);
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(propertyName, value));
        return criteria.setFirstResult(0).setMaxResults(pageSize).list();
    }

    public static <T> T findUniqueByProperty(Class<T> persistentClass,
                                             String propertyName, Object value) {
        Assert.hasText(propertyName);
        Criteria criteria = getSession().createCriteria(persistentClass);
        criteria.add(Restrictions.eq(propertyName, value));
        return (T) criteria.uniqueResult();
    }

    public static boolean isPropertyUnique(Class persistentClass,
                                           String propertyName, Object newValue) {
        if (newValue == null)
            return true;
        Object object = findUniqueByProperty(persistentClass, propertyName, newValue);
        return (object == null);
    }

    public static Query createQuery(String queryString, Object... values) {
        Assert.hasText(queryString);
        Query queryObject = getSession().createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }

    public static Query createSQLQuery(String queryString, Object... values) {
        Assert.hasText(queryString);
        Query queryObject = getSession().createSQLQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }

    public static void refresh(Object entity) {
        getSession().refresh(entity);
    }

    public static void refresh(Object entity, final LockMode lockMode) {
        Assert.notNull(lockMode);
        getSession().refresh(entity, lockMode);
    }

    public static void evict(Object entity) {
        getSession().evict(entity);
    }

    public static void flush() {
        getSession().flush();
    }

    public static void clear() {
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
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
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
     * @param pageNo
     *            页号,从1开始.
     */
    public static Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
        // Count查询
        String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
        List<?> countlist = find(countQueryString, values);
        boolean groupByFlag = false;
        if (countQueryString.indexOf("group by") > 0) {
            groupByFlag = true;
        }
        long totalCount = 0;
        if (countlist != null && !countlist.isEmpty()) {
            if (groupByFlag) {
                totalCount = Long.valueOf(countlist.size());
            } else {
                totalCount = Long.parseLong(countlist.get(0).toString());
            }
        }
        if (totalCount < 1)
            return new Page();
        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        Query query = createQuery(hql, values);
        List<?> list = query.setFirstResult(startIndex).setMaxResults(pageSize)
                .list();

        return new Page(startIndex, totalCount, pageSize, list);
    }

    /**
     * 对所有属性进行验证
     */
    public Map<String, String> validate() {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = persistentClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            validateField(getValidations(field), getMessageKey(fieldName),
                    invokeMethod(fieldName), map);
        }
        return map;
    }

    /**
     * 对单个属性进行验证
     */
    public Map<String, String> validate(String fieldName) {
        if (fieldName == null) {
            return validate();
        }
        Field field = null;
        try {
            field = persistentClass.getDeclaredField(fieldName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<String, String>();
        validateField(getValidations(field), getMessageKey(fieldName),
                invokeMethod(fieldName), map);
        return map;
    }

    /**
     * 具体进行验证的方法,验证后将错误信息记入到map中
     *
     * @param vs
     *            验证器
     * @param key
     *            错误信息的键
     * @param fieldValue
     *            属性的值
     * @param map
     *            装载错误信息的map
     */
    public void validateField(Validation[] vs, String key, Object fieldValue, Map<String, String> map) {
        for (Validation validateAnnotation : vs) {
            if (validateAnnotation != null) {
                String msg = ValidateContext.validate(validateAnnotation, fieldValue);
                if (!msg.equals("")) {
                    map.put(key, msg);
                    break;
                }
            }
        }
    }

    /**
     * 获得该field上的所有验证器的数组
     */
    public Validation[] getValidations(Field field) {
        Validation[] validations = new Validation[1];
        if (field != null) {
            if (field.isAnnotationPresent(Validation.class)) {
                validations[0] = field.getAnnotation(Validation.class);
                return validations;
            }
            if (field.isAnnotationPresent(Validations.class)) {
                return field.getAnnotation(Validations.class).value();
            }
        }
        return validations;
    }

    /**
     * 通过反射获得对象的名称和传入的属性名称拼装成存储错误消息的key字符串
     */
    private String getMessageKey(String fieldName) {
        String objectName = persistentClass.getName();
        int index = objectName.lastIndexOf(".");
        if (index > 0) {
            objectName = objectName.substring(index + 1);
        }
        StringBuffer sb = new StringBuffer();
        sb.append(objectName);
        sb.append("_");
        sb.append(fieldName);
        sb.append("_msg");
        return sb.toString();
    }

    /**
     * 通过反射调用该属性的get方法获得属性的值
     */
    private Object invokeMethod(String filedName) {
        filedName = filedName.substring(0, 1).toUpperCase() + filedName.substring(1);
        try {
            Method method = persistentClass.getMethod("get" + filedName);
            return method.invoke(this);
        } catch (Exception e) {
            return null;
        }
    }
}
