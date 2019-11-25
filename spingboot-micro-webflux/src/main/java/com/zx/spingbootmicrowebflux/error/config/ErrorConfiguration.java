package com.zx.spingbootmicrowebflux.error.config;

import com.zx.spingbootmicrowebflux.error.handler.MyErrorWebExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhouxin
 * @date 2019-02-10
 */
@Configuration
public class ErrorConfiguration {


  private final ServerProperties serverProperties;

  private final ApplicationContext applicationContext;

  private final ResourceProperties resourceProperties;

  private final List<ViewResolver> viewResolvers;

  private final ServerCodecConfigurer serverCodecConfigurer;

  public ErrorConfiguration(ServerProperties serverProperties,
                            ResourceProperties resourceProperties,
                            ObjectProvider<ViewResolver> viewResolversProvider,
                            ServerCodecConfigurer serverCodecConfigurer,
                            ApplicationContext applicationContext) {
    this.serverProperties = serverProperties;
    this.applicationContext = applicationContext;
    this.resourceProperties = resourceProperties;
    this.viewResolvers = viewResolversProvider.orderedStream()
        .collect(Collectors.toList());
    this.serverCodecConfigurer = serverCodecConfigurer;
  }

  @Bean
  public ErrorWebExceptionHandler errorWebExceptionHandler(
      ErrorAttributes errorAttributes) {
    MyErrorWebExceptionHandler exceptionHandler = new MyErrorWebExceptionHandler(
        errorAttributes, this.resourceProperties,
        this.applicationContext);
    exceptionHandler.setViewResolvers(this.viewResolvers);
    exceptionHandler.setMessageWriters(this.serverCodecConfigurer.getWriters());
    exceptionHandler.setMessageReaders(this.serverCodecConfigurer.getReaders());
    return exceptionHandler;
  }
}
