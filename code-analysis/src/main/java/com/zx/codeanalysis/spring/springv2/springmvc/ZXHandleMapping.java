package com.zx.codeanalysis.spring.springv2.springmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author zhouxin
 * @date 2018/11/14
 */
public class ZXHandleMapping {

  private Object controller;

  private Method method;

  private Pattern url;

  public ZXHandleMapping(Object controller, Method method, Pattern url) {
    this.controller = controller;
    this.method = method;
    this.url = url;
  }

  public Object getController() {
    return controller;
  }

  public void setController(Object controller) {
    this.controller = controller;
  }

  public Method getMethod() {
    return method;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public Pattern getUrl() {
    return url;
  }

  public void setUrl(Pattern url) {
    this.url = url;
  }
}
