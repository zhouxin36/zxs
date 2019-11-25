package com.zx.codeanalysis.mybatis.mybatisv1;

import com.zx.codeanalysis.base.DateSource;
import com.zx.codeanalysis.mybatis.mybatisv1.plugin.MyInterceptorChain;

import java.lang.reflect.Proxy;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public class MyConfiguration {

  private DateSource dateSource;

  private MyInterceptorChain interceptorChain;

  public MyConfiguration(DateSource dateSource, MyInterceptorChain interceptorChain) {
    this.dateSource = dateSource;
    this.interceptorChain = interceptorChain;
  }

  public <T> T getMapper(Class<T> clazz, MySqlSession sqlSession) {
    //noinspection unchecked
    return (T) Proxy.newProxyInstance(this.getClass().getClassLoader()
        , new Class[]{clazz}
        , new MyMapperProxy(sqlSession));
  }

  public DateSource getDateSource() {
    return dateSource;
  }

  public MyInterceptorChain getInterceptorChain() {
    return interceptorChain;
  }

  public StatementHandler getStatementHandler() {
    StatementHandler statementHandler = new DefaultStatementHandler(this);
    return this.getInterceptorChain().pluginAll(statementHandler);
  }

  public ResultSetHandler getResultSetHandler() {
    ResultSetHandler resultSetHandler = new DefaultResultSetHandler();
    return this.getInterceptorChain().pluginAll(resultSetHandler);
  }
}
