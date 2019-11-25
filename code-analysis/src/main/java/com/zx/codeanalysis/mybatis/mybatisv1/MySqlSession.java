package com.zx.codeanalysis.mybatis.mybatisv1;

/**
 * @author zhouxin
 * @date 2018/10/22
 */
public class MySqlSession {
  private MyConfiguration configuration;
  private MyExecutor executor;

  public MySqlSession(MyConfiguration configuration, MyExecutor executor) {
    this.configuration = configuration;
    this.executor = executor;
  }

  public <T> T getMapper(Class<T> clazz) {
    return configuration.getMapper(clazz, this);
  }

  public <T> T selectOne(String statement, Object[] parameter, Class<T> returnType) {
    return executor.query(statement, parameter, configuration, returnType);
  }
}
