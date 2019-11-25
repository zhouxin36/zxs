package com.zx.codeanalysis.pattern.proxy.dynamic;

import java.util.StringJoiner;
import java.util.UUID;

public class User {
  private String id;
  private String userName;

  public User() {
    this.id = UUID.randomUUID().toString();
    this.userName = UUID.randomUUID().toString();
  }

  public User(String id, String userName) {
    this.id = id;
    this.userName = userName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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
        .add("id='" + id + "'")
        .add("userName='" + userName + "'")
        .toString();
  }
}
