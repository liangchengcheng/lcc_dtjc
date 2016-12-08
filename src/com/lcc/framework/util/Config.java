package com.lcc.framework.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
/**
 * Created by lcc on 2016/12/8.
 */
public class Config {
    /**
     *  Description of the Field
     */
    public static int CONFIG_PROPFILE = 1;
    /**
     *  Description of the Field
     */
    public static int CONFIG_XMLFILE = 2;

    private static Config cfg = null;
    private static String configFileName = null;

    private int config_file_type = 1;
    private Document doc = null;

    private Properties props;
    private Node rootElement = null;
    private static String workPath = "";
    private Vector tempProps=new Vector();

    /**
     *  Constructor for the Config object
     */
    public Config() {
        props = new java.util.Properties();
    }


    /**
     *  Sets the ConfigFileName attribute of the Config object
     *
     *@param  cfg  The new ConfigFileName value
     */
    public static void setConfigFileName(String cfg) {
        configFileName = cfg;
    }

    public void setProperty(String keyName, String keyValue,boolean isTemp) {
        if(isTemp){
            tempProps.remove(keyName);
            tempProps.add(keyName);
        }
        setProperty(keyName,keyValue);

    }
    /**
     *  Sets the Property attribute of the Config object
     *
     *@param  keyName   The new Property value
     *@param  keyValue  The new Property value
     */
    public void setProperty(String keyName, String keyValue) {
        if (keyName == null || keyValue == null || keyName.length() == 0) {
            return;
        }
        props.setProperty(keyName, keyValue);
    }


    /**
     *  Gets the ConfigFileName attribute of the Config object
     *
     *@return    The ConfigFileName value
     */
    public static String getConfigFileName() {
        return configFileName;
    }

    public static Config getInstance() {
        if (cfg == null) {
            cfg = new Config();
            cfg.loadConfig();
            return cfg;
        } else {
            return cfg;
        }
    }


    /**
     *  Gets the instance attribute of the Config class
     *
     *@param  configFileName  Description of the Parameter
     *@return                 The instance value
     */
    public static Config getInstance(String configFileName) {
        if (cfg == null) {
            cfg.setConfigFileName(configFileName);
            cfg = new Config();
            cfg.loadConfig();
            return cfg;
        } else {
            return cfg;
        }

    }


    /**
     *  Gets the Property attribute of the Config object
     *
     *@param  keyName  Description of Parameter
     *@return          The Property value
     */
    public String getProperty(String keyName) {
        String s = props.getProperty(keyName);
        return DataTypeUtil.toCHNString(s);
    }

    public final static String getWorkPath() {
        if (workPath == null || workPath.length() <= 0) {
            workPath = getInstance().getProperty("workDir");
        }
        if (workPath == null) {
            workPath = getInstance().getProperty("workdir");
        }
        return workPath;
    }

    private int loadConfig() {
        InputStream fis = null;
        try {
            try {
                if (configFileName == null || configFileName.length() <= 0) {
                    LogUtil.getLogger().error("发生错误");
                    return -1;
                }
                fis = Config.class.getResourceAsStream(configFileName);
                props.load(fis);
            } catch (Exception ex1) {
                fis = new FileInputStream(configFileName);
                props.load(fis);
            }
            LogUtil.getLogger().debug("load config success:" + configFileName);
            return 0;
        } catch (Exception ex) {
            LogUtil.getLogger().debug("file may not exist");
            LogUtil.getLogger().debug(this.getClass().getName() + "::", ex);

            return 100;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception ex2) {
            }
        }
    }


    /**
     *  The main program for the SticConfig class
     *
     *@param  args  The command line arguments
     */
    public static void main(String[] args) {
        //Config sc = Config.getInstance("F:/new_source/GANSU/stic/stic.xml");
        Properties p= new Properties();
        try {
            p.load(new FileInputStream("F:/new_source/GANSU/stic/stic2.properties"));
            for (Iterator iter = p.keySet().iterator(); iter.hasNext();) {
                String element = (String) iter.next();

                String ss = p.getProperty(element);
                if("CLIENT_FONT_NAME".equals(element)){

                    ss = new String(ss.getBytes("8859_1"),"GB2312");
                    System.out.print("ss:"+ss);
                }
                //sc.setProperty(element, ss);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int saveConfig() {
        java.io.FileOutputStream fos = null;
        try {
            //zxt.pub.util.Utilities.setUserDir();//yql 2004-01-08 rem
            if (configFileName == null || configFileName.length() <= 0) {
                return -1;
            }
            fos = new java.io.FileOutputStream(configFileName);
            props.store(fos, "STIC CONFIG FILE");
            fos.close();
            return 0;
        } catch (Exception ex) {
            LogUtil.getLogger().debug("file may not exist");
            LogUtil.getLogger().debug(this.getClass().getName() + "::", ex);
            ex.printStackTrace();
            return 100;
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }

    }

    public int saveConfig(String fileName){
        if(fileName == null){
            LogUtil.getLogger().error("发生了错误!");
            return 100;
        }
        if(fileName.toLowerCase().endsWith(".xml")){
            config_file_type = Config.CONFIG_XMLFILE;
        }else{
            config_file_type = Config.CONFIG_PROPFILE;
        }
        configFileName = fileName;
        return saveConfig();
    }
}
