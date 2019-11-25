package com.zx.codeanalysis.mybatis.mybatisv2.handler;

import com.zx.codeanalysis.mybatis.mybatisv2.config.ZXConfiguration;
import com.zx.codeanalysis.mybatis.mybatisv2.interfaces.ZXStatementHandler;
import com.zx.codeanalysis.spring.springv2.aop.TransactionalProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class ZXDefaultStatementHandler implements ZXStatementHandler {

  private ZXConfiguration configuration;

  public ZXDefaultStatementHandler(ZXConfiguration configuration) {
    this.configuration = configuration;
  }

  @Override
  public <T> T query(String sql, Object[] args, Class<T> returnType) {
    Connection connection = null;
    try {
      connection = TransactionalProxy.threadLocal.get() != null
          ? TransactionalProxy.threadLocal.get() : configuration.getConnectionPool().getConnection().getWrapConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      if (args != null) {
        for (int i = 0; i < args.length; i++) {
          preparedStatement.setObject(i + 1, args[i]);
        }
      }
      ResultSet resultSet = preparedStatement.executeQuery();
      return configuration.getResultSetHandler().handler(resultSet, returnType);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      close(connection);
    }
  }

  @Override
  public int update(String sql, Object[] args) {
    Connection connection = null;
    try {
      connection = TransactionalProxy.threadLocal.get() != null
          ? TransactionalProxy.threadLocal.get() : configuration.getConnectionPool().getConnection().getWrapConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      if (args != null) {
        for (int i = 0; i < args.length; i++) {
          preparedStatement.setObject(i + 1, args[i]);
        }
      }
      return preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    } finally {
      close(connection);
    }
  }

  private void close(Connection connection) {
    if (TransactionalProxy.threadLocal.get() == null) {
      if (null != connection) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
