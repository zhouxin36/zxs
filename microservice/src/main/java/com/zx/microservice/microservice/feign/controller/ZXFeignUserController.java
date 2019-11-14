package com.zx.microservice.microservice.feign.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouxin
 * @date 2018/12/17
 */
@RestController
public class ZXFeignUserController {

    private final UserClient userClient;

    public ZXFeignUserController(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") UserClient userClient) {
        this.userClient = userClient;
    }


    @GetMapping(value = "/ZXFeignGetEnv")
    public String getEnv(@RequestParam("message") String message) {
        return "message:" + message;
    }

    @GetMapping(value = "/zxFeign/ZXFeignGetEnv")
    public String zxGetEnv(@RequestParam("message") String message) {
        return userClient.getEnv(message);
    }
}
