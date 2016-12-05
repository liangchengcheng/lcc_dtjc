package com.lcc.framework.bean.result;

/**
 * Created by lcc on 2016/12/6.
 * 返回的结果。
 */
public class RestBean<T>  {
    static int SUCESSCODE = 1;
    static int FAILCODE = 0;
    int ret = SUCESSCODE;
    String msg = "";
    T rv;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public T getRv() {
        return rv;
    }

    public void setRv(T rv) {
        this.rv = rv;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setSuccess(){
        this.ret = this.SUCESSCODE;
    }
    public void setSuccess(String msg){
        this.ret = this.SUCESSCODE;
        this.msg = msg;
    }
    public void setSuccess(T res){
        this.ret = this.SUCESSCODE;
        this.rv = res;
    }
    public void setSuccess(String msg, T res){
        this.ret = this.SUCESSCODE;
        this.msg = msg;
        this.rv = res;
    }
    public void setFail(){
        this.ret = this.FAILCODE;
    }
    public void setFail(String msg){
        this.ret = this.FAILCODE;
        this.msg = msg;
    }
    public void setFail(String msg, T res){
        this.ret = this.FAILCODE;
        this.msg = msg;
        this.rv = res;
    }
    public static int getSUCESSCODE() {
        return SUCESSCODE;
    }

    public static void setSUCESSCODE(int sUCESSCODE) {
        SUCESSCODE = sUCESSCODE;
    }

    public static int getFAILCODE() {
        return FAILCODE;
    }

    public static void setFAILCODE(int fAILCODE) {
        FAILCODE = fAILCODE;
    }
}
