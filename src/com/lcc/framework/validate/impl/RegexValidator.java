package com.lcc.framework.validate.impl;

import com.lcc.framework.validate.AbstractValidator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lcc on 2016/11/30.
 */
public class RegexValidator extends AbstractValidator{

    private boolean caseSensitive = true;

    private String expression = "";

    public String doValidate() {
        String msg = "";
        Pattern pattern;
        if (isCaseSensitive()) {
            pattern = Pattern.compile(getValidation().regex());
        } else {
            pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        }
        String compare = (String) getFiledValue();
        compare = compare.trim();
        Matcher matcher = pattern.matcher( compare );
        if (!matcher.matches()) {
            msg = getValidation().message();
        }
        return msg;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String formateValue(){
        return (String) getFiledValue();
    }
}
