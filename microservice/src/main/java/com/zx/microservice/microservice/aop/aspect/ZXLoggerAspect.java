package com.zx.microservice.microservice.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhouxin
 * @date 2018-12-14
 */

@Component
@Aspect
public class ZXLoggerAspect {

  private static final Logger logger = LoggerFactory.getLogger(ZXLoggerAspect.class);
  @DeclareParents(value = "com.zx.microservice.microservice.aop.aspect.AspectService", defaultImpl = WeaveMethodImpl.class)
  public static WeaveMethod weaveMethod;

  @Pointcut("execution(* com.zx.microservice.microservice.rest.controller.UserController.getEnv()) " +
      "|| execution(* com.zx.microservice.microservice.aop.aspect.AspectService.hehe())")
  public void logger() {
  }

  @Around(value = "logger()", argNames = "thisJoinPoint")
  public Object loggerOut(ProceedingJoinPoint thisJoinPoint) {
    Object returnValue = null;
    try {
      logger.warn("---------->ZXLoggerAspect.loggerOut|pref");
      returnValue = thisJoinPoint.proceed();
//            logger.warn("---------->ZXLoggerAspect.loggerOut|suffix");
    } catch (Throwable throwable) {
//            logger.warn("---------->ZXLoggerAspect.loggerOut|exception");
      throwable.printStackTrace();
    }
//        logger.warn("---------->ZXLoggerAspect.loggerOut|returnValue");
    return returnValue;
  }

}
