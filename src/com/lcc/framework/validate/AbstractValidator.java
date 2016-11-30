package com.lcc.framework.validate;

import com.lcc.framework.validate.annotation.Validation;

import java.io.UnsupportedEncodingException;


public abstract class AbstractValidator{

    private Validation validation = null;

    private Object filedValue = null;

    public Validation getValidation() {
        return validation;
    }

    public Object getFiledValue() {
        return filedValue;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public void setFiledValue(Object filedValue) {
        this.filedValue = filedValue;
    }

    public String validate(){
        String msg = "";
        if(getValidation().blank() && blankFiledValue()){
            return msg;
        }else{
            return doValidate();
        }
    }

    public boolean blankFiledValue(){
        if(filedValue != null){
            if(filedValue instanceof String){
                String value = formateValue();
                if(!value.equalsIgnoreCase("")){
                    return false;
                }
            }else{
                return false;
            }
        }
        return true;
    }

    public String formateValue(){
        String value = (String) filedValue;
        try {
            return new String(value.getBytes(), "iso-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract String doValidate();

}
