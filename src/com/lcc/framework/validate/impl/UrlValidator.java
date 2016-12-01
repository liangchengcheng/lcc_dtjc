package com.lcc.framework.validate.impl;

import com.lcc.framework.validate.AbstractValidator;

/**
 * Created by lcc on 2016/12/1.
 */
public class UrlValidator extends AbstractValidator {

    public String doValidate() {
        String msg = "";
        String filedValue = (String)getFiledValue();
        if(!filedValue.startsWith("http://")){
            msg = getValidation().message();
        }
        return msg;
    }

}