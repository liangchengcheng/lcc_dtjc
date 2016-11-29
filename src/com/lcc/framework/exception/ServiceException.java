package com.lcc.framework.exception;

/**
 * Created by lcc on 2016/11/29.
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID= 8452042078986632665L;

    private String errorCode= "";

    public ServiceException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
