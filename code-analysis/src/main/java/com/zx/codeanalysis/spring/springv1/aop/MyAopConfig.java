package com.zx.codeanalysis.spring.springv1.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @date 2018/10/30
 */
public class MyAopConfig {

  private Map<Method, MyAspect> points = new HashMap<>();

  public void put(Method target, Object aspect, Method[] points) {
    this.points.put(target, new MyAspect(aspect, points));
  }

  public MyAspect get(Method method) {
    return this.points.get(method);
  }

  public boolean contains(Method method) {
    return this.points.containsKey(method);
  }

  public class MyAspect {
    private Object aspect;
    private Method[] points;

    public MyAspect(Object aspect, Method[] points) {
      this.aspect = aspect;
      this.points = points;
    }

    public Object getAspect() {
      return aspect;
    }

    public void setAspect(Object aspect) {
      this.aspect = aspect;
    }

    public Method[] getPoints() {
      return points;
    }

    public void setPoints(Method[] points) {
      this.points = points;
    }
  }
}
