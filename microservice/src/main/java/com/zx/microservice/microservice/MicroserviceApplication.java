package com.zx.microservice.microservice;

import com.zx.microservice.microservice.feign.annotation.EnableZXFeign;
import com.zx.microservice.microservice.feign.controller.UserClient;
import net.sf.cglib.core.DebuggingClassWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * -classpath E:\idea-project\zxs\code-analysis\build\classes\java\main;E:\idea-project\zxs\code-analysis\build\resources\main;
 * --add-opens java.base/java.lang=cglib
 */
@SpringBootApplication
@ServletComponentScan
@EnableZXFeign(classes = UserClient.class)
@EnableCircuitBreaker
public class MicroserviceApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(MicroserviceApplication.class);

  public static void main(String[] args) {
    String path = "D://microServiceCode";
    // CG
    System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, path);
    new SpringApplicationBuilder(MicroserviceApplication.class)
        .listeners((ApplicationListener<ApplicationStartingEvent>) event ->
            LOGGER.info("---------------1-------------->eventClass:{}", event.getClass()))
//                .listeners(event ->
//                        logger.info("---------------2-------------->eventClass:{}",event.getClass()))
        .listeners((ApplicationListener<ApplicationStartedEvent>) event ->
            LOGGER.info("---------------3-------------->eventClass:{}", event.getClass()))
//                .
        .run(args);
    // 换banner
//        application.setBanner(new ImageBanner(new FileUrlResource("F:/桌面-图片/index.png")));

  }

  @Bean
  public ExitCodeGenerator exitCodeGenerator() {
    return () -> 42;
  }


  public static class CustomPropertySourceLocator implements PropertySourceLocator {

    @Override
    public PropertySource<?> locate(Environment environment) {
      Map<String, Object> source = new HashMap<>();
      source.put("server.port", "9090");
      return new MapPropertySource("my-property-source", source);

    }
  }
}
