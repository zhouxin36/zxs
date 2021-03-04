package com.zx.microservice.microservice.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aaa")
public class Controller {

  public final HelloService helloService;


  public Controller(HelloService helloService) {
    this.helloService = helloService;
  }

  @GetMapping("/bbb")
  public String a(){
    return helloService.a().a().toString();
  }
}
