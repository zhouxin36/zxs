package org.springframework.integration.config;

import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhouxin
 * @date 2018-12-19
 */
public class HandlerMethodArgumentResolversHolder {
  private final List<HandlerMethodArgumentResolver> resolvers;

  public HandlerMethodArgumentResolversHolder(List<HandlerMethodArgumentResolver> resolvers) {
    this.resolvers = new ArrayList<>(resolvers);
  }

  public List<HandlerMethodArgumentResolver> getResolvers() {
    return Collections.unmodifiableList(this.resolvers);
  }

  public void addResolver(HandlerMethodArgumentResolver resolver) {
    this.resolvers.add(resolver);
  }

  public boolean removeResolver(HandlerMethodArgumentResolver resolver) {
    return this.resolvers.remove(resolver);
  }

}
