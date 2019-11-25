package com.zx.codeanalysis.mybatis.mybatisv2.datasource;

import com.zx.codeanalysis.base.DateSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class ZXConnectionPool {

  private static final Logger LOGGER = LoggerFactory.getLogger(ZXConnectionPool.class);
  private List<ZXConnection> useConnections = new ArrayList<>();

  private List<ZXConnection> unUseConnections = new ArrayList<>();

  private DateSource dateSource;

  public ZXConnectionPool(DateSource dateSource) {
    this.dateSource = dateSource;
  }

  public synchronized ZXConnection getConnection() {
//        logger.info("getConnection start:useConnections{},unUseConnections{}",useConnections,unUseConnections);

    if (unUseConnections.isEmpty()) {
      try {
        Connection connection = DriverManager.getConnection(dateSource.getUrl(), dateSource.getUserName(), dateSource.getPassword());
        ZXConnection zxConnection = new ZXConnection(connection, this);
        useConnections.add(zxConnection);
        LOGGER.info("getConnection end isEmpty:useConnections{},unUseConnections{}", useConnections, unUseConnections);
        return zxConnection;
      } catch (SQLException e) {
        e.printStackTrace();
        return null;
      }
    } else {
      ZXConnection zxConnection = unUseConnections.get(0);
      unUseConnections.remove(zxConnection);
      useConnections.add(zxConnection);
      LOGGER.info("getConnection end:useConnections{},unUseConnections{}", useConnections, unUseConnections);
      return zxConnection;
    }
  }

  synchronized void setConnection(ZXConnection connection) {
//        logger.info("setConnection start:useConnections{},unUseConnections{}",useConnections,unUseConnections);
    unUseConnections.add(connection);
    useConnections.remove(connection);
    LOGGER.info("setConnection end:useConnections{},unUseConnections{}", useConnections, unUseConnections);
  }


}
