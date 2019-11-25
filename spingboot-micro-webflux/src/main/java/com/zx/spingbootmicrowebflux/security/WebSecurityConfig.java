package com.zx.spingbootmicrowebflux.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.reactive.ReactiveManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.core.userdetails.ReactiveUserDetailsServiceResourceFactoryBean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 默认实现
 *
 * @author zhouxin
 * @date 2019/1/9
 * @see ReactiveSecurityAutoConfiguration
 * @see UserDetailsServiceAutoConfiguration
 */
@EnableWebFluxSecurity
public class WebSecurityConfig {

  private final OAuth2ClientProperties oAuth2ClientProperties;

  @Autowired
  public WebSecurityConfig(OAuth2ClientProperties oAuth2ClientProperties) {
    this.oAuth2ClientProperties = oAuth2ClientProperties;
  }

  @Bean
  public MapReactiveUserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("user").passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode).password("password").roles("USER").build();
    return new MapReactiveUserDetailsService(user);
  }

  /**
   * 权限配置：
   * 外部配置：{@link ReactiveUserDetailsServiceResourceFactoryBean}
   * 请求配置：
   * 加载 {@link org.springframework.security.config.annotation.web.reactive.WebFluxSecurityConfiguration#securityWebFilterChains}
   * 默认实现 {@link org.springframework.security.config.annotation.web.reactive.WebFluxSecurityConfiguration#springSecurityFilterChain}
   *
   * @param http
   * @return
   * @see ReactiveManagementWebSecurityAutoConfiguration
   */
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http
//            , ReactiveClientRegistrationRepository reactiveClientRegistrationRepository
//            , ReactiveOAuth2AuthorizedClientService reactiveOAuth2AuthorizedClientService
//            , ServerOAuth2AuthorizedClientRepository serverOAuth2AuthorizedClientRepository
  ) {
    http
        .authorizeExchange()
        .pathMatchers("/security/**").authenticated()
        .anyExchange().permitAll()
        .and()
        .httpBasic();
    http.csrf().disable();
    http
        .logout()
        .logoutUrl("/my/logout")
    ;
//        http
//                .oauth2Client()
//                .clientRegistrationRepository(reactiveClientRegistrationRepository)
//                .authorizedClientRepository(serverOAuth2AuthorizedClientRepository)
//                .authorizedClientService(this.authorizedClientService())
//                .authorizationCodeGrant()
//                .authorizationRequestRepository(this.authorizationRequestRepository())
//                .authorizationRequestResolver(this.authorizationRequestResolver())
//                .accessTokenResponseClient(this.accessTokenResponseClient())
//                ;
    return http.build();
  }
}