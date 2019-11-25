package com.zx.spingbootmicrowebflux.error.enums;

/**
 * @author zhouxin
 * @date 2019-02-07
 */
public enum ZXErrorCode {
  MY_ERROR(580, "我的错"),
  YOUR_ERROR(500, "你的错"),
  ;
  private Integer code;
  private String message;

  ZXErrorCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
