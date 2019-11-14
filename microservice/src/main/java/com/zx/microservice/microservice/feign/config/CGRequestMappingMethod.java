package com.zx.microservice.microservice.feign.config;

import com.zx.microservice.microservice.feign.controller.UserClient;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

/**
 * @author zhouxin
 * @date 2018/12/17
 */
public class CGRequestMappingMethod implements MethodInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(CGRequestMappingMethod.class);

    private BeanFactory beanFactory;

    private String serviceName;

    public CGRequestMappingMethod(BeanFactory beanFactory, String serviceName) {
        this.beanFactory = beanFactory;
        this.serviceName = serviceName;
    }

    public Object getInstance(Class<?> clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,objects);
        }
        logger.info("------------>CGRequestMappingMethod#intercept");

        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = UserClient.class.getMethod("getEnv", String.class);
        Parameter parameter = method.getParameters()[0];

        parameter.isNamePresent();

        DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

        Stream.of(nameDiscoverer.getParameterNames(method)).forEach(logger::info);
    }
}
