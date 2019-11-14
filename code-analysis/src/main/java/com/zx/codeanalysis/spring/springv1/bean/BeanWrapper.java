package com.zx.codeanalysis.spring.springv1.bean;

import com.zx.codeanalysis.spring.springv1.aop.CGAopProxy;
import com.zx.codeanalysis.spring.springv1.aop.DyAopProxy;
import com.zx.codeanalysis.spring.springv1.aop.MyAopConfig;
import com.zx.codeanalysis.spring.springv1.core.FactoryBean;

/**
 * @author zhouxin
 * @date 2018/10/26
 */
public class BeanWrapper extends FactoryBean {

    private BeanPostProcessor postProcessor;

    private Object wrapperInstance;

    private Object originalInstance;

    private CGAopProxy cgAopProxy = new CGAopProxy();

    private DyAopProxy dyAopProxy = new DyAopProxy();

    public BeanWrapper(Object instance){
//        if(instance.getClass().getInterfaces().length != 0){
//
//            this.wrapperInstance = dyAopProxy.getProxy(instance);
//        }else {
            this.wrapperInstance = cgAopProxy.getProxy(instance.getClass());
//
//        }
        this.originalInstance = instance;
    }

    public void setMyAopConfig(MyAopConfig config){
        cgAopProxy.setConfig(config);
        dyAopProxy.setConfig(config);
    }

    public Object getOriginalInstance() {
        return originalInstance;
    }

    public Object getWrapperInstance(){
        return this.wrapperInstance;
    }

    public Class<?> getWrappedClass(){
        return this.wrapperInstance.getClass();
    }

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }
}
