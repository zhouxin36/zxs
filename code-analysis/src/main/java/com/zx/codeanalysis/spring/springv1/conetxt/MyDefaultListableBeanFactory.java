package com.zx.codeanalysis.spring.springv1.conetxt;

import com.zx.codeanalysis.spring.springv1.bean.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouxin
 * @date 2018/10/30
 */
public class MyDefaultListableBeanFactory extends MyAbstractApplicationContext {

  //用来保存配置信息
  protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

  @Override
  protected void refreshBeanFactory() {

  }
}
