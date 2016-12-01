package com.lcc.framework.validate.impl;

import com.lcc.framework.validate.AbstractValidator;

/**
 * Created by lcc on 2016/12/1.
 */
public class StringValidator extends AbstractValidator {

    public String doValidate() {
        String msg = "";
        String value = (String) getFiledValue();
        int length = value.trim().length();
        int max = Integer.valueOf(getValidation().max());
        int min = Integer.valueOf(getValidation().min());
        if ((min > -1) && (length < min)) {
            msg = getValidation().message();
        } else if ((max > -1) && (length > max)) {
            msg = getValidation().message();
        }
        return msg;
    }

}

