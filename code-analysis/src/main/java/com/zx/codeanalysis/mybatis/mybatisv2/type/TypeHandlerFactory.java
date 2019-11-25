package com.zx.codeanalysis.mybatis.mybatisv2.type;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class TypeHandlerFactory {
  private static Map<Class, TypeHandler> typeHandlerMap = new HashMap<>();

  static {
    typeHandlerMap.put(String.class, new StringTypeHandler());
    typeHandlerMap.put(Integer.class, new IntegerTypeHandler());
  }

  public static TypeHandler getTypeHandler(Class clazz) {
    TypeHandler typeHandler = typeHandlerMap.get(clazz);
    if (typeHandler == null) {
      throw new RuntimeException("没有这个TypeHandler");
    }
    return typeHandler;
  }
}
