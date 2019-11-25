package com.zx.microservice.microservice.security;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.userdetails.UserDetailsResourceFactoryBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * 默认实现
 *
 * @author zhouxin
 * @date 2019/1/9
 * @see SecurityAutoConfiguration
 * @see UserDetailsServiceAutoConfiguration
 * @see SpringBootWebSecurityConfiguration
 */
@EnableWebSecurity
public class WebSecurityConfig {

  /**
   * {@link InMemoryUserDetailsManager 账号密码配置}
   * <p>
   * 权限配置：
   * 外部配置：{@link UserDetailsResourceFactoryBean}
   * <p>
   * web mvc 配置
   */
  @Bean
  public UserDetailsService userDetailsService() throws Exception {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("user")
        .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)
        .password("password").roles("USER").build());
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String finalPassword = "{bcrypt}" + bCryptPasswordEncoder.encode("123456");
    manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
    finalPassword = "{noop}123456";
    manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
    return manager;
  }

  /**
   * @see org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityConfigurerAdapter
   * 请求配置：
   * 加载 {@link WebSecurityConfiguration#setFilterChainProxySecurityConfigurer}
   * 默认实现 {@link WebSecurityConfigurerAdapter#configure(HttpSecurity)}
   */
  @Configuration
  class ZXWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      /**
       * 简单认证
       */
      http
          .authorizeRequests()
          .antMatchers("/security/**").authenticated()
          .anyRequest().permitAll()
          .and()
          .httpBasic();
//            http.csrf().disable();
           /* http
                    .logout()
                    .logoutUrl("/my/logout")
                    .logoutSuccessUrl("/my/index")
//                    .logoutSuccessHandler(null) // 注销成功后处理：例如重定向URL，SimpleUrlLogoutSuccessHandler
//                    .invalidateHttpSession(true)
//                    .addLogoutHandler(logoutHandler) // 注销后处理：例如清除cookie，CookieClearingLogoutHandler
//                    .deleteCookies(cookieNamesToClear)
                    ;*/
      /**
       * oauth 2.0
       *
       */
//            http.authorizeRequests()
//                    .antMatchers("/security/**").authenticated()
//                    .anyRequest().permitAll()
//                    .and().oauth2Login();
//            http
//                    .oauth2Client();
//                    .clientRegistrationRepository(applicationContext.getBean(ClientRegistrationRepository.class));
//                    .authorizedClientRepository(this.authorizedClientRepository())
//                    .authorizedClientService(this.authorizedClientService())
//                    .authorizationCodeGrant()
//                    .authorizationRequestRepository(this.authorizationRequestRepository())
//                    .authorizationRequestResolver(this.authorizationRequestResolver())
//                    .accessTokenResponseClient(this.accessTokenResponseClient());
    }

    /**
     * Spring Boot 2 配置，这里要bean 注入
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
      return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
  }

//    @Bean
//    public ClientRegistrationRepository getClientRegistrationRepository(){
//        ClientRegistration build = ClientRegistration
//                .withRegistrationId("google")
//                .clientId("google-client-id")
//                .clientSecret("google-client-secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
//                .scope("openid", "profile", "email", "address", "phone")
//                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
//                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//                .clientName("Google")
//                .build();
//        String str= null;
//        return new InMemoryClientRegistrationRepository(build);
//    }


}