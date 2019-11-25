package com.zx.microservice.microservice.aop.interceptor;

import com.zx.microservice.microservice.rest.config.ZXWebMvcConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注册：{@link ZXWebMvcConfigurerAdapter#addInterceptors}
 *
 * @author zhouxin
 * @date 2018-12-14
 */
public class ZXLoggerInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(ZXLoggerInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        logger.warn("---------->ZXLoggerInterceptor.preHandle");
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        logger.warn("---------->ZXLoggerInterceptor.postHandle");
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.warn("---------->ZXLoggerInterceptor.afterCompletion");
  }
}
