package com.zx.microservice.microservice.scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * 此接口的语义类似于BeanPostProcessor的语义，但有一个主要区别：BeanFactoryPostProcessor对bean配置元数据进行操作。
 * 也就是说，Spring IoC容器允许BeanFactoryPostProcessor读取配置元数据，并可能在容器实例化除BeanFactoryPostProcessor实例之外的任何bean之前更改它。
 * <p>
 * before every {@link BeanPostProcessor}
 *
 * @author zhouxin
 * @since 2019/2/19
 */
@Configuration
class ZXBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

  private static final Logger logger = LoggerFactory.getLogger(ZXBeanFactoryPostProcessor.class);

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        logger.info("--------------------------ZXBeanFactoryPostProcessor---------------beanFactory-----------------,beanFactory:{}",beanFactory);
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}