package com.zx.microservice.microservice.rest.controller;

import com.alibaba.fastjson.JSON;
import com.zx.microservice.microservice.bean.User;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.StandardServletEnvironment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数处理：{@link HandlerMethodArgumentResolver 处理接口}{@link WebMvcConfigurer#addArgumentResolvers 注册}
 * 返回值处理：{@link HandlerMethodReturnValueHandler 处理接口}{@link WebMvcConfigurer#addReturnValueHandlers 注册}
 * {@link ResponseBody} {@link RequestBody} 处理 {@link RequestResponseBodyMethodProcessor}
 *
 * @author zhouxin
 * @date 2018/11/20
 */
@Controller
public class UserController {

  private final Environment environment;

  public UserController(Environment environment) {
    this.environment = environment;
  }

  /**
   * Content-Type ： application/properties
   * // Accept ： application/json
   *
   * @param user
   * @return
   */
  @PostMapping(value = "/propertiesToJson"
//            , consumes = "application/properties"
//            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  @ResponseBody
  public User propertiesToJson(@RequestBody User user) {
    return user;
  }

  /**
   * // Accept ： application/properties
   * Content-Type ： application/json
   *
   * @param user
   * @return
   */
  @PostMapping(value = "/jsonToProperties"
      , produces = "application/properties"
//            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  @ResponseBody
  public User jsonToProperties(@RequestBody User user) {
    return user;
  }

  /**
   * // Accept ： application/properties
   * Content-Type ： application/json
   *
   * @param user
   * @return
   */
  @GetMapping(value = "/jsonToProperties2"
      , produces = "application/properties"
//            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
  )
  @ResponseBody
  public User jsonToProperties2(User user) {
    return user;
  }

  @PostMapping(value = "/jsonToJson")
  @ResponseBody
  public User jsonToJson(@RequestBody User user) {
    return user;
  }

  @GetMapping(value = "/getEnv")
  @ResponseBody
  public Map getEnv() {
    Map<String, Object> map = new HashMap<>();
    if (!(environment instanceof StandardServletEnvironment)) {
      return map;
    }
    for (PropertySource sources :
        ((StandardServletEnvironment) environment).getPropertySources()) {
      extract("", map, sources);
    }
    return map;
  }

  private void extract(String root, Map<String, Object> map,
                       PropertySource<?> source) {
    if (source instanceof CompositePropertySource) {
      for (PropertySource<?> nest : ((CompositePropertySource) source)
          .getPropertySources()) {
        extract(source.getName() + ":", map, nest);
      }
    } else {
      map.put(root + source.getName(), JSON.toJSON(source));
    }
  }
}
