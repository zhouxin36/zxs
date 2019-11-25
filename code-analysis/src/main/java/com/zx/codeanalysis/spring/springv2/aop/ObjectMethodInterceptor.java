package com.zx.codeanalysis.spring.springv2.aop;

import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author zhouxin
 * @since 2019/3/13
 */
public interface ObjectMethodInterceptor extends MethodInterceptor {

  Object gerTarget();
}
