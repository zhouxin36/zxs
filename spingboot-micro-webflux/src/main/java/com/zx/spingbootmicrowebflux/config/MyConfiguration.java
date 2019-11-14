package com.zx.spingbootmicrowebflux.config;

import com.zx.spingbootmicrowebflux.bean.User;
import com.zx.spingbootmicrowebflux.databinding.editor.StringToInteger;
import com.zx.spingbootmicrowebflux.databinding.formatter.MultiDateFormatter;
import com.zx.spingbootmicrowebflux.repository.UserRepository;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.Order;
import org.springframework.core.codec.AbstractDecoder;
import org.springframework.core.codec.AbstractEncoder;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurerComposite;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

/**
 * @author zhouxin
 * @date 2019-02-09
 */
@Configuration
public class MyConfiguration implements WebFluxConfigurer {

    public static final String MEDIA_TYPE = "application/properties";

    /**
     * =======================Codecs begin====================================================
     * <p>
     * 读取编解码器{@link WebFluxConfigurationSupport#serverCodecConfigurer}
     * <p>
     * 使用Reader{@link BodyExtractors#readWithMessageReaders)}
     * 使用Writer{@link BodyInserters#writeWithMessageWriters}
     *
     * @return
     */
    class MyDecoder extends AbstractDecoder<User> {

        public MyDecoder() {
            super(MediaType.valueOf(MEDIA_TYPE));
        }

        @Override
        public Flux<User> decode(Publisher<DataBuffer> inputStream
                , ResolvableType elementType
                , MimeType mimeType
                , Map<String, Object> hints) {
            return Flux.from(inputStream).map(dataBuffer -> {
                Properties properties = new Properties();
                try {
                    properties.load(new InputStreamReader(dataBuffer.asInputStream(), StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new User(Integer.valueOf(properties.get("user.id").toString())
                        , properties.getProperty("user.userName"));
            });
        }

        @Override
        public Mono<User> decodeToMono(Publisher<DataBuffer> inputStream, ResolvableType elementType,
                                       @Nullable MimeType mimeType, @Nullable Map<String, Object> hints) {

            return decode(inputStream, elementType, mimeType, hints).next();
        }
    }

    class MyEncoder extends AbstractEncoder<User> {

        public MyEncoder() {
            super(MediaType.valueOf(MEDIA_TYPE));
        }


        @Override
        public Flux<DataBuffer> encode(Publisher<? extends User> inputStream
                , DataBufferFactory bufferFactory
                , ResolvableType elementType
                , MimeType mimeType
                , Map<String, Object> hints) {
            Properties properties = new Properties();
            return Flux.from(inputStream).map(user -> {
                properties.setProperty("id", user.getId() + "");
                properties.setProperty("userName", user.getUserName());
                DataBuffer dataBuffer = bufferFactory.allocateBuffer();
                try {
                    properties.store(new OutputStreamWriter(dataBuffer.asOutputStream()
                            , StandardCharsets.UTF_8), "json to properties");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return dataBuffer;
            });
        }
    }

    @Bean
    public RouterFunction<ServerResponse> myCodecCustomizerUserController(UserRepository userRepository) {
        return RouterFunctions
                .route(POST("/jsonToProperties"), e -> ServerResponse.ok()
                        .contentType(MediaType.valueOf(MyConfiguration.MEDIA_TYPE))
                        .body(e.bodyToMono(User.class).flux(), User.class))
                .andRoute(POST("/propertiesToJson"), e -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(e.bodyToMono(User.class).flux(), User.class));
    }

    /**
     * 方式一
     * 调用
     * @see WebFluxAutoConfiguration.WebFluxConfig 构造注入
     * @see WebFluxAutoConfiguration.WebFluxConfig#configureHttpMessageCodecs
     * @see WebFluxConfigurerComposite#configureHttpMessageCodecs
     * @return
     */
    @Bean
    public CodecCustomizer myCodecCustomizer() {
        return codecConfigurer -> {
            codecConfigurer.customCodecs().decoder(new MyDecoder());
            codecConfigurer.customCodecs().encoder(new MyEncoder());
        };
    }



    /**
     * 方式二
     *
     * 加载WebFluxConfigurer {@link DelegatingWebFluxConfiguration#setConfigurers}
     * 调用  {@link WebFluxConfigurerComposite#configureHttpMessageCodecs}
     */
//    @Override
//    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
//        configurer.customCodecs().decoder(new MyDecoder());
//        configurer.customCodecs().encoder(new MyEncoder());
//    }
    /**
     * =======================Codecs end====================================================
     */

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // addConverter Converter,ConverterFactory, or GenericConverter interfaces
        // addFormatter Formatter
        registry.addConverter(new StringToInteger());
        registry.addFormatter(new MultiDateFormatter());
    }
}