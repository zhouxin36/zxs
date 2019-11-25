package com.zx.spingbootmicrowebflux.aop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 需要使用webFlux
 *
 * @author zhouxin
 * @date 2019/2/12
 */
@Component
@Order(0)
public class NewFilter implements WebFilter {

  private static final Logger logger = LoggerFactory.getLogger(NewFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return chain.filter(exchange).doOnSuccess(e -> logger.info("--------------newFilter-----------success"));
  }
}
