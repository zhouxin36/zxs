package com.zx.codeanalysis.spring.springv2.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @date 2018/11/13
 */
public class ZXAopConfig {

  private Map<Method, AspectPoint> aspectPointMap = new HashMap<>();

  public void put(Method target, Object aspect, Method preMethod, Method suffixMethod) {
    this.aspectPointMap.put(target, new AspectPoint(aspect, preMethod, suffixMethod));
  }

  public AspectPoint get(Method target) {
    return this.aspectPointMap.get(target);
  }

  class AspectPoint {
    private Object aspect;

    private Method preMethod;

    private Method suffixMethod;

    public AspectPoint(Object aspect, Method preMethod, Method suffixMethod) {
      this.aspect = aspect;
      this.preMethod = preMethod;
      this.suffixMethod = suffixMethod;
    }

    public Object getAspect() {
      return aspect;
    }

    public void setAspect(Object aspect) {
      this.aspect = aspect;
    }

    public Method getPreMethod() {
      return preMethod;
    }

    public void setPreMethod(Method preMethod) {
      this.preMethod = preMethod;
    }

    public Method getSuffixMethod() {
      return suffixMethod;
    }

    public void setSuffixMethod(Method suffixMethod) {
      this.suffixMethod = suffixMethod;
    }
  }
}
