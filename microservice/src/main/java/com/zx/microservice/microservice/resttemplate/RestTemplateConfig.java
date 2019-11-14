package com.zx.microservice.microservice.resttemplate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;

/**
 * @see RestTemplateAutoConfiguration
 * 自定义 {@link RestTemplateCustomizer}
 *
 * @author zhouxin
 * @date 2018-12-15
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(@Qualifier("restTemplateInterceptor") ClientHttpRequestInterceptor clientHttpRequestInterceptor
            , RestTemplateBuilder restTemplateBuilder){
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.setMessageConverters(Arrays.asList(new StringHttpMessageConverter(Charset.forName("utf-8"))
                ,new ByteArrayHttpMessageConverter()));
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory());
        restTemplate.setInterceptors(Collections.singletonList(clientHttpRequestInterceptor));
        return restTemplate;
    }
}
