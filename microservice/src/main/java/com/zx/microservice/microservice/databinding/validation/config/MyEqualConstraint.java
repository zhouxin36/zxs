package com.zx.microservice.microservice.databinding.validation.config;


import com.zx.microservice.microservice.databinding.validation.annotation.ZXEqual;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zhouxin
 * @date 2018-11-25
 */
public class MyEqualConstraint implements ConstraintValidator<ZXEqual,String> {
    @Override
    public void initialize(ZXEqual constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate("打你哟");
        return "zhouxin".equals(value);
    }
}
