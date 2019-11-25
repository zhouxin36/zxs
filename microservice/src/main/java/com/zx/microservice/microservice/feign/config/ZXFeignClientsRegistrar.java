package com.zx.microservice.microservice.feign.config;

import com.zx.microservice.microservice.feign.annotation.EnableZXFeign;
import com.zx.microservice.microservice.feign.annotation.ZXFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author zhouxin
 * @date 2018/12/17
 */
public class ZXFeignClientsRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware, BeanFactoryAware {

  private static final Logger logger = LoggerFactory.getLogger(ZXFeignClientsRegistrar.class);

  private Environment environment;

  private BeanFactory beanFactory;

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
    Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableZXFeign.class.getName());
    if (!(annotationAttributes.get("classes") instanceof Class<?>[])) {
      return;
    }
    Class<?>[] classes = (Class<?>[]) annotationAttributes.get("classes");
    Stream.of(classes)
        .filter(Class::isInterface)
        .filter(clazz -> clazz.isAnnotationPresent(ZXFeignClient.class))
        .forEach(clazz -> {
          String serviceName = environment.resolvePlaceholders(clazz.getAnnotation(ZXFeignClient.class).name());
          String beanName = "ZXFeign." + environment.resolvePlaceholders(clazz.getAnnotation(ZXFeignClient.class).name());
          CGRequestMappingMethod cgRequestMappingMethod = new CGRequestMappingMethod(beanFactory, serviceName);
          Object instance = cgRequestMappingMethod.getInstance(clazz);
          if (registry instanceof SingletonBeanRegistry) {
            SingletonBeanRegistry registry1 = (SingletonBeanRegistry) registry;
            registry1.registerSingleton(beanName, instance);
          } else {
            registerBeanByFactoryBean(beanName, instance, clazz, registry);
          }
        });
  }

  private void registerBeanByFactoryBean(String serviceName, Object instance, Class<?> clazz, BeanDefinitionRegistry registry) {
    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RestClientClassFactoryBean.class);
    beanDefinitionBuilder.addConstructorArgValue(instance);
    beanDefinitionBuilder.addConstructorArgValue(clazz);
    AbstractBeanDefinition beanDefinition =
        beanDefinitionBuilder.getBeanDefinition();
    registry.registerBeanDefinition(serviceName, beanDefinition);
  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = beanFactory;
  }

  private static class RestClientClassFactoryBean implements FactoryBean {

    private final Object proxy;

    private final Class<?> restClientClass;

    private RestClientClassFactoryBean(Object proxy, Class<?> restClientClass) {
      this.proxy = proxy;
      this.restClientClass = restClientClass;
    }

    @Override
    public Object getObject() throws Exception {
      return proxy;
    }

    @Override
    public Class<?> getObjectType() {
      return restClientClass;
    }

    @Override
    public boolean isSingleton() {
      return true;
    }
  }
}
