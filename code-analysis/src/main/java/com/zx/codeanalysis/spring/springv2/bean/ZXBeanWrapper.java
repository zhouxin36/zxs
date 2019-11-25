package com.zx.codeanalysis.spring.springv2.bean;

import com.zx.codeanalysis.mybatis.mybatisv2.config.ZXConfiguration;
import com.zx.codeanalysis.spring.springv2.aop.TransactionalProxy;
import com.zx.codeanalysis.spring.springv2.aop.ZXAopConfig;
import com.zx.codeanalysis.spring.springv2.aop.ZXCGAopProxy;

import java.lang.reflect.Modifier;

/**
 * @author zhouxin
 * @date 2018/11/13
 */
public class ZXBeanWrapper {

  private Object originalInstance;

  private Object wrapperInstance;

  public ZXBeanWrapper(Object originalInstance, ZXConfiguration configuration, ZXAopConfig zxAopConfig) {
    this.originalInstance = originalInstance;
    TransactionalProxy transactionalProxy = new TransactionalProxy(configuration);
    ZXCGAopProxy zxcgAopProxy = new ZXCGAopProxy();
    zxcgAopProxy.setAopConfig(zxAopConfig);
    if (Modifier.isFinal(originalInstance.getClass().getModifiers())) {
      this.wrapperInstance = originalInstance;
    } else {
//            this.wrapperInstance = transactionalProxy.getInstance(originalInstance,transactionalProxy);
      this.wrapperInstance = zxcgAopProxy.getInstance(transactionalProxy.getInstance(originalInstance, transactionalProxy));
    }
  }

  public Object getOriginalInstance() {
    return originalInstance;
  }

  public Object getWrapperInstance() {
    return wrapperInstance;
  }
}
