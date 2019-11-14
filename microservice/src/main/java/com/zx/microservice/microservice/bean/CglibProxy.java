package com.zx.microservice.microservice.bean;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @since 2018/10/17
 */
public class CglibProxy implements MethodInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(CglibProxy.class);

    public static <T> T getInstance(Class<T> clazz){
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(clazz);

        enhancer.setCallback(new CglibProxy());

        //noinspection unchecked
        return (T)enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //业务
        logger.info("前");
        Object o = proxy.invokeSuper(obj,args);
        logger.info("后");

        return o;
    }
}
