package com.zx.spingbootmicrowebflux.flux.config;

import com.zx.spingbootmicrowebflux.bean.User;
import com.zx.spingbootmicrowebflux.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

/**
 * @author zhouxin
 * @date 2018-11-24
 */
@Configuration
public class WebFluxConfiguration {


  @Bean
  public RouterFunction<ServerResponse> routerFunctionUsers(UserRepository userRepository) {
    return RouterFunctions.route(GET("/users")
        , e -> ServerResponse.ok().body(Flux.fromIterable(userRepository.findAll()), User.class))
        .andRoute(POST("/addUser"), e -> ServerResponse.ok().body(e.bodyToMono(User.class).map(userRepository::save), Boolean.class));
  }

}
