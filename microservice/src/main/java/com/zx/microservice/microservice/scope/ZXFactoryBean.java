package com.zx.microservice.microservice.scope;

import com.zx.microservice.microservice.bean.CglibProxy;
import com.zx.microservice.microservice.bean.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouxin
 * @since 2019/2/19
 * <p>
 * {@link BeanFactory.getBean("&ZXFactoryBean")} 返回 {@link ZXFactoryBean} &->{@link BeanFactory#FACTORY_BEAN_PREFIX}
 * {@link BeanFactory.getBean("ZXFactoryBean")} 返回 CglibProxy.getInstance(User.class);
 */
@Configuration
public class ZXFactoryBean implements FactoryBean<User> {
  @Override
  public User getObject() throws Exception {
    return CglibProxy.getInstance(User.class);
  }

  @Override
  public Class<?> getObjectType() {
    return User.class;
  }
}
