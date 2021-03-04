package com.zx.microservice.microservice.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopProxy {

  @Before("execution(* com.zx.microservice.microservice.config.HelloService.a())")
  public void test(){
    System.out.println("test");
  }
}

