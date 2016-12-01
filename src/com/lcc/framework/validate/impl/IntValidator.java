package com.lcc.framework.validate.impl;

import com.lcc.framework.validate.AbstractValidator;

/**
 * Created by lcc on 2016/12/1.
 */
public class IntValidator extends AbstractValidator {

    public String doValidate() {
        String msg = "";
        Integer value = (Integer) getFiledValue();
        int max = Integer.valueOf(getValidation().max());
        int min = Integer.valueOf(getValidation().min());
        if (value.intValue() < min || value.intValue() > max) {
            msg = getValidation().message();
        }
        return msg;
    }

}

