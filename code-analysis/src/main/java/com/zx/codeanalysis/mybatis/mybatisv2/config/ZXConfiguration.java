package com.zx.codeanalysis.mybatis.mybatisv2.config;

import com.zx.codeanalysis.base.DateSource;
import com.zx.codeanalysis.mybatis.mybatisv2.datasource.ZXConnectionPool;
import com.zx.codeanalysis.mybatis.mybatisv2.handler.ZXDefalutResultSetHandler;
import com.zx.codeanalysis.mybatis.mybatisv2.handler.ZXDefaultStatementHandler;
import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXResultSetHandler;
import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXStatementHandler;
import com.zx.codeanalysis.mybatis.mybatisv2.plugin.ZXInterceptorChain;

import java.lang.reflect.Proxy;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class ZXConfiguration {

  private ZXConnectionPool connectionPool;

  private ZXInterceptorChain interceptorChain;

  public ZXConfiguration(ZXInterceptorChain interceptorChain) {
    DateSource dateSource = DateSource.COMMON;
//        try {
//            Class.forName(dateSource.getDriverClass());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    this.connectionPool = new ZXConnectionPool(dateSource);
    this.interceptorChain = interceptorChain;
  }

  <T> T getMapper(Class<T> clazz, ZXSqlSession sqlSession) {
    //noinspection unchecked
    return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ZXMapperProxy(sqlSession));
  }

  public ZXStatementHandler getStatementHandler() {
    return interceptorChain.pluginAll(new ZXDefaultStatementHandler(this));
  }

  public ZXResultSetHandler getResultSetHandler() {
    return interceptorChain.pluginAll(new ZXDefalutResultSetHandler());
  }

  public ZXConnectionPool getConnectionPool() {
    return connectionPool;
  }
}
