package com.zx.spingbootmicrowebflux.bean;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * @author zhouxin
 * @date 2018-11-24
 */
public class User {

  private Integer id;

  private String userName;

  private LocalDateTime localDateTime;

  public User() {
  }

  public User(Integer id, String userName) {
    this.id = id;
    this.userName = userName;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
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

  @Override
  public String toString() {
    return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("userName='" + userName + "'")
        .add("localDateTime=" + localDateTime)
        .toString();
  }
}
