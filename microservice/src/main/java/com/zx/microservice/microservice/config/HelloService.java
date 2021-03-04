package com.zx.microservice.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

  @Autowired
  private HelloService helloService;

  public HelloService a(){
    System.out.println(helloService.toString());
    return helloService;
  }
}
