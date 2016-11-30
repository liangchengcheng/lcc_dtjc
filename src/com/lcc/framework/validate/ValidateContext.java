package com.lcc.framework.validate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcc on 2016/11/30.
 */
public class ValidateContext {
    protected static Map<ValidatorType,AbstractValidator> map = new HashMap<ValidatorType, AbstractValidator>();

    static{
        map.put(ValidatorType.REQUIRED, new RequiredValidator());
        map.put(ValidatorType.EMAIL, new EmailValidator());
        map.put(ValidatorType.INT, new IntValidator());
        map.put(ValidatorType.REGEX, new RegexValidator());
        map.put(ValidatorType.STRING, new StringValidator());
        map.put(ValidatorType.URL, new UrlValidator());
    }

    public static String validate(Validation validateAnnotation, Object filedValue) {
        AbstractValidator av = (AbstractValidator) map.get(validateAnnotation.type());
        av.setValidation(validateAnnotation);
        av.setFiledValue(filedValue);
        return av.validate();
    }
}
