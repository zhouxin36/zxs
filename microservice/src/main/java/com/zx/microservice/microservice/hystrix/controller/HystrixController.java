package com.zx.microservice.microservice.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouxin
 * @since 2019-11-17
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixController {

  private static final Logger logger = LoggerFactory.getLogger(HystrixController.class);

  @GetMapping("/error")
  @HystrixCommand(threadPoolKey = "hystrixError",
      fallbackMethod = "hehe",
      commandProperties = {@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "50")})
  public String error() throws InterruptedException {
    int time = new Random().nextInt(100);
    System.out.println("handle time:" + time + "ms");
    TimeUnit.MILLISECONDS.sleep(time);
    return "zhouxin";
  }

  public String hehe() {
    return "error";
  }
}
