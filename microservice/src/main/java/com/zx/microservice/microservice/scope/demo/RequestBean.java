package com.zx.microservice.microservice.scope.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

/**
 * @author zhouxin
 * @since 2019/2/26
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
public class RequestBean implements IRequestBean {
  private UUID uuid;

  public RequestBean() {
    uuid = UUID.randomUUID();
  }

  public void printId() {
    System.out.println("RequestBean:" + uuid);
  }
}