package com.zx.microservice.microservice.databinding;

import com.zx.microservice.microservice.bean.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.Valid;

/**
 * 需要使用{@link Valid}
 * @author zhouxin
 * @since 2019/2/28
 */
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(User.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",
                "id.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
                "userName.required");
    }
}
