package com.zx.codeanalysis.mybatis.mybatisv2.config;

import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXSelect;
import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXUpdate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class ZXMapperProxy implements InvocationHandler {

  private ZXSqlSession sqlSession;

  ZXMapperProxy(ZXSqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (method.isAnnotationPresent(ZXSelect.class)) {
      ZXSelect annotation = method.getAnnotation(ZXSelect.class);
      String sql = annotation.value();
      return sqlSession.query(sql, args, method.getReturnType());
    }
    if (method.isAnnotationPresent(ZXUpdate.class)) {
      ZXUpdate annotation = method.getAnnotation(ZXUpdate.class);
      String sql = annotation.value();
      return sqlSession.update(sql, args);
    }
    return method.invoke(this, args);
  }
}
