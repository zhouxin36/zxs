package com.zx.spingbootmicrowebflux.error.exception;


import com.zx.spingbootmicrowebflux.error.enums.ZXErrorCode;

/**
 * @author zhouxin
 * @date 2019-02-07
 */
public class ZXException extends RuntimeException {

  private Integer code;

  public ZXException(ZXErrorCode errorCode) {
    super(errorCode.getMessage());
    this.code = errorCode.getCode();
  }

  public ZXException(ZXErrorCode errorCode, String message) {
    super(message);
    this.code = errorCode.getCode();
  }

  public Integer getCode() {
    return code;
  }
}
