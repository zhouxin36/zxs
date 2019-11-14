package com.zx.microservice.microservice.databinding.validation.controller;

import com.zx.microservice.microservice.bean.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhouxin
 * @date 2018-11-25
 */

@RestController
public class ValidationUserController {

    @RequestMapping(value = "/select", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public User select(@Valid @RequestBody User user) {
        return user;
    }

    @RequestMapping(value = "/select2", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public User select2(@RequestBody User user) {
        return user;
    }
}
