package com.lcc.framework.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;
import org.hibernate.util.PropertiesHelper;

public class Sysid implements IdentifierGenerator ,Configurable{

    private String sep = "";

    public Serializable generate(SessionImplementor session, Object obj){
        try {
            Thread.sleep(28);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        Date d = new Date();
        Random r = new Random();
        return sf.format(d) + (r.nextInt(89)+10);
    }

    public void configure(Type type, Properties params, Dialect d) {
        sep = PropertiesHelper.getString("separator", params, "");
    }

}
