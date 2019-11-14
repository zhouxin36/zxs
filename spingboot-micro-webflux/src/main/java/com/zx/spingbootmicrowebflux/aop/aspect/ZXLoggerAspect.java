package com.zx.spingbootmicrowebflux.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 无法使用
 *
 * @author zhouxin
 * @date 2018-12-14
 */

@Component
@Aspect
public class ZXLoggerAspect {

    private final static Logger logger = LoggerFactory.getLogger(ZXLoggerAspect.class);

    @Pointcut("execution(* com.zx.spingbootmicrowebflux.security.SecurityController.say())")
    public void logger(){}

    @Around(value = "logger()", argNames = "thisJoinPoint")
    public Object loggerOut(ProceedingJoinPoint thisJoinPoint) {
        Object returnValue = null;
        try {
            logger.warn("---------->ZXLoggerAspect.loggerOut|pref");
            returnValue = thisJoinPoint.proceed();
            logger.warn("---------->ZXLoggerAspect.loggerOut|suffix");
        } catch (Throwable throwable) {
            logger.warn("---------->ZXLoggerAspect.loggerOut|exception");
            throwable.printStackTrace();
        }
        logger.warn("---------->ZXLoggerAspect.loggerOut|returnValue");
        return returnValue;
    }
}
