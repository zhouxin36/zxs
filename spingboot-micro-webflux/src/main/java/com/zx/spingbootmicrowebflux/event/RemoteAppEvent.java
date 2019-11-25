package com.zx.spingbootmicrowebflux.event;

import org.springframework.context.ApplicationEvent;

public class RemoteAppEvent extends ApplicationEvent {

  /**
   * 应用名称
   */
  private final String appName;
  /**
   * 是否广播到集群
   */
  private final boolean isCluster;
  /**
   * 事件传输类型 HTTP、RPC、MQ
   */
  private String type;

  /***
   * @param source POJO 事件源，JSON 格式
   * @param appName
   * @param isCluster
   */
  public RemoteAppEvent(Object source, String appName, boolean isCluster) {
    super(source);
    this.appName = appName;
    this.isCluster = isCluster;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getAppName() {
    return appName;
  }

}
