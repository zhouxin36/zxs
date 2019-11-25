package com.zx.microservice.microservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhouxin
 * @date 2018/11/30
 */
public class SpringEventListenerDemo {

  private static final Logger logger = LoggerFactory.getLogger(SpringEventListenerDemo.class);

  public static void main(String[] args) {
    // Annotation 驱动的 Spring 上下文
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext();
    // 注册监听器
    context.addApplicationListener(
        new ApplicationListener<MyApplicationEvent>() {
          /**
           * 监听器得到事件
           * @param event
           */
          @Override
          public void onApplicationEvent(MyApplicationEvent event) {

            logger.error("接收到事件：" + event.getSource() + " @ " + event.getApplicationContext());
          }
        });

    context.refresh();
    // 发布事件
    context.publishEvent(new MyApplicationEvent(context, "Hello,World"));
    context.publishEvent(new MyApplicationEvent(context, 1));
    context.publishEvent(new MyApplicationEvent(context, 100));


  }

  private static class MyApplicationEvent extends ApplicationEvent {

    private final ApplicationContext applicationContext;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyApplicationEvent(ApplicationContext applicationContext, Object source) {
      super(source);
      this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
      return applicationContext;
    }
  }


}
