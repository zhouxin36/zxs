package com.zx.codeanalysis.pattern.proxy.nest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Consumer;

/**
 * @author zhouxin
 * @since 2019/3/13
 */
public class ProxyNestCGInterface {

    private final static Logger logger = LoggerFactory.getLogger(ProxyNestCGInterface.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Consumer<String> target = str -> logger.info("Heheh:{}",str);
        for (int i = 0; i < 10; i++) {
            MyProxyInvocation2 myProxyInvocation = new MyProxyInvocation2();
            target = myProxyInvocation.getProxy(target,i);
            target.accept("hdahd");
            System.out.println("-----------------------------------------华丽的分界线-------------------------------------------");
        }
        long end = System.currentTimeMillis();
        logger.info("------------->运行时间，time:{}ms",end-start);
    }
}

class MyProxyInvocation2 implements MethodInterceptor {

    private final Logger logger= LoggerFactory.getLogger(MyProxyInvocation.class);

    private Object target;

    private int index;

    public <T> T getProxy(T target,int index){
        this.target = target;
        this.index = index;
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setInterfaces(target.getClass().getInterfaces());
        //noinspection unchecked
        return (T)enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object value;
        if(Object.class.equals(method.getDeclaringClass())){
            value = method.invoke(target,args);
            return value;
        }
        logger.info("---------->开始,index:{}",index);
        value = method.invoke(target,args);
        logger.info("---------->结束,index:{}",index);
        return value;
    }
}