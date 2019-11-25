package com.zx.codeanalysis.pattern.singleton.reflect;

/**
 * @author zhouxin
 * @since 2019/3/12
 */
public enum TestEnum {
  HEHE("id", "userName"),
  ;

  private String id;

  private String userName;

  TestEnum(String id, String userName) {
    this.id = id;
    this.userName = userName;
  }

  public String getId() {
    return id;
  }

  public String getUserName() {
    return userName;
  }
}
