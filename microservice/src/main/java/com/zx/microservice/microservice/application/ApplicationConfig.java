package com.zx.microservice.microservice.application;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.util.Locale;

/**
 * @author zhouxin
 * @since 2019/2/27
 */
public class ApplicationConfig {

  public static void main(String[] args) {
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    applicationContext.register(ApplicationConfig.class);
    applicationContext.refresh();
    System.out.println(applicationContext.getMessage("message", null, "Default", null));
    System.out.println(applicationContext.getMessage("argument.required", new Object[]{"userDao"}, "Required", null));
    System.out.println(applicationContext.getMessage("argument.required", new Object[]{"userDao"}, "Required", Locale.UK));
  }

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
    resourceBundleMessageSource.setBasenames("format", "exceptions");
    return resourceBundleMessageSource;
  }

  public void e() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
  }
}
