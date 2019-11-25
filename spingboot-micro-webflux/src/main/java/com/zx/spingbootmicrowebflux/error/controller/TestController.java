package com.zx.spingbootmicrowebflux.error.controller;

import com.zx.spingbootmicrowebflux.error.enums.ZXErrorCode;
import com.zx.spingbootmicrowebflux.error.exception.ZXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger logger = LoggerFactory.getLogger(TestController.class);

  @RequestMapping("/test1")
  public void test1() {
    throw new ZXException(ZXErrorCode.MY_ERROR);
  }

  @RequestMapping("/test2")
  public void test2() {
    throw new AsyncRequestTimeoutException();
  }

  @RequestMapping("/str")
  public void str(String str) {
    logger.info(str);
  }
}
