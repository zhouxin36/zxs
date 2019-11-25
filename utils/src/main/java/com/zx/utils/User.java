package com.zx.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author zhouxin
 * @date 2018/11/20
 */
public class User {

  private static int a = 1;
  private static long aLong = 2L;
  private static char aChar = 'a';
  private static boolean aBoolean = true;
  private static short aShort = 2;
  private static float aFloat = 1.1f;
  private static double aDouble = 1.1;
  private static byte aByte = 1;
  private Integer id;
  private String userName;
  private LocalDateTime localDateTime;

  public User() {
  }

  public User(Integer id, String userName) {
    this.id = id;
    this.userName = userName;
  }

  public static void main(String[] args) {
    DefaultConversionService conversionService = new DefaultConversionService();
//        conversionService.addConverter(new AConverter());
//        User user = new User();
//        user.setId(1);
//        user.setLocalDateTime(LocalDateTime.now());
//        user.setUserName("zhouxin");
//        conversionService.convert(conversionService.convert(user, String.class), User.class);
    Integer convert = conversionService.convert("1", Integer.class);
    DefaultFormattingConversionService defaultFormattingConversionService = new DefaultFormattingConversionService();
    defaultFormattingConversionService.addFormatter(new DateFormatter("yyyy-MM-dd"));
    defaultFormattingConversionService.convert("2017-01-01", Date.class);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("userName='" + userName + "'")
        .add("localDateTime=" + localDateTime)
        .toString();
  }

  static class AConverter implements Converter<Object, String> {
    @Override
    public String convert(Object source) {
      return JSON.toJSONString(source);
    }
  }
}