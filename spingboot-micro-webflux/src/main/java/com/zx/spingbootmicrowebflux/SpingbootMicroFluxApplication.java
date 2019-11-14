package com.zx.spingbootmicrowebflux;

import com.zx.spingbootmicrowebflux.event.HttpRemoteAppEventListener;
import com.zx.spingbootmicrowebflux.stream.config.MyChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.reactive.context.StandardReactiveWebEnvironment;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.config.BindingServiceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.integration.config.HandlerMethodArgumentResolversHolder;
import org.springframework.integration.context.IntegrationContextUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableBinding(MyChannel.class)
public class SpingbootMicroFluxApplication {

    private final static Logger logger = LoggerFactory.getLogger(SpingbootMicroFluxApplication.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.setId("ZX");
        annotationConfigApplicationContext.refresh();
        new SpringApplicationBuilder(SpingbootMicroFluxApplication.class)
                .environment(config())
                .listeners(new HttpRemoteAppEventListener())
                .parent(annotationConfigApplicationContext)
                .run(args);
    }

    /**
     * 系统配置（由于java9模块化，classpath无法加载）
     * @return
     */
    private static ConfigurableEnvironment config() {
        StandardReactiveWebEnvironment standardReactiveWebEnvironment = new StandardReactiveWebEnvironment();
        standardReactiveWebEnvironment.getPropertySources().forEach(e->{
            if(e.getName().equals(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME)){
//                logger.info("------>env:{}",e);
                logger.info("------>env:{}",e.getProperty("java.class.path"));
            }
        });
        Map<String, Object> map = new HashMap<>();
        map.put("server.port","3636");
        map.put("management.endpoints.web.exposure.include","*");//打开actuator权限
        map.put("management.server.port","10001");//分开服务器端口，actuator
        MapPropertySource mapPropertySource = new MapPropertySource("ZXEnvironment",map);
        standardReactiveWebEnvironment.getPropertySources().addFirst(mapPropertySource);
        return standardReactiveWebEnvironment;
    }

    /**
     * 一个BUG。。。
     * {@link BindingServiceConfiguration#messageHandlerMethodFactory}
     * copy from {@link org.springframework.integration.handler.support.HandlerMethodArgumentResolversHolder}
     * @return
     */
    @Bean(IntegrationContextUtils.ARGUMENT_RESOLVERS_BEAN_NAME)
    public HandlerMethodArgumentResolversHolder handlerMethodArgumentResolversHolder(){
        return new HandlerMethodArgumentResolversHolder(Collections.emptyList());
    }

}
