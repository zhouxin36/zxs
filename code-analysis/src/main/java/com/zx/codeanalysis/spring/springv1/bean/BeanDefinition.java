package com.zx.codeanalysis.spring.springv1.bean;

/**
 * 存储配置文件中的信息
 *
 * @author zhouxin
 * @date 2018/10/25
 */
public class BeanDefinition {

  private String beanClassName;
  private boolean lazyInit = false;
  private String factoryBeanName;

  public String getBeanClassName() {
    return beanClassName;
  }

  public void setBeanClassName(String beanClassName) {
    this.beanClassName = beanClassName;
  }

  public boolean isLazyInit() {
    return lazyInit;
  }

  public void setLazyInit(boolean lazyInit) {
    this.lazyInit = lazyInit;
  }

  public String getFactoryBeanName() {
    return factoryBeanName;
  }

  public void setFactoryBeanName(String factoryBeanName) {
    this.factoryBeanName = factoryBeanName;
  }
}
