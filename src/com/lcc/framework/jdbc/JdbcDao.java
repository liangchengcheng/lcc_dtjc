package com.lcc.framework.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Table;
import com.lcc.framework.dao.EntityDao;
import com.lcc.framework.model.AbstractEntity;
import com.lcc.framework.util.DateUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * jdbcTemplate的封装类，主要提供封装后的方法，如将原始返回类型中的Map反射成Pojo
 *
 * @author sam
 *
 */
public class JdbcDao {

    private JdbcTemplate jdbcTemplate;

    /**
     * 保存或更新对象，传入对象必须继承于AbstractEntity类，否则操作无效<br/>
     * Sample:<br/>保存的例子:<br/>Advertisment adv = new Advertisment();<br/>
     * adv.setId("1");<br/>adv.setName("advertisment1");<br/>saveOrUpdate(adv);<br/>
     * 更新的例子:<br/>Advertisment adv = get(1,Advertisment.Class);<br/>
     * adv.setName("advertisment2");<br/>saveOrUpdate(adv);<br/>
     *
     * @param obj   要保存的对象
     */
    @SuppressWarnings("unchecked")
    public void saveOrUpdate(Object obj) {
        if (obj instanceof AbstractEntity) {
            AbstractEntity entityDao = (AbstractEntity) obj;
            if (entityDao.getId() == null) {
                save(obj);
            } else {
                update(obj);
            }
        }
    }

    /**
     * 保存对象<br/>Sample:<br/>Advertisment adv = new Advertisment();<br/>
     * adv.setId("1");<br/>adv.setName("advertisment1");<br/>save(adv);<br/>
     *
     * @param obj    要保存的对象
     */
    public void save(Object obj) {
        saveOrUpdateObj(obj, "save");
    }

    /**
     * 更新对象<br/>Sample:<br/>Advertisment adv = get(1,Advertisment.Class);<br/>adv.setName("advertisment2");<br/>
     * saveOrUpdate(adv);<br/>
     *
     * @param obj   要更新的对象
     */
    public void update(Object obj) {
        saveOrUpdateObj(obj, "update");
    }

    /**
     * 将所有的属性对应进行封装<br/>Sample:<br/>Advertisment adv =
     * get(1,Advertisment.Class);<br/>Map<String,Object> map =
     * setObjToMap(adv);<br/>返回的结果为进行匹配后的map结果集
     *
     * @return 进行匹配后的map结果集
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> setObjToMap(Object obj) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        return setPojoToMap(obj, null, hashMap);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> find(String sql, Object... values) {
        return null;
    }

    /**
     * 根据传入的类名，获取该类在数据库中的所有结果集，类型为pojo对象<br/>getAll(Advertisment.Class);<br/>
     *
     * @param persistentClass  类名
     *
     * @return 该类在数据库中的所有结果集
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> persistentClass) {
        String selectSql = getSelectSql(persistentClass);
        return getObjList(selectSql, persistentClass);
    }

    /**
     * 根据传入的类名生成默认的sql语句<br/>Sample:<br/>getSelectSql(Advertisment.Class);<br/>返回语句为:select *
     * from p_advertisement;<br/>
     *
     * @param persistentClass  类名
     *
     * @return 返回查询该类所有的sql语句
     */
    public String getSelectSql(Class<?> persistentClass) {
        String tableName = getTableName(persistentClass);
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ").append(tableName);
        return sql.toString();
    }

    /**
     * 根据id获取该类在数据库中的结果，返回类型为pojo<br/>Sample:<br/>get(1,Advertisment.Class);<br/>
     *
     * @param id id
     *
     * @param persistentClass  pojo的类型
     *
     * @return pojo对象
     */
    public <T> T get(Integer id, Class<T> persistentClass) {
        StringBuffer sql = new StringBuffer();
        sql.append(getSelectSql(persistentClass)).append(" where id = ")
                .append(id);
        return getObj(sql.toString(), persistentClass);
    }

    /**
     * 根据 entityClass 获取 Table name
     *
     * @persistentClass 类名
     *
     * @return 类名映射的数据库表的名称
     */
    public static String getTableName(Class<?> persistentClass) {
        String tableName = persistentClass.getSimpleName().toUpperCase();
        if (persistentClass.isAnnotationPresent(Table.class)) {
            Table table = persistentClass.getAnnotation(Table.class);
            if (table.name().trim().length() > 0) {
                tableName = table.name();
            }
        }
        return tableName;
    }

    /**
     * 根据sql语句返回队列,类型为map<br/>Sample:<br/>getMapList("select * from
     * p_advertisment");<br/>
     *
     * @param sql
     *            要执行的sql语句
     * @return 符合条件的数据结果集，队列中的每个对象为Map类型
     */
    @SuppressWarnings("unchecked")
    public List getMapList(String sql) {
        return getMapList(sql, null);
    }

    /**
     * 根据sql语句和数组中的参数返回队列,类型为map<br/>Sample:<br/>getMapList("select * from
     * p_advertisment where id = ?",new Object[]{1});<br/>
     *
     * @param sql
     *            要执行的sql语句
     * @param values
     *            参数的数组
     * @return 符合条件的数据结果集，队列中的每个对象为Map类型
     * @see JdbcTemplate
     */
    @SuppressWarnings("unchecked")
    public List getMapList(String sql, Object... values) {
        if (values == null) {
            return jdbcTemplate.queryForList(sql);
        } else {
            return jdbcTemplate.queryForList(sql, values);
        }
    }

    /**
     * 根据sql语句和参数返回队列，类型为pojo类型<br/>Sample:<br/>getObjList("select * from
     * p_advertisment where id > ?",new Object[]{1},Advertisment.Class);<br/>
     *
     * @param sql 要执行的sql语句
     *
     * @param values  参数的数组
     *
     * @param persistentClass  需要返回的pojo的类型
     *
     * @return 符合条件的数据结果集，队列中的每个对象为pojo类型
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getObjList(Class<T> persistentClass, String sql, Object... values) {
        List<Map> list = getMapList(sql, values);
        List<T> result = new ArrayList<T>();
        for (Map map : list) {
            result.add((T) setMapToObj(map, persistentClass));
        }
        return result;
    }

    /**
     * 根据sql语句和参数返回队列，类型为pojo类型<br/>Sample:<br/>getObjList("select * from
     * p_advertisment where id > 2",Advertisment.Class);<br/>
     *
     * @param sql 要执行的sql语句
     *
     * @param persistentClass  需要返回的pojo的类型
     *
     * @return 符合条件的数据结果集，队列中的每个对象为pojo类型
     */
    public <T> List<T> getObjList(String sql, Class<T> persistentClass) {
        return getObjList(persistentClass, sql, new Object[] {});
    }

    /**
     * 根据sql语句返回指定类型的结果<br/>Sample:<br/>getObj("select * from p_advertisment
     * where id = 2",Advertisment.Class);<br/>
     *
     * @param sql  要执行的sql语句
     *
     * @param persistentClass  需要返回的pojo的类型
     *
     * @return 符合条件的记录，类型为参数中pojo的类型
     */
    @SuppressWarnings("unchecked")
    public <T> T getObj(String sql, Class<T> persistentClass) {
        return getObj(persistentClass, sql, new Object[] {});
    }

    /**
     * 根据sql语句返回指定类型的结果<br/>Sample:<br/>getObj("select * from p_advertisment
     * where id = ?",new Object[]{1},Advertisment.Class);<br/>
     *
     * @param sql
     *            要执行的sql语句
     * @param values
     *            参数的数组
     * @param persistentClass
     *            需要返回的pojo的类型
     * @return 符合条件的记录，类型为参数中pojo的类型
     */
    @SuppressWarnings("unchecked")
    public <T> T getObj(Class<T> persistentClass, String sql, Object... values) {
        Map<String, Object> map = getMap(sql, values);
        return setMapToObj(map, persistentClass);
    }

    /**
     * 执行sql语句
     *
     * @param sql 要执行的sql语句
     *
     * @see JdbcTemplate
     */
    public void excute(String sql) {
        jdbcTemplate.execute(sql);
    }

    /**
     * 将map转换成pojo类型<br/>Sample:<br/>Map<String,Object> map = new HashMap<String,Object>;<br/>
     * map.put("id",1);<br/>map.put("name","sam");<br/>
     * setMapToObj(map,Advertisment.Class);<br/>
     *
     * @param map
     *            数据结果集
     * @param persistentClass
     *            需要返回的pojo的类型
     * @return 反射后的pojo对象
     */
    @SuppressWarnings("unchecked")
    private <T> T setMapToObj(Map<String, Object> map, Class<T> persistentClass) {
        T bean = null;
        try {
            bean = persistentClass.newInstance();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Field field = null;
                // 项目中字段与数据库映射规则为cityName--city_name,在反射中取出来的为带"_"的，所以进行处理
                String key = entry.getKey();
                int temp = key.indexOf("_");
                if (temp > 0) {
                    String first = key.substring(0, temp);
                    String sec = key.substring(temp + 1, temp + 2).toUpperCase();
                    String third = key.substring(temp + 2, key.length());
                    StringBuffer sb = new StringBuffer();
                    sb.append(first).append(sec).append(third);
                    key = sb.toString();
                }
                try {
                    field = persistentClass.getDeclaredField(key);
                } catch (NoSuchFieldException e) {
                    // 到该类的父类中去找
                    try {
                        field = persistentClass.getSuperclass().getDeclaredField(key);
                    } catch (NoSuchFieldException e1) {
                        // 到该类的父类的父类中去找
                        try {
                            field = persistentClass.getSuperclass().getSuperclass().getDeclaredField(key);
                        } catch (NoSuchFieldException e2) {
                            // 目前项目中的pojo的子父类关系最多为三级，所以超出3级后就直接抛出异常了
                            e2.printStackTrace();
                        }
                    }
                }
                field.setAccessible(true);
                field.set(bean, entry.getValue());
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 执行sql语句以及参数,返回结果为map类型
     *
     * @param sql  要执行的sql语句
     *
     * @param values  参数的数组
     *
     * @return 结果集，类型为map
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getMap(String sql, Object... values) {
        if (values == null) {
            return jdbcTemplate.queryForMap(sql);
        } else {
            return jdbcTemplate.queryForMap(sql, values);
        }
    }

    /**
     * 生成更新对象的sql语句(update)
     *
     * @param map
     *            保存字段的对应的map
     * @param tableName
     *            保存的表名
     * @return 生成的sql语句
     */
    private String generateUpdateSql(Map<String, Object> map, String tableName) {
        StringBuffer sb = new StringBuffer();
        sb.append("update ").append(tableName).append(" set ");
        Integer id = (Integer) map.get("id");
        map.remove("id");
        Iterator<String> it = map.keySet().iterator();
        int temp = 0;
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            if (value != null) {
                if (temp > 0) {
                    sb.append(",");
                }
                sb.append(key).append("='");
                if (value instanceof Date) {
                    sb.append(DateUtil.dateTimeFormatter((Date) value));
                } else {
                    sb.append(value);
                }
                sb.append("'");
                temp++;
            }
        }
        sb.append(" where id = ").append(id);
        return sb.toString();
    }

    /**
     * 生成保存对象的sql语句(insert)
     *
     * @param map 保存字段的对应的map
     *
     * @param tableName  保存的表名
     *
     * @return 生成的sql语句
     */
    private String generateAddSql(Map<String, Object> map, String tableName) {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into ").append(tableName);
        StringBuffer properties = new StringBuffer("(");
        StringBuffer values = new StringBuffer(" values(");
        Iterator<String> it = map.keySet().iterator();
        int temp = 0;
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            if (value != null) {
                if (temp > 0) {
                    properties.append(",");
                    values.append(",");
                }
                properties.append(key);
                values.append("'");
                if (value instanceof Date) {
                    values.append(DateUtil.dateTimeFormatter((Date) value));
                } else {
                    values.append(value);
                }
                values.append("'");
                temp++;
            }
        }
        sb.append(properties).append(")").append(values).append(")");
        return sb.toString();
    }

    /**
     * 将pojo的属性加入到map中
     *
     * @param obj pojo对象
     *
     * @param persistentClass  匹配类型
     *
     * @param hashMap 返回的map结果集
     *
     * @return 本次迭代后匹配的map结果集
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> setPojoToMap(Object obj, Class persistentClass, Map hashMap) {
        if (persistentClass == null) {
            persistentClass = obj.getClass();
        } else {
            persistentClass = persistentClass.getSuperclass();
        }
        try {
            Method m[] = persistentClass.getDeclaredMethods();
            for (int i = 0; i < m.length; i++) {
                if (m[i].getName().indexOf("get") == 0) {
                    String key = m[i].getName();
                    // 第一个字母转成小写，别的不变
                    StringBuffer sb = new StringBuffer(key.substring(3, 4).toLowerCase());
                    sb.append(key.substring(4, key.length()));
                    key = sb.toString();

                    if (key.toLowerCase().equals(key)) {
                        hashMap.put(key, m[i].invoke(obj, new Object[0]));
                    } else {
                        while (!key.toLowerCase().equals(key)) {
                            int maxLen = key.length();
                            boolean flag = true;
                            for (int k = 0; k < maxLen; k++) {
                                String temp = key.substring(k, k + 1);
                                if (!temp.toLowerCase().equals(temp)) {
                                    sb = new StringBuffer();
                                    sb.append(key.substring(0, k)).append("_")
                                            .append(temp.toLowerCase()).append(key.substring(k + 1, maxLen));
                                    key = sb.toString();
                                    flag = false;
                                }

                                if (k == maxLen - 1 || key.toLowerCase().equals(key)) {
                                    try {
                                        hashMap.put(key, m[i].invoke(obj, new Object[0]));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                if (!flag) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (persistentClass.getSuperclass() == EntityDao.class) {
            return hashMap;
        } else {
            return setPojoToMap(obj, persistentClass, hashMap);
        }
    }

    /**
     * 从sql语句中截取表名
     *
     * @param sql 执行的sql语句
     * @return sql语句中的表名
     */
    private String getTableNameFromSql(String sql) {
        String[] temp = sql.split(" from ");
        if (temp.length > 1) {
            sql = temp[1].trim();
            int end = sql.indexOf(" ");
            if (end == -1) {
                return temp[1];
            } else {
                return sql.substring(0, end);
            }
        }
        return null;
    }

    /**
     * 根据pojo以及操作类型，保存或更新对象
     */
    @SuppressWarnings("unchecked")
    private void saveOrUpdateObj(Object obj, String type) {
        Class persistentClass = obj.getClass();
        String tableName = getTableName(persistentClass);
        Map<String, Object> hashMap = setObjToMap(obj);
        String sql = "";
        if (type.equals("save")) {
            sql = generateAddSql(hashMap, tableName);
        }
        if (type.equals("update")) {
            sql = generateUpdateSql(hashMap, tableName);
        }
        excute(sql);
    }

    /**
     * 构造函数
     */
    public JdbcDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
