package com.lcc.framework.constants;

import com.lcc.framework.util.Config;
import com.lcc.taxi.task.TaskRunBean;
import com.lcc.taxi.task.UserTaskRunBean;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lcc on 2016/12/7.
 */
public class UserConstants  extends ConfigurableContants {

    /**
     * 用户登录错误次数写入cookie
     */
    public static final String COOKIE_COUNT_KEY = "count";

    /**
     * 控件传输key
     */
    public static final String TRANSPORT_KEY = "12345678876543211234567887654321";

    /**
     * 车牌号字
     */
    public static final String PREFIX_VEHICLE = Config.getInstance().getProperty("PREFIX_VEHICLE");

    /**
     * 临时卡有效天数
     */
//	public static final int TEMP_CARD_ENDDAYS = DataTypeUtil.toInt(Config.getInstance().getProperty("TEMP_CARD_ENDDAYS"));

    /**
     * 默认开户行名称
     */
//	public static final String DEAULT_BANKNAME = DataTypeUtil.toNotNullString(Config.getInstance().getProperty("DEAULT_BANKNAME"));

    /**
     * cookie中数据保存的时间(30天)
     */
    public static final int COOKIE_SAVE_TIME =30* 24 * 60 * 60;

    /**
     * cookie中的用户名的key
     */
    public static final String COOKIE_PASSPORT_KEY = "passport";

    /**
     * cookie中的企业的key
     */
    public static final String COOKIE_QY_KEY = "qiye";

    /**
     * 通过
     */
    public static final String USER_STATE_PASS = "1";

    /**
     * 中间件地址
     */
    public static final String SERVERIP = Config.getInstance().getProperty("serverip");

    /**
     * 中间件端口
     */
//	public static final int SERVERPORT = Integer.parseInt(Config.getInstance().getProperty("serverport"));


    /**
     * 服务资格证有效期天数
     */
//	public static final Integer FWZGZYXQTS = Integer.parseInt(Config.getInstance().getProperty("fwzgzyxqts"))*365;


    /**
     * 法定退休年龄
     */
//	public static final Integer FDTXNL = Integer.parseInt(Config.getInstance().getProperty("fdtxnl"));
//
//
//	/**
//	 * 服务资格吊销后再申请间隔年数
//	 */
//	public static final Integer FWZGDXZSQNS = Integer.parseInt(Config.getInstance().getProperty("fwzgdxzsqns"))*365;
//	public static final Integer FWZGDXZSQNS_N = Integer.parseInt(Config.getInstance().getProperty("fwzgdxzsqns"));
//
//
//	/**
//	 * 服务资格年审提前时间
//	 */
//	public static final Integer FWZGNSTQSJ = Integer.parseInt(Config.getInstance().getProperty("fwzgnstqsj"));
//
//
//	/**
//	 * 年审超期宽限期
//	 */
//	public static final Integer NSCQKXQ = DataTypeUtil.toInt(Config.getInstance().getProperty("nscqkxq"));
//
//
//	/**
//	 * 两次上车间隔时间
//	 */
//	public static final Integer LCSCJGSJ = Integer.parseInt(Config.getInstance().getProperty("lcscjgsj"));
//
//
    /**
     * 一卡通司机代码Ext（前缀）
     */
    public static final String YKTSJDM_EXT = "012";
//
//
//	/**
//	 * 一辆车上最大驾驶员数量
//	 */
//	public static final Integer MAXDRIONEVEHICLE = DataTypeUtil.toInt(Config.getInstance().getProperty("maxdrionevehicle"), 3);
//
//
//	/**
//	 * 培训机构名称
//	 */
//	public static final String PXJGMC = Config.getInstance().getProperty("pxjgmc");
//
//
//	/**
//	 * 发证机构名称
//	 */
//	public static final String FZJGMC = Config.getInstance().getProperty("fzjgmc");
//

    /**
     * 未通过（删除）)
     */
    public static final String USER_STATE_NOT_PASS = "0";

    /**
     * 有效标志
     */
    public static final String YXBZ_TRUE = "1";
    public static final String YXBZ_FALSE = "0";
    public static final int YXBZ_TRUE_I = 1;
    public static final int YXBZ_FALSE_I = 0;

    /**
     * 页面显示记录条数
     */
    public static final int PAGE_NO = 3;

    /**
     * 验证码验证失败信息
     */
    public static final String YZM_FAIL = "验证码验证失败";

    /**
     * 用户名或者密码验证失败信息
     */
    public static final String PORT_PASS_FAIL_NOUSER = "用户名不存在";
    public static final String PORT_PASS_FAIL_PASSERROR = "密码错误";

    /**
     * 系统类型
     */
    public static final String SYSTEMID = "3";

    /**
     * 用户类型
     */
    public static final String ROLEID_HGBM = "1";
    public static final int ROLEID_HGBM_INT = 1;
    public static final String ROLEID_PXJG = "2";
    public static final int ROLEID_PXJG_INT = 2;
    public static final String ROLEID_QY = "3";

    /**
     * 账户无效状态信息
     */
    public static final String PORT_UNUSER = "账户处于无效状态，请联系管理员";

    /**
     * 月份常量
     */
    public static final String[] MONTH = new String[] { "一月", "二月", "三月", "四月",
            "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };

    /**
     * 日期常量
     */
    public static final String[] DAY = new String[] { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
            "29", "30", "31" };

    /**
     * 新增驾驶员
     */
    public static final String JSY_TYPE_XZ = "1";

    /**
     * 存量驾驶员
     */
    public static final String JSY_TYPE_CL = "2";

    /**
     * 证照状态   1：有效  2：无效
     */
    public static final String ZZZT_YX = "1";
    public static final String ZZZT_WX = "2";

    /**
     * 从业状态  1：从业  2：待业  8：吊销  9：注销
     */
    public static final String CYZT_CY = "1";
    public static final String CYZT_DY = "2";
    public static final String CYZT_DX = "8";
    public static final String CYZT_ZX = "9";

    /**
     * 车辆营运状态
     */
    public static final String CLYYZT_YY = "10";

    /**
     * 企业经营状态
     */
    public static final String QYJYZT_YY = "1";

    /**
     * 卡片操作类型
     * 1：发卡   2：补卡   3：更新   4：激活  5：吊销
     */
    public static final String KPCZLX_FK = "1";
    public static final String KPCZLX_BK = "2";
    public static final String KPCZLX_GX = "3";
    public static final String KPCZLX_JH = "4";
    public static final String KPCZLX_DX = "5";


    /**
     * 补卡原因
     * 1：损坏  2：丢失
     */
    public static final String BKYY_SH = "1";
    public static final String BKYY_GS = "2";


    /**
     * 驾驶员入职申请
     * 1：新入职
     * 2：换公司
     */
    public static final String JSYRZSQ_XRZ = "1";
    public static final String JSYRZSQ_HGS = "2";

    /**
     * 服务单位变更
     */
    public static final String FWDWBG_ZZ = "在职";
    public static final String FWDWBG_LZ = "离职";
    public static final String FWDWBG_CT = "辞退";

    /**
     * 服务资格变更
     * 1：上车
     2：车辆变更
     3：注销
     4：吊销
     */
    public static final String FWZGBG_SC = "1";
    public static final String FWZGBG_CLBG = "2";
    public static final String FWZGBG_ZX = "3";
    public static final String FWZGBG_DX = "4";


    /**
     *  证件类型
     */
    public static final String ZJLX_FWZGZ = "2";


    /**
     * 证件加入黑名单方式
     * 1：人工    2：自动
     */
    public static final String ADDBLACKTYPE_RG = "1";
    public static final String ADDBLACKTYPE_ZD = "2";



    /**
     * 年审处理机构
     * 1：企业    2：行管
     */
    public static final String NSCLJG_QY = "1";
    public static final String NSCLJG_HG = "2";


    /**
     * 注销原因
     */
    public static final String ZXYY_LZ = "1";
    public static final String ZXYY_CT = "2";

    /**
     * 卡片状态
     */
    public static final int CARDTYPE_ZC = 1;
    public static final int CARDTYPE_YSY = 2;
    public static final int CARDTYPE_HMD = 3;

    /**
     * 系统标识
     */
    public static final String SYSTEMID_JKZH = "1";
    public static final String SYSTEMID_QYZX = "2";

    public static final HashMap<String, String> zxyyMap = new HashMap<String, String>();

    static {
        zxyyMap.put(ZXYY_LZ, FWDWBG_LZ);
        zxyyMap.put(ZXYY_CT, FWDWBG_CT);
    }

    /**
     * 卡片使用性质
     */
    public static final String CARD_USETYPE_ZS = "1";
    public static final String CARD_USETYPE_LS = "2";

    /**
     * 从业资格证打印模板
     */
    //	public static final String EMPLOYEE_PRINT_TEMPLATE = ManageFile.ReadFile(SystemConstants.PROJECT_HOME+"printemployeetemplate.xml");

    public static final String CPYS_LS = "1";
    public static final String VCLTYPE = "出租车";

    /**
     * 防伪密标类型:1、文字，2：图片
     */
    public static final String MBLX_PIC = "2";
    public static final String MBLX_WZ = "1";

    /**
     * 任务执行成功
     */
    public static final Integer TASK_RUN_SUCCESS = 0;

    /**
     * 任务不存在
     */
    public static final Integer TASK_RUN_RWBCZ = -1;

    /**
     * 任务已删除
     */
    public static final Integer TASK_RUN_RWYSC = -2;

    public static final HashMap<Integer, String> taskTitleMap = new HashMap<Integer, String>();

    static {
        taskTitleMap.put(TASK_RUN_RWBCZ, "任务不存在");
        taskTitleMap.put(TASK_RUN_RWYSC, "任务已删除");
    }
    /**
     * 任务执行类型  不定时
     */
    public static final String TASK_RUN_TYPE_ALL = "2";

    /**
     * 任务执行类型  定时
     */
    public static final String TASK_RUN_TYPE_TIMING = "1";


    /**
     * 执行中任务map
     */
    public static final ConcurrentHashMap<String, TaskRunBean> taskRunMap = new ConcurrentHashMap<String, TaskRunBean>();

    /**
     * 用户与执行中任务关系
     */
    public static final ConcurrentHashMap<Integer, UserTaskRunBean> userTaskRunMap = new ConcurrentHashMap<Integer, UserTaskRunBean>();

    public static final int HEADER_LEN = 12;

    public static final int TAIL_LEN = 2;

    static int sn;

    public synchronized static int genSn() {
        if (sn <= Integer.MAX_VALUE)
            sn++;
        else {
            sn = 0;
        }
        return (short) sn;
    }

}

