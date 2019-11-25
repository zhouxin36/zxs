package com.zx.microservice.microservice.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouxin
 * @date 2019/2/18
 */
@RestController
@RequestMapping("/scope")
public class ScopeController {

  private final ConfigurableBeanFactory configurableBeanFactory;

  public ScopeController(ConfigurableBeanFactory configurableBeanFactory) {
    this.configurableBeanFactory = configurableBeanFactory;
  }

  @RequestMapping("/destroy")
  public void destroy(String beanName) {
    configurableBeanFactory.destroyBean(beanName, configurableBeanFactory.getBean(beanName));
  }
}
