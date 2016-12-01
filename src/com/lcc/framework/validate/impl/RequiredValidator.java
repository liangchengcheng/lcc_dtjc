package com.lcc.framework.validate.impl;

import com.lcc.framework.validate.AbstractValidator;

/**
 * Created by lcc on 2016/12/1.
 */
public class RequiredValidator  extends AbstractValidator {

    public String doValidate() {
        String msg = "";
        if(getFiledValue() == null){
            msg = getValidation().message();
        }
        return msg;
    }

    @Override
    public boolean blankFiledValue() {
        return false;
    }

}

