package com.zx.microservice.microservice.feign.controller;

import com.zx.microservice.microservice.feign.annotation.ZXFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhouxin
 * @date 2018/12/17
 */
@ZXFeignClient(name = "${spring.application.name}")
public interface UserClient {

  @GetMapping(value = "/ZXFeignGetEnv")
  String getEnv(@RequestParam("message") String message);
}
