package com.zx.codeanalysis.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouxin
 * @date 2018/10/16
 */
public class BeanFactory {
  //线程安全
  private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

  private BeanFactory() {
  }

  public static Object getBean(String className) {

    if (!ioc.containsKey(className)) {
      Object obj = null;
      try {
        obj = Class.forName(className).getDeclaredConstructor().newInstance();
        ioc.put(className, obj);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return obj;
    } else {
      return ioc.get(className);
    }

  }


}
