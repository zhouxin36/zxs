package com.zx.codeanalysis.spring.springv1.bean;

/**
 * @author zhouxin
 * @date 2018/10/26
 */
public class BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName)  {
        return bean;
    }
}
