package com.zx.spingbootmicrowebflux.webClient;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author zhouxin
 * @date 2019/2/14
 */
//@Service
public class MyService {
  private final WebClient webClient;

  public MyService(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://example.org").build();
  }

  public Mono<Object> someRestCall(String name) {
    return this.webClient.get().uri("/{name}/details", name)
        .retrieve().bodyToMono(Object.class);
  }
}