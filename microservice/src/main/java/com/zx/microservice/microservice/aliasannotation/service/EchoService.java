package com.zx.microservice.microservice.aliasannotation.service;

import com.zx.microservice.microservice.aliasannotation.annotation.TransactionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhouxin
 * @date 2018/12/13
 */
@TransactionalService("heheService")
public class EchoService {

    private static final Logger logger = LoggerFactory.getLogger(EchoService.class);

    public void echo(){
        logger.error("--------------->EchoService输出");
    }
}
