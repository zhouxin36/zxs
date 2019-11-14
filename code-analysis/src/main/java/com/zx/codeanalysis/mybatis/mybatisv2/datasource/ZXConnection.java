package com.zx.codeanalysis.mybatis.mybatisv2.datasource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author zhouxin
 * @date 2018-11-11
 */
public class ZXConnection implements InvocationHandler {

    private static final String CLOSE = "close";

    private Connection connection;

    private Connection wrapConnection;

    private ZXConnectionPool connectionPool;

    ZXConnection(Connection connection, ZXConnectionPool zxConnectionPool) {
        this.connection = connection;
        this.connectionPool = zxConnectionPool;
        this.wrapConnection = (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(),new Class[]{Connection.class},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals(CLOSE)){
            connectionPool.setConnection(this);
            return null;
        }
        return method.invoke(connection,args);
    }

    public Connection getWrapConnection() {
        return wrapConnection;
    }
}
