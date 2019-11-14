package com.zx.microservice.microservice.aop.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhouxin
 * @since 2019/3/8
 */
@Controller
@RequestMapping("/aspect")
public class AspectController {

    private static final Logger logger = LoggerFactory.getLogger(AspectController.class);

    private final AspectService aspectService;

    private final ApplicationContext applicationContext;

    public AspectController(AspectService aspectService, ApplicationContext applicationContext) {
        this.aspectService = aspectService;
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/say")
    public void say(){
        logger.info(aspectService.hehe());
        logger.info(((WeaveMethod)aspectService).eat());
        logger.info(((WeaveMethod)aspectService).say());
    }
}
