package com.zx.codeanalysis.pattern.proxy.transactional;

import com.zx.codeanalysis.spring.springv2.aop.TransactionalProxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.zx.codeanalysis.pattern.proxy.transactional.DateSource.COMMON;

/**
 * @author zhouxin
 * @since 2019/2/22
 */
public class CGLIBTransactionalProxy implements MethodInterceptor {

    public static final InheritableThreadLocal<Connection> threadLocal = new InheritableThreadLocal<>();

    private static final Logger logger = LoggerFactory.getLogger(CGLIBTransactionalProxy.class);

    public Object getInstance(Class<?> clazz){
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(clazz);

        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if(!method.isAnnotationPresent(HEHETransactional.class)){
            return proxy.invokeSuper(obj,args);
        }

        Connection connection = null;
        try{
            connection = DriverManager.getConnection(COMMON.getUrl(), COMMON.getUserName(), COMMON.getPassword());
            connection.setAutoCommit(false);
            threadLocal.set(connection);
            Object o = proxy.invokeSuper(obj,args);
            connection.commit();
            logger.info("-------------CGLIBTransactionalProxy-----commit---method:{}",method);
            return o;
        }catch (Exception e){
            if(null != connection) {
                connection.rollback();
                logger.info("-------------CGLIBTransactionalProxy-----rollback---method:{}",method);
            }
        }finally {
            close(connection);
            logger.info("-------------CGLIBTransactionalProxy-----close---method:{}",method);
        }
        return null;
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
