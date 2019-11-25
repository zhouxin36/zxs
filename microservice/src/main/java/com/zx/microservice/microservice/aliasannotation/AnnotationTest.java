package com.zx.microservice.microservice.aliasannotation;

import com.zx.microservice.microservice.aliasannotation.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * @author zhouxin
 * @date 2018/12/13
 */
@ComponentScan(basePackages = "com.zx.microservice.microservice.aliasannotation.service")
@EnableTransactionManagement
public class AnnotationTest {

  private static final Logger logger = LoggerFactory.getLogger(AnnotationTest.class);

  public static void main(String[] args) {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();


    annotationConfigApplicationContext.register(AnnotationTest.class);
    annotationConfigApplicationContext.refresh();

    annotationConfigApplicationContext.getBeansOfType(EchoService.class).forEach((beanName, bean) -> {
      logger.error("------------------->Bean Name:{},Bean:{}", beanName, bean);
      bean.echo();
    });
    annotationConfigApplicationContext.close();
  }

  @Configuration
  public static class MyPlatformTransactionManager implements PlatformTransactionManager {

    @Override
    public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
      logger.info("------------->getTransaction:{}", definition);
      return new DefaultTransactionStatus(
          null, true, true,
          definition.isReadOnly(), true, null);
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
      logger.info("------------->commit");
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
      logger.info("------------->rollback");
    }
  }
}
