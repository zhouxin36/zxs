package com.zx.codeanalysis.spring.springv2.aop;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author zhouxin
 * @date 2018/11/13
 */
public class ZXCGAopProxy implements ObjectMethodInterceptor {

    private ZXAopConfig aopConfig;

    private Object target;

    public void setAopConfig(ZXAopConfig aopConfig) {
        this.aopConfig = aopConfig;
    }

    public Object getInstance(Object target){
//        Enhancer enhancer = new Enhancer();
//
//        enhancer.setSuperclass(clazz);
//        enhancer.setCallback(this);
//        return enhancer.create();

        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        Object tar = target;
        while (tar instanceof Factory) {
            ObjectMethodInterceptor callback = (ObjectMethodInterceptor) ((Factory) tar).getCallback(0);
            tar = callback.gerTarget();
        }
        enhancer.setSuperclass(tar.getClass());
        return enhancer.create();
    }



    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        ZXAopConfig.AspectPoint aspectPoint = aopConfig.get(method);
        if(aspectPoint != null){
            aspectPoint.getPreMethod().invoke(aspectPoint.getAspect());
        }
//        Object object = methodProxy.invokeSuper(o, objects);
        Object object = method.invoke(target,objects);
        if(aspectPoint != null){
            aspectPoint.getSuffixMethod().invoke(aspectPoint.getAspect());
        }
        return object;
    }

    @Override
    public Object gerTarget() {
        return this.target;
    }
}
