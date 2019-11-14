package com.zx.microservice.microservice;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.bind.support.WebBindingInitializer;

/**
 * @author zhouxin
 * @date 2019-02-06
 */
public class Question {
    /**
     * Spring MVC uses a {@link WebBindingInitializer} to initialize a {@link WebDataBinder} for a particular
     * request. If you create your own {@link ConfigurableWebBindingInitializer} @Bean, Spring Boot will
     * automatically configure Spring MVC to use it.
     *
     * add {@link WebMvcAutoConfiguration.EnableWebMvcConfiguration#getConfigurableWebBindingInitializer()}
     */
}
