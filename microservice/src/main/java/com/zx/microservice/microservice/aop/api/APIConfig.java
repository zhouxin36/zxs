package com.zx.microservice.microservice.aop.api;

import com.zx.microservice.microservice.aop.aspect.AspectService;
import com.zx.microservice.microservice.aop.aspect.WeaveMethod;
import com.zx.microservice.microservice.aop.aspect.WeaveMethodImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouxin
 * @since 2019/3/11
 */
@Configuration
public class APIConfig {

  private static final Logger logger = LoggerFactory.getLogger(APIConfig.class);

  @Bean
  public MethodInterceptor myMethodInterceptor() {
    return invocation -> {
      logger.info("APIConfig.MethodInterceptor#methodInterceptor,method:{}", invocation.getMethod().getName());
      return invocation.proceed();
    };
  }

  @Bean
  public ProxyFactoryBean weaveMethod1() throws ClassNotFoundException {
    ProxyFactoryBean p = new ProxyFactoryBean();
    p.setProxyInterfaces(new Class[]{WeaveMethod.class});
    p.setTarget(new WeaveMethodImpl());
    p.setInterceptorNames("myMethodInterceptor");
    return p;
  }

  @Bean
  public ProxyFactoryBean aspectService1(AspectService aspectService) {
    ProxyFactoryBean p = new ProxyFactoryBean();
    p.setTarget(aspectService);
    p.setInterceptorNames("myMethodInterceptor");
    p.setProxyTargetClass(true);
    return p;
  }
}
