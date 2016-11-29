package com.lcc.framework.util;

import java.math.BigDecimal;
/**
 * Created by lcc on 2016/11/29.
 */
public class MathUtils {
    /**
     * 相加方法
     * @param v1	加数
     * @param v2	被加数
     * @return
     */
    public static final BigDecimal add(BigDecimal v1, BigDecimal v2){
        return v1.add(v2).setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 相减方法
     * @param v1	减数
     * @param v2  被减数
     * @return
     */
    public static final BigDecimal sub(BigDecimal v1, BigDecimal v2){
        return v1.subtract(v2).setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 相乘方法
     * @param v1	乘数
     * @param v2  被乘数
     * @return
     */
    public static final BigDecimal multiply(BigDecimal v1, BigDecimal v2){
        return v1.multiply(v2).setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 比较v1与v2的值大小
     * @param v1
     * @param v2
     * @return
     */
    public static final boolean compareDecimal(BigDecimal v1, BigDecimal v2){
        return v1.intValue() >= v2.intValue();
    }

}
