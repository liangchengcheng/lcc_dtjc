package com.lcc.framework.validate.impl;

/**
 * Created by lcc on 2016/12/1.
 */
public class EmailValidator extends RegexValidator {

    public static final String emailAddressPattern =
            "\\b(^['_A-Za-z0-9-]+(\\.['_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";

    public EmailValidator() {
        setExpression(emailAddressPattern);
        setCaseSensitive(false);
    }
}
