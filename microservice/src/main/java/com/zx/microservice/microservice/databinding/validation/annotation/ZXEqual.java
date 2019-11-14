package com.zx.microservice.microservice.databinding.validation.annotation;


import com.zx.microservice.microservice.databinding.validation.config.MyEqualConstraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zhouxin
 * @date 2018-11-25
 */
@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { MyEqualConstraint.class})
public @interface ZXEqual {
    String message() default "打你哟";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
