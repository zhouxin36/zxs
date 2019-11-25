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
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class SessionBean implements ISessionBean {
  private UUID uuid;

  public SessionBean() {
    uuid = UUID.randomUUID();
  }

  public void printId() {
    System.out.println("SessionBean:" + uuid);
  }
}