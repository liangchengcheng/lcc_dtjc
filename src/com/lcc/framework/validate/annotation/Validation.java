package com.lcc.framework.validate.annotation;

import com.lcc.framework.validate.ValidatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lcc on 2016/11/30.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validation {

    ValidatorType type();

    String regex() default "";

    String max() default "0";

    String min() default "0";

    String message() default "";

    boolean blank() default false;

}
