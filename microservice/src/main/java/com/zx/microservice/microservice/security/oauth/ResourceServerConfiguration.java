package com.zx.microservice.microservice.security.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * OAuth 资源服务器配置
 *
 * @author zhouxin
 * @date 2019/2/13
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  private static final String DEMO_RESOURCE_ID = "security";

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // Since we want the protected resources to be accessible in the UI as well we need
    // session creation to be allowed (it's disabled by default in 2.0.6)
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .and()
        .requestMatchers().anyRequest()
        .and()
        .anonymous()
        .and()
        .authorizeRequests()
        .antMatchers("/security/**").authenticated();//配置order访问控制，必须认证过后才可以访问
  }
}