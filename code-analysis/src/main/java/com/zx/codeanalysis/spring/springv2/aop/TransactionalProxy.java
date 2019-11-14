package com.zx.codeanalysis.spring.springv2.aop;

import com.zx.codeanalysis.mybatis.mybatisv2.config.ZXConfiguration;
import com.zx.codeanalysis.spring.annotation.Service;
import com.zx.codeanalysis.spring.annotation.Transactional;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Connection;
/**
 * @author zhouxin
 * @since 2019/2/22
 */
public class TransactionalProxy implements ObjectMethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TransactionalProxy.class);

    public static final InheritableThreadLocal<Connection> threadLocal = new InheritableThreadLocal<>();

    private ZXConfiguration configuration;

    private Object target;

    public TransactionalProxy(ZXConfiguration configuration) {
        this.configuration = configuration;
    }

    public Object getInstance(Object target, Callback ... callbacks){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallbacks(callbacks);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        if(o.getClass().getSuperclass().isAnnotationPresent(Service.class) && method.isAnnotationPresent(Transactional.class)){
            Connection connection = null;
            try{
                connection = configuration.getConnectionPool().getConnection().getWrapConnection();
                connection.setAutoCommit(false);
                threadLocal.set(connection);
                Object invoke = method.invoke(target, objects);
                connection.commit();
                logger.info("-------------TransactionalProxy-----commit---method:{}",method);
                return invoke;
            }catch (Exception e){
                if(null != connection) {
                    connection.rollback();
                    logger.info("-------------TransactionalProxy-----rollback---method:{}",method);
                }
                throw e;
            }finally {
                if(null != connection) {
                    connection.close();
                    logger.info("-------------TransactionalProxy-----close---method:{}",method);
                }
            }
        }else {
            return method.invoke(target, objects);
        }
    }

    @Override
    public Object gerTarget() {
        return this.target;
    }
}
