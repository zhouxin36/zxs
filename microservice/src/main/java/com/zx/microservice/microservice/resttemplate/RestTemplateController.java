package com.zx.microservice.microservice.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhouxin
 * @date 2018-12-15
 */

@RestController
@EnableScheduling
public class RestTemplateController {

    private final RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String applicationName;

    public RestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/invoke/say", method = RequestMethod.GET)
    public String invokeSay(@RequestParam String message){
        return restTemplate.getForObject("https://" + applicationName + "/say?message=" + message, String.class);
    }

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String say(@RequestParam String message){
        return "你说：" + message;
    }
}
