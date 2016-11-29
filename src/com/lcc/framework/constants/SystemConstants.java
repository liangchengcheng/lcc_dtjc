package com.lcc.framework.constants;

import com.lcc.framework.util.DataTypeUtil;

import java.io.File;

public class SystemConstants extends ConfigurableContants {

    /**
     * 缓存树
     */
    public static final String TREE = "tree";

    public static final String USER_SESSION_KEY = "user";

    public static final String USER_SESSION_MENUIDS = "menuids";

    public static final String USER_SESSION_COM_KEY = "user_com_key";

    public static final String COOKIE_SESSION_KEY = "session_key";

    public static final String DEFAULT_PASSWORD = "888888";
    public static  String DEFAULT_WZXWSJ = "1462351372221";
    public static  String DEFAULT_XCBLSJ = "1462351372221";
    public static  String DEFAULT_XWBLSJ = "1462351372221";
    public static  String DEFAULT_JSYHMDSJ = "1462351372221";
    public static  String DEFAULT_CLHMDSJ = "1462351372221";
    public static final boolean SESSION_CLUSTERID = false;

    public static String PROJECT_HOME="";
    public static String USERID="1";//稽查图片专门用户管理

    public static String PROJECT_ROOT="";

    public static String PROJECT_TEMP_DIR = "temp"+File.separator;

    public static String PROJECT_YKT_EXPORT="";
    public static String STATE_YX="1";
    public static String STATE_WX="0";
    public static String TYPE_APP="1";
    //实时轨迹缓存时间
    public static String GPS_CACHE=getProperty("gps_cache", "10000");
    //考核总分
    public static String KHZF=getProperty("KHZF", "20");
    //营收类型
    public static String QD="QD";
    public static String QT="QT";
    public static String YS="YS";

    public static int BATCHSIZE=100;
    //apk默认上传路径
    public static String apkpath = "apkDown";
    //头像默认上传路径
    public static String portraitPath = "portraitPath";
    public static final String PIC = "pic";

    static {
        init("/system.properties");
    }

    /**
     * 文件上传路径中的根目录
     */
    public static String UPLOAD_ROOT_PATH = getProperty("upload_root_path", "download");

    /**
     * 文件上传服务器路径中的根目录
     */
    public static String UPLOAD_IMG_SERVER_PATH = getProperty("upload_img_server_path", "download");

    public static final String TEMPLETE_ROOT_PATH = "templete";

    public static String RES_PASS=getProperty("res_pass");

    public static String LEFT_M = getProperty("left_m");

    public static String RIGHT_M = getProperty("right_m");

    public static final String CSIP = getProperty("CSIP");
    public static final int CSPORT = DataTypeUtil.toInt(getProperty("CSPORT"));

    /**
     * 排除在外的企业
     */
    public static String IDS = getProperty("ids", "");
}

