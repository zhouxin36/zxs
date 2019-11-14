package com.zx.microservice.microservice.scope;

import com.zx.microservice.microservice.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 作用域查询{@link AbstractBeanFactory#doGetBean}
 * {@link InitializingBean} {@link PostConstruct} init bean
 * {@link DisposableBean} {@link PreDestroy} destroy bean
 * @author zhouxin
 * @date 2019/2/18
 */
@Configuration
public class ScopeConfig implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ScopeConfig.class);

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public User singleton() {
        return new User(1, "singleton");
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public User prototype() {
        return new User(1, "prototype");
    }

    @Bean
    @Scope(WebApplicationContext.SCOPE_REQUEST)
    public User request() {
        return new User(1, "request");
    }

    @Bean
    @RequestScope
    public User request2() {
        return new User(1, "request2");
    }

    @Bean
    @Scope(WebApplicationContext.SCOPE_SESSION)
    public User session() {
        return new User(1, "session");
    }

    @Bean
    @SessionScope
    public User session2() {
        return new User(1, "session2");
    }

    @Bean
    @Scope(WebApplicationContext.SCOPE_APPLICATION)
    public User application() {
        return new User(1, "application");
    }

    @Bean
    @ApplicationScope
    public User application2() {
        return new User(1, "application2");
    }

    /**
     * 无此作用域
     */
    @Bean
    @Scope("websocket")
    public User websocket() {
        return new User(1, "websocket");
    }

    @Override
    public void destroy() throws Exception {
//        logger.info("-------------------ScopeConfig----------------------destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        logger.info("-------------------ScopeConfig----------------------afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
//        logger.info("-------------------ScopeConfig----------------------PostConstruct");
    }

    @PreDestroy
    public void destroy2() {
//        logger.info("-------------------ScopeConfig----------------------PreDestroy");
    }

    /**
     *
     * BeanPostProcessor接口定义了可以实现的回调方法，以提供您自己的（或覆盖容器的默认）实例化逻辑，依赖关系解析逻辑等。
     * 如果要在Spring容器完成实例化，配置和初始化bean之后实现一些自定义逻辑，可以插入一个或多个自定义BeanPostProcessor实现
     *
     * 反射获取内部类构造
     * ScopeConfig.InstantiationTracingBeanPostProcessor.class.getDeclaredConstructor(new Class[]{ScopeConfig.class});
     */
    @Configuration
    class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

        public Object postProcessBeforeInitialization(Object bean, String beanName) {
//            logger.info("--------------------------ScopeConfig-------------postProcessBeforeInitialization-------------------,bean:{},beanName:{}",bean,beanName);
            return bean;
        }


        public Object postProcessAfterInitialization(Object bean, String beanName) {
//            logger.info("--------------------------ScopeConfig---------------postProcessAfterInitialization-----------------,bean:{},beanName:{}",bean,beanName);
            return bean;
        }
    }
}
