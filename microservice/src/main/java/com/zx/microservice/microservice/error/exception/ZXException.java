package com.zx.microservice.microservice.error.exception;

import com.zx.microservice.microservice.error.enums.ZXErrorCode;

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

    public ZXException(String message) {
        super(message);
        this.code = 500;
    }

    public Integer getCode() {
        return code;
    }
}
