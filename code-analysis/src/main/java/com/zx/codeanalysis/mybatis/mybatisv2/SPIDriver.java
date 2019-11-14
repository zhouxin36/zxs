package com.zx.codeanalysis.mybatis.mybatisv2;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author zhouxin
 * @since 2019/3/26
 */
public class SPIDriver implements Driver {

    static {
        System.out.println("SPI机制加载");
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return null;
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return false;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
