package com.zx.microservice.microservice.error.controller;

import com.zx.microservice.microservice.error.enums.ZXErrorCode;
import com.zx.microservice.microservice.error.exception.ZXException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

/**
 * @author zhouxin
 * @date 2019-02-07
 */
@Controller
@RequestMapping("/error")
public class TestController {

  @RequestMapping("/test1")
  public void test1() {
    throw new ZXException(ZXErrorCode.MY_ERROR);
  }

  @RequestMapping("/test2")
  public void test2() {
    throw new AsyncRequestTimeoutException();
  }
}
