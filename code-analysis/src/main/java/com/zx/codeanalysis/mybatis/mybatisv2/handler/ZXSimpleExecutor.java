package com.zx.codeanalysis.mybatis.mybatisv2.handler;

import com.zx.codeanalysis.mybatis.mybatisv2.config.ZXConfiguration;
import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXExecutor;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class ZXSimpleExecutor implements ZXExecutor {

  private ZXConfiguration configuration;

  public ZXSimpleExecutor(ZXConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public <T> T selectOne(String sql, Object[] args, Class<T> returnType) {
    return configuration.getStatementHandler().query(sql, args, returnType);
  }

  @Override
  public int update(String sql, Object[] args) {
    return configuration.getStatementHandler().update(sql, args);
  }
}
