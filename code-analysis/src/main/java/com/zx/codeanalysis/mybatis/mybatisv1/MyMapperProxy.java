package com.zx.codeanalysis.mybatis.mybatisv1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public class MyMapperProxy implements InvocationHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyMapperProxy.class);

  private MySqlSession sqlSession;

  public MyMapperProxy(MySqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (Object.class.equals(method.getDeclaringClass())) {
      return method.invoke(this, args);
    }
    Select annotation = method.getAnnotation(Select.class);

    if (annotation != null) {
      String sql = annotation.sql();
      return sqlSession.selectOne(sql, args, method.getReturnType());
    } else {
      LOGGER.info("未找到Select注解");
      return null;
    }
  }
}
