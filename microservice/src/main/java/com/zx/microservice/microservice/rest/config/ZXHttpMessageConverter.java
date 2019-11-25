package com.zx.microservice.microservice.rest.config;

import com.zx.microservice.microservice.bean.User;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;


/**
 * 配置
 *
 * @author zhouxin
 * @date 2018/11/20
 * @see EnableWebMvc ->
 * @see DelegatingWebMvcConfiguration ->
 * @see WebMvcConfigurationSupport#getMessageConverters ->
 * <p>
 * readInternal {@link AbstractMessageConverterMethodArgumentResolver#readWithMessageConverters}
 * writeInternal {@link AbstractMessageConverterMethodProcessor#writeWithMessageConverters}
 */
public class ZXHttpMessageConverter extends AbstractHttpMessageConverter<User> {


  public ZXHttpMessageConverter() {
    super(MediaType.valueOf("application/properties"));
    setDefaultCharset(Charset.forName("UTF-8"));
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return clazz.isAssignableFrom(User.class);
  }

  @Override
  protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    Properties properties = new Properties();
    properties.load(new InputStreamReader(inputMessage.getBody(), getDefaultCharset()));
    return new User(Integer.valueOf(properties.get("user.id").toString()), properties.getProperty("user.userName"));
  }

  @Override
  protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    Properties properties = new Properties();
    properties.setProperty("id", user.getId() + "");
    properties.setProperty("userName", user.getUserName());
    properties.store(new OutputStreamWriter(outputMessage.getBody(), getDefaultCharset()), "json to properties");
  }
}
